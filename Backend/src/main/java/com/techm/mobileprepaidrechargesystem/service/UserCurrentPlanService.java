package com.techm.mobileprepaidrechargesystem.service;

import com.techm.mobileprepaidrechargesystem.model.UserCurrentPlanDetails;
import com.techm.mobileprepaidrechargesystem.model.Plan;
import com.techm.mobileprepaidrechargesystem.model.Transaction;
import com.techm.mobileprepaidrechargesystem.model.User;
import com.techm.mobileprepaidrechargesystem.repository.PlanRepository;
import com.techm.mobileprepaidrechargesystem.repository.TransactionRepository;
import com.techm.mobileprepaidrechargesystem.repository.UserCurrentPlanRepository;
import com.techm.mobileprepaidrechargesystem.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserCurrentPlanService {
    private final UserCurrentPlanRepository userCurrentPlanRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private TransactionRepository transactionRepository;

   
    public UserCurrentPlanService(UserCurrentPlanRepository userCurrentPlanRepository) {
        this.userCurrentPlanRepository = userCurrentPlanRepository;
    } 
    
    public List<UserCurrentPlanDetails> getUsersWithExpiringPlans() {
        return userCurrentPlanRepository.findByPlanExpiryDateBefore(LocalDate.now().plusDays(300));
    }

    public UserCurrentPlanDetails saveOrUpdateUserPlan(UserCurrentPlanDetails userPlanDetails) {
        // Fetch User from DB to avoid detached entity issue
        User user = userRepository.findById(userPlanDetails.getUser().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch Plan from DB
        Plan plan = planRepository.findById(userPlanDetails.getPlan().getPlanId())
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        // Fetch Transaction from DB
        Transaction transaction = transactionRepository.findById(userPlanDetails.getTransaction().getTransactionId())
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        
        Optional<UserCurrentPlanDetails> existingPlan = userCurrentPlanRepository.findById(user.getUserId());

        if (existingPlan.isPresent()) {
            // Update the existing record
            UserCurrentPlanDetails userPlan = existingPlan.get();
            userPlan.setUser(user);
            userPlan.setPlan(plan);
            userPlan.setTransaction(transaction);
            userPlan.setDataUsage(userPlanDetails.getDataUsage());

            return userCurrentPlanRepository.save(userPlan);
        } else {
            // Create a new record
            userPlanDetails.setUser(user);
            userPlanDetails.setPlan(plan);
            userPlanDetails.setTransaction(transaction);
            return userCurrentPlanRepository.save(userPlanDetails);
        }
    }


    public Optional<UserCurrentPlanDetails> getUserCurrentPlan(Long userId) {
        return userCurrentPlanRepository.findById(userId);
    }

}
