package com.project.donationmanagement.service;

import com.project.donationmanagement.entity.Donor;
import com.project.donationmanagement.entity.Ngo;
import com.project.donationmanagement.entity.User;
import com.project.donationmanagement.repository.DonorRepository;
import com.project.donationmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorService {
    @Autowired
    private DonorRepository donorRepository;
    @Autowired
    private UserRepository userRepository;
    public Donor getById(String userName) {
        return donorRepository.findById(userName).orElse(null);
    }
    public String deleteDonorById(String userName) {
        if(!donorRepository.existsById(userName)) {
            return userName+" doesn't exists in donor DB";
        }
        donorRepository.deleteById(userName);
        userRepository.deleteById(userName);
        return userName+" was deleted";
    }
    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    public Donor updateDonor(Donor donor) {
        User existingUser=userRepository.findById(donor.getUserName()).orElse(null);
        existingUser.setFirstName(donor.getFirstName());
        existingUser.setLastName(donor.getLastName());
        Donor existingDonor=donorRepository.findById(donor.getUserName()).orElse(null);
        existingDonor.setFirstName(donor.getFirstName());
        existingDonor.setLastName(donor.getLastName());
        userRepository.save(existingUser);
        return donorRepository.save(existingDonor);
    }
}
