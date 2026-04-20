package com.connectify.backend.repository.auth;

import com.connectify.backend.model.auth.Register;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Integer> {
    Optional<Register> findByEmail(String email);
    Optional<Register> findByContactNumber(String contactNumber); 
}
        