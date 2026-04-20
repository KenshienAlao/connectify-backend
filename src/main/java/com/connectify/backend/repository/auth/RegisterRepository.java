package com.connectify.backend.repository.auth;

import com.connectify.backend.model.user.UserModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByEmail(String email);
    Optional<UserModel> findByContactNumber(String contactNumber); 
}
        