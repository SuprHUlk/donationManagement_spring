package com.project.donationmanagement.repository;

import com.project.donationmanagement.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, String> {
    List<Donation> findByDonorId(String donorId);

    List<Donation> findByNgoId(String ngoId);
}
