package com.project.donationmanagement.controller;

import com.project.donationmanagement.entity.Donation;
import com.project.donationmanagement.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DonationController {
    @Autowired
    private DonationService donationService;

    @PostMapping({"/registerDonation"})
    public Donation registerDonation(@RequestBody Donation donation) {
        return donationService.registerDonation(donation);
    }

    @GetMapping({"/getDonationById/{id}"})
    public Donation getDonationById(@PathVariable String id) {
        return donationService.getDonationById(id);
    }

    @GetMapping({"/getAllDonation"})
    public List<Donation> getAllDonation() {
        return donationService.getAllDonation();
    }

    @GetMapping({"/getAllDonationByDonor/{donorId}"})
    public List<Donation> getAllDonationByDonor(@PathVariable String donorId) {
        return donationService.getAllDonationByDonor(donorId);
    }

    @GetMapping({"/getAllDonationByNgo/{ngoId}"})
    public List<Donation> getAllDonationByNgo(@PathVariable String ngoId) {
        return donationService.getAllDonationByNgo(ngoId);
    }
}
