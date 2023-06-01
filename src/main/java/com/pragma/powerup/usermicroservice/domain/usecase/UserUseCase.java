package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.MailAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.PersonAlreadyExistsException;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.UserHasNotLegalAgeException;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;

import java.time.LocalDateTime;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort personPersistencePort) {
        this.userPersistencePort = personPersistencePort;
    }

    private User saveUser(User user, Long idRole) {
        if(userPersistencePort.existsByDniNumber(user.getDniNumber()))
            throw new PersonAlreadyExistsException();
        if(userPersistencePort.existsByEmail(user.getEmail()))
            throw new MailAlreadyExistsException();
        if(!hasLegalAge(user))
            throw new UserHasNotLegalAgeException();

        user.setRole(new Role());
        user.getRole().setId(idRole);
        return userPersistencePort.saveUser(user);
    }


    @Override
    public User saveOwner(User user) {
        return saveUser(user, Constants.OWNER_ROLE_ID);
    }

    @Override
    public User saveEmployee(User user) {
        return saveUser(user, Constants.EMPLOYEE_ROLE_ID);
    }

    @Override
    public User saveClient(User user) {
        return saveUser(user, Constants.USER_ROLE_ID);
    }

    @Override
    public boolean isOwner(String userDni) {
        return userPersistencePort.isOwner(userDni);
    }

    private boolean hasLegalAge(User person){
        LocalDateTime now = LocalDateTime.now();
        return now.getYear() - person.getBirthDate().getYear() >= 18;
    }

}
