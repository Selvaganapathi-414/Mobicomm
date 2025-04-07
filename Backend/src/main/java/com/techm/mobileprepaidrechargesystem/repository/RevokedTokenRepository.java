package com.techm.mobileprepaidrechargesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.techm.mobileprepaidrechargesystem.model.RevokedToken;

@Repository
public interface RevokedTokenRepository extends JpaRepository<RevokedToken, String> {
    boolean existsByToken(String token);
}
