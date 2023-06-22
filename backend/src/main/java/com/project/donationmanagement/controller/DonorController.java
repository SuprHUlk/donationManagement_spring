package com.project.donationmanagement.controller;

import com.project.donationmanagement.entity.Donor;
import com.project.donationmanagement.entity.Ngo;
import com.project.donationmanagement.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DonorController {
    @Autowired
    private DonorService donorService;
    @GetMapping({"/getDonorById/{userName}"})
    public Donor getById(@PathVariable String userName) {
        return donorService.getById(userName);
    }

    @GetMapping({"/getAllDonors"})
    public List<Donor> getAllNgos() {
        return donorService.getAllDonors();
    }

    @DeleteMapping({"/deleteDonor/{userName}"})
    public String deleteDonorById(@PathVariable String userName) {
        return donorService.deleteDonorById(userName);
    }

    @PutMapping({"/updateDonor"})
    public Donor updateNgo(@RequestBody Donor donor) {
        return donorService.updateDonor(donor);
    }
}
