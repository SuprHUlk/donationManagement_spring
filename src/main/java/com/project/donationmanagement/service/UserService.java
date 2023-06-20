package com.project.donationmanagement.service;

import com.project.donationmanagement.entity.Role;
import com.project.donationmanagement.entity.User;
import com.project.donationmanagement.repository.RoleRepository;
import com.project.donationmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void initRoleAndUser() {

        Role ngoRole = new Role();
        ngoRole.setRoleName("ngoRole");
        roleRepository.save(ngoRole);

        Role donorRole = new Role();
        donorRole.setRoleName("donorRole");
        roleRepository.save(donorRole);

        User ngoUser = new User();
        ngoUser.setUserName("ngoUser123");
        ngoUser.setPass(getEncodedPassword("ngoUser@pass"));
        ngoUser.setFirstName("ngoUser");
        ngoUser.setLastName("ngoUser");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(ngoRole);
        ngoUser.setRole(adminRoles);
        userRepository.save(ngoUser);
    }

    public User registerNewUser(User user) {
        if(userRepository.existsById(user.getUserName())) {
            throw new IllegalArgumentException("Username not unique");
        }
        user.setPass(getEncodedPassword(user.getPass()));
        return userRepository.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
