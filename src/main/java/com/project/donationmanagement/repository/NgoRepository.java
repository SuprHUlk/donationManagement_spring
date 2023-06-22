package com.project.donationmanagement.repository;

import com.project.donationmanagement.entity.Ngo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NgoRepository extends JpaRepository<Ngo, String> {
}
