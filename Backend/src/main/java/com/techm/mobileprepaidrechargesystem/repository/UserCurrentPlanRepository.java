package com.techm.mobileprepaidrechargesystem.repository;

import com.techm.mobileprepaidrechargesystem.model.User;
import com.techm.mobileprepaidrechargesystem.model.UserCurrentPlanDetails;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository 
public interface UserCurrentPlanRepository extends JpaRepository<UserCurrentPlanDetails, Long> {

	List<UserCurrentPlanDetails> findByPlanExpiryDateBefore(LocalDate planExpiryDate);
    Optional<UserCurrentPlanDetails> findByUser_UserId(Long userId);

}

