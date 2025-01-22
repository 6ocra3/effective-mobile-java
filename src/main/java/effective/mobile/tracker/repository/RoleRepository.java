package effective.mobile.tracker.repository;

import effective.mobile.tracker.model.role.Role;
import effective.mobile.tracker.model.role.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}
