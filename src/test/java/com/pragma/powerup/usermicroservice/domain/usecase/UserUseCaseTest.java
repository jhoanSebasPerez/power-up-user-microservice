package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.MailAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.PersonAlreadyExistsException;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.exceptions.UserHasNotLegalAgeException;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @InjectMocks
    UserUseCase userUseCase;

    @Mock
    IUserPersistencePort userPersistencePort;

    User user;

    @BeforeEach
    void setUp() {
        //Role role = new Role();
        //role.setId(3L);

        user = new User();
        user.setFirstName("john");
        user.setLastName("doe");
        user.setEmail("john@email.com");
        user.setDniNumber("2384092384");
        user.setPhone("+573003789090");
        //user.setRole(role);
        user.setPassword("1234a");
        user.setBirthDate(LocalDate.of(1998, 5, 24));
    }

    @Test
    void saveUserTest() {
        when(userPersistencePort.existsByDniNumber(user.getDniNumber())).thenReturn(false);
        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(false);

        userUseCase.saveOwner(user);

        assertEquals(Constants.OWNER_ROLE_ID, user.getRole().getId());

        verify(userPersistencePort).saveUser(user);
    }

    @Test
    void saveUserWithEmailAlreadyExists() {
        when(userPersistencePort.existsByDniNumber(user.getDniNumber())).thenReturn(false);
        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(true);

        assertThrows(MailAlreadyExistsException.class, () -> {
            userUseCase.saveOwner(user);
        });
    }

    @Test
    void saveUserWithDniNumberExists() {
        when(userPersistencePort.existsByDniNumber(user.getDniNumber())).thenReturn(true);

        assertThrows(PersonAlreadyExistsException.class, () -> {
            userUseCase.saveOwner(user);
        });
    }

    @Test
    void saveUserWithNotLegalAge() {
        user.setBirthDate(LocalDate.of(2010, 12, 11));

        assertThrows(UserHasNotLegalAgeException.class, () -> {
            userUseCase.saveOwner(user);
        });
    }
}