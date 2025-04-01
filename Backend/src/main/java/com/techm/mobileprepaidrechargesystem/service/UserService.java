package com.techm.mobileprepaidrechargesystem.service;

import com.techm.mobileprepaidrechargesystem.model.User;
import com.techm.mobileprepaidrechargesystem.model.User.AccountStatus;
import com.techm.mobileprepaidrechargesystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUserExistsByPhoneNumber(String phoneNumber) {
        Optional<User> user = userRepository.findByUserPhonenumber(phoneNumber);
        return user.isPresent();
    }
    
    public Optional<User> getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByUserPhonenumber(phoneNumber);
    }
    
    public User addUser(User user) {
        return userRepository.save(user);
    }
    
    public List<User> getAllUsers() {
        return userRepository.findByRoleRoleId(1L);
    }
    
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    public User updateUser(Long userId, User updatedUser) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setUserFname(updatedUser.getUserFname());
        existingUser.setUserLname(updatedUser.getUserLname());
        existingUser.setUserEmail(updatedUser.getUserEmail());
        existingUser.setUserPhonenumber(updatedUser.getUserPhonenumber());
        existingUser.setUserAlternatePhonenumber(updatedUser.getUserAlternatePhonenumber());
        existingUser.setUserDateOfBirth(updatedUser.getUserDateOfBirth());
        existingUser.setUserAddress(updatedUser.getUserAddress());
        existingUser.setUserDistrict(updatedUser.getUserDistrict());
        existingUser.setUserState(updatedUser.getUserState());
        existingUser.setUserPincode(updatedUser.getUserPincode());

        return userRepository.save(existingUser);
    }

    
    public void deleteUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(userId);
    }

	public List<User> getAllAdminUsers() {
        return userRepository.findByRoleRoleId(2L);

	}
	
	public boolean deactivateUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUserAccountStatus(AccountStatus.INACTIVE); 
            userRepository.save(user);
            return true;
        }
        return false;
    }

	public User getUserbyUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
		return userOptional.get();
	}

	public boolean isValidPassword(long userId , String userPassword) {
		Optional<User> user = userRepository.findById(userId);
		System.out.println(user.get());
		if(user.get().getPassword().equals(userPassword)) {
			return true;
		}
		return false;
	}

	public boolean changeAdminPassword(long userId, String password) {
		User user = userRepository.findById(userId).get();
		user.setPassword(password);
		userRepository.save(user);
		return true;
	}

}
