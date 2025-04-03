package com.techm.mobileprepaidrechargesystem.repository;

import com.techm.mobileprepaidrechargesystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserPhonenumber(String userPhonenumber);
	Optional<User> findByUsername(String username);
	List<User> findByRoleRoleId(Long roleId);
}
