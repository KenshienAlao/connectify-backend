package com.connectify.backend.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connectify.backend.dto.auth.login.LoginRequest;
import com.connectify.backend.dto.auth.login.LoginResponse;
import com.connectify.backend.dto.auth.register.RegisterRequest;
import com.connectify.backend.dto.auth.register.RegisterResponse;
import com.connectify.backend.dto.user.UserResponse;
import com.connectify.backend.exception.ExistException;
import com.connectify.backend.exception.UnexpectedException;
import com.connectify.backend.model.user.UserModel;
import com.connectify.backend.repository.auth.RegisterRepository;


@Service
public class AuthService {
    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Transactional
    public RegisterResponse registerUser(RegisterRequest entity) {
        String email = entity.email();
        String contactNumber = entity.contactNumber();

        if (isBlank(email) && isBlank(contactNumber)) {
            throw new UnexpectedException("Email or contact number is required!", "EMAIL_OR_CONTACT_NUMBER_REQUIRED");
        }

        if (!isBlank(email)) {
            if (registerRepository.findByEmail(email).isPresent()) {
                throw new ExistException("Email already exists!", "EMAIL_EXIST");
            }
        }
        

        if (!isBlank(contactNumber)) {
            if (registerRepository.findByContactNumber(contactNumber).isPresent()) {
                throw new ExistException("Contact number already exists!", "CONTACT_NUMBER_EXIST");
            }
        }

        UserModel user = UserModel.builder()
        .firstName(entity.name().firstName())
        .lastName(entity.name().lastName())
        .gender(entity.gender())
        .month(entity.birthday().month())
        .day(entity.birthday().day())
        .year(entity.birthday().year())
        .contactNumber(isBlank(contactNumber) ? null : contactNumber)
        .email(isBlank(email) ? null : email)
        .password(passwordEncoder.encode(entity.password())) // Hash the password
        .build();

        UserModel savedUser = registerRepository.save(user);

        return new RegisterResponse(
            new RegisterResponse.NameResponse(savedUser.getFirstName(), savedUser.getLastName()),
            savedUser.getEmail()
        );
    }
    @Transactional
    public LoginResponse loginUser(LoginRequest entity) {
        String email = entity.email();
        String password = entity.password();
   
        if (isBlank(email) || isBlank(password)) {
            throw new UnexpectedException("Email or password is required!", "EMAIL_OR_PASSWORD_REQUIRED");
        }

        UserModel user = registerRepository.findByEmail(email)
                .orElseThrow(() -> new UnexpectedException("Invalid email or password!", "INVALID_CREDENTIALS"));

        if (!passwordEncoder.matches(password, user.getPassword())) { // compare
            throw new UnexpectedException("Invalid email or password!", "INVALID_CREDENTIALS");
        }

        return new LoginResponse(
                user.getId(),
                "dummy-auth-token", 
                new UserResponse(
                    user.getId(),
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getAvatar()
                )
        );
    }

    private boolean isBlank(String str) {
        return str == null || str.isBlank();
    }

}
