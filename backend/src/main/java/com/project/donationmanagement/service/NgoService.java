package com.project.donationmanagement.service;

import com.project.donationmanagement.entity.Ngo;
import com.project.donationmanagement.entity.User;
import com.project.donationmanagement.repository.NgoRepository;
import com.project.donationmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NgoService {
    @Autowired
    private NgoRepository ngoRepository;
    @Autowired
    private UserRepository userRepository;
    public Ngo getById(String userName) {
        return ngoRepository.findById(userName).orElse(null);
    }

    public String deleteNgoById(String userName) {
        if(!ngoRepository.existsById(userName)) {
            return userName+" doesn't exists in ngo DB";
        }
        userRepository.deleteById(userName);
        ngoRepository.deleteById(userName);
        return userName+" was deleted";
    }

    public List<Ngo> getAllNgos() {
        return ngoRepository.findAll();
    }

    public Ngo updateNgo(Ngo ngo) {
        User existingUser=userRepository.findById(ngo.getUserName()).orElse(null);
        existingUser.setFirstName(ngo.getFirstName());
        existingUser.setLastName(ngo.getLastName());
        Ngo existingNgo=ngoRepository.findById(ngo.getUserName()).orElse(null);
        existingNgo.setFirstName(ngo.getFirstName());
        existingNgo.setLastName(ngo.getLastName());
        existingNgo.setEmail(ngo.getEmail());
        existingNgo.setAddress(ngo.getAddress());
        userRepository.save(existingUser);
        return ngoRepository.save(existingNgo);
    }
}
