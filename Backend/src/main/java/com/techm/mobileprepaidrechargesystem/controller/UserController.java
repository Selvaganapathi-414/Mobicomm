package com.techm.mobileprepaidrechargesystem.controller;

import com.techm.mobileprepaidrechargesystem.exception.ResourceNotFoundException;
import com.techm.mobileprepaidrechargesystem.model.User;
import com.techm.mobileprepaidrechargesystem.repository.UserRepository;
import com.techm.mobileprepaidrechargesystem.service.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

	@PreAuthorize("hasAuthority('CUSTOMER')")

    @GetMapping("/customer/phonenumbers/{phoneNumber}")
    public ResponseEntity<Boolean> checkUserPhoneNumber(@PathVariable String phoneNumber) {
        boolean exists = userService.isUserExistsByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(exists);
    }

	@PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/customer/phone/{phoneNumber}")
    public ResponseEntity<User> getUserByPhoneNumber(@PathVariable String phoneNumber) {
        return userService.getUserByPhoneNumber(phoneNumber)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("User with phone number " + phoneNumber + " not found"));
    }

	@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public List<User> getNonAdminUsers() {
        return userService.getAllUsers();
    }

	@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/adminusers")
    public List<User> getAdminUsers() {
        return userService.getAllAdminUsers();
    }

	@PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User createdUser = userService.addUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

	@PreAuthorize("hasAuthority('CUSTOMER') or hasAuthority('ADMIN')")
    @GetMapping("/both/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("User with ID " + userId + " not found");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
	

	@PreAuthorize("hasAuthority('CUSTOMER') or hasAuthority('ADMIN')")
    @PutMapping("/both/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        User existingUser = userService.getUserById(userId);
        if (existingUser == null) {
            throw new ResourceNotFoundException("User with ID " + userId + " not found");
        }
        User updated = userService.updateUser(userId, updatedUser);
        return ResponseEntity.ok(updated);
    }

	@PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/admin/changeStatus/{id}")
    public ResponseEntity<String> deactivateUser(@PathVariable Long id) {
        boolean isUpdated = userService.deactivateUser(id);
        if (!isUpdated) {
            throw new ResourceNotFoundException("User with ID " + id + " not found");
        }
        return ResponseEntity.ok("User status updated to Inactive");
    }
}
