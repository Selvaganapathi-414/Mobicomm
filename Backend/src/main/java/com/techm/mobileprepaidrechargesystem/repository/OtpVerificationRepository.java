package com.techm.mobileprepaidrechargesystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techm.mobileprepaidrechargesystem.model.OtpVerification;

import java.util.Optional;

public interface OtpVerificationRepository extends JpaRepository<OtpVerification, Long> {

	@Query("SELECT o FROM OtpVerification o WHERE o.phoneNumber = :phoneNumber ORDER BY o.generatedTime DESC LIMIT 1")
	Optional<OtpVerification> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

}
