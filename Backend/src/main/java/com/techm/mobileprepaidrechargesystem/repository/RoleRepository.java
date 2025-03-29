package com.techm.mobileprepaidrechargesystem.repository;

import com.techm.mobileprepaidrechargesystem.model.Role;
import com.techm.mobileprepaidrechargesystem.model.Role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}
