package effective.mobile.tracker.service;

import effective.mobile.tracker.exceptions.NotFoundRoleException;
import effective.mobile.tracker.model.role.Role;
import effective.mobile.tracker.model.role.RoleEnum;
import effective.mobile.tracker.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getRoleOrThrow(RoleEnum roleEnum){
        return roleRepository.findByName(roleEnum).orElseThrow(() -> new NotFoundRoleException(Role.class, roleEnum));
    }
}
