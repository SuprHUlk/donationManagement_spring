package com.project.donationmanagement.service;

import com.project.donationmanagement.entity.Donation;
import com.project.donationmanagement.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {
    @Autowired
    private DonationRepository donationRepository;

    public Donation registerDonation(Donation donation) {
        return donationRepository.save(donation);
    }

//    public String deleteDonation(String id) throws IllegalArgumentException {
//        if()
//        if(donationRepository.existsById(id)) {
//            donationRepository.deleteById(id);
//            return id+" has been removed";
//        }
//        else {
//            throw new IllegalArgumentException(id+" doesn't exist");
//        }
//    }

    public Donation getDonationById(String id) {
        return donationRepository.findById(id).orElse(null);
    }

    public List<Donation> getAllDonation() {
        return donationRepository.findAll();
    }

    public List<Donation> getAllDonationByDonor(String donorId) {
        return donationRepository.findByDonorId(donorId);
    }

    public List<Donation> getAllDonationByNgo(String ngoId) {
        return donationRepository.findByNgoId(ngoId);
    }
}
