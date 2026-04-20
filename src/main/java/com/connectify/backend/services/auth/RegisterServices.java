package com.connectify.backend.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.connectify.backend.dto.auth.register.RegisterRequest;
import com.connectify.backend.dto.auth.register.RegisterResponse;
import com.connectify.backend.exception.ExistException;
import com.connectify.backend.exception.UnexpectedException;
import com.connectify.backend.model.auth.Register;
import com.connectify.backend.repository.auth.RegisterRepository;


@Service
public class RegisterServices {
    @Autowired
    private RegisterRepository registerRepository;
    
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

        Register user = Register.builder()
        .firstName(entity.name().firstName())
        .lastName(entity.name().lastName())
        .gender(entity.gender())
        .month(entity.birthday().month())
        .day(entity.birthday().day())
        .year(entity.birthday().year())
        .contactNumber(isBlank(contactNumber) ? null : contactNumber)
        .email(isBlank(email) ? null : email)
        .password(entity.password())
        .build();

        Register savedUser = registerRepository.save(user);

        return new RegisterResponse(
            new RegisterResponse.NameResponse(savedUser.getFirstName(), savedUser.getLastName()),
            savedUser.getEmail()
        );
    }

    private boolean isBlank(String str) {
        return str == null || str.isBlank();
    }
}
