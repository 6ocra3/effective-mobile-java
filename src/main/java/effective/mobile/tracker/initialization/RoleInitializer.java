package effective.mobile.tracker.initialization;

import effective.mobile.tracker.model.role.Role;
import effective.mobile.tracker.model.role.RoleEnum;
import effective.mobile.tracker.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleInitializer {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void initRoles() {
        if (roleRepository.findAll().isEmpty()) {
            roleRepository.save(new Role(RoleEnum.ROLE_USER));
            roleRepository.save(new Role(RoleEnum.ROLE_ADMIN));
        }
    }
}