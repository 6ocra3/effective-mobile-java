package effective.mobile.tracker.service;

import effective.mobile.tracker.dto.auth.LoginRequest;
import effective.mobile.tracker.dto.auth.LoginResponse;
import effective.mobile.tracker.dto.auth.RegisterRequest;
import effective.mobile.tracker.dto.auth.RegisterResponse;
import effective.mobile.tracker.exceptions.ExistByLoginException;
import effective.mobile.tracker.model.User;
import effective.mobile.tracker.model.role.Role;
import effective.mobile.tracker.model.role.RoleEnum;
import effective.mobile.tracker.repository.UserRepository;
import effective.mobile.tracker.security.JwtUtils;
import effective.mobile.tracker.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public RegisterResponse register(RegisterRequest registerRequest){
        if(isUserExists(registerRequest.getEmail())){
            throw new ExistByLoginException(User.class, registerRequest.getEmail());
        }

        User user = new User(registerRequest.getEmail(), passwordEncoder.encode(registerRequest.getPassword()));
        addDefaultRole(user);
        userRepository.save(user);

        return new RegisterResponse(
                authenticate(registerRequest)
        );

    }

    public boolean isUserExists(String email){return userRepository.existsByEmail(email);}

    public void addDefaultRole(User user){
        Role defaultRole = this.roleService.getRoleOrThrow(RoleEnum.ROLE_USER);
        user.getRoles().add(defaultRole);
    }

    public LoginResponse login(LoginRequest loginRequest){
        return authenticate(loginRequest);
    }

    public LoginResponse authenticate(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new LoginResponse(
                jwt,
                userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet())
        );
    }

    public Optional<User> findByEmail(String email){
        return this.userRepository.findByEmail(email);
    }
}
