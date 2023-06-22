package com.project.donationmanagement.controller;

import com.project.donationmanagement.entity.Ngo;
import com.project.donationmanagement.entity.User;
import com.project.donationmanagement.service.NgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NgoController {
    @Autowired
    private NgoService ngoService;

    @GetMapping({"/getNgoById/{userName}"})
    public Ngo getById(@PathVariable String userName) {
        return ngoService.getById(userName);
    }

    @GetMapping({"/getAllNgos"})
    public List<Ngo> getAllNgos() {
        return ngoService.getAllNgos();
    }

    @DeleteMapping({"/deleteNgo/{userName}"})
    public String deleteNgoById(@PathVariable String userName) {
        return ngoService.deleteNgoById(userName);
    }

    @PutMapping({"/updateNgo"})
    public Ngo updateNgo(@RequestBody Ngo ngo) {
        return ngoService.updateNgo(ngo);
    }
}
