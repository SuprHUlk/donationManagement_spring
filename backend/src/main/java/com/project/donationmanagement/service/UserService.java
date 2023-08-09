package com.project.donationmanagement.service;

import com.project.donationmanagement.entity.Donor;
import com.project.donationmanagement.entity.Ngo;
import com.project.donationmanagement.entity.Role;
import com.project.donationmanagement.entity.User;
import com.project.donationmanagement.repository.DonorRepository;
import com.project.donationmanagement.repository.NgoRepository;
import com.project.donationmanagement.repository.RoleRepository;
import com.project.donationmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private NgoRepository ngoRepository;
    @Autowired
    private DonorRepository donorRepository;
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

    public User registerNewUser(User user) throws Exception {
        if(userRepository.existsById(user.getUserName())) {
            throw new IllegalArgumentException("Username is not unique");
        }
        if(user.getRoleName().equals("ngoRole")) {
            addToNgoRepository(user);
        }
        else if(user.getRoleName().equals("donorRole")) {
            addToDonorRepository(user);
        }

        user.setPass(getEncodedPassword(user.getPass()));
        return userRepository.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void addToNgoRepository(User user) throws Exception {
        Ngo ngoUser=new Ngo();
        ngoUser.setUserName(user.getUserName());
        ngoUser.setFirstName(user.getFirstName());
        ngoUser.setLastName(user.getLastName());
        try {
            ngoRepository.save(ngoUser);
        }
        catch(Exception e) {
            throw new Exception("Some error occurred while adding to ngoRepository");
        }
    }

    public void addToDonorRepository(User user) throws Exception {
        Donor donorUser=new Donor();
        donorUser.setUserName(user.getUserName());
        donorUser.setFirstName(user.getFirstName());
        donorUser.setLastName(user.getLastName());
        try {
            donorRepository.save(donorUser);
        }
        catch(Exception e) {
            throw new Exception("Some error occurred while adding to ngoRepository");
        }
    }
}
