package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.UserHasNotLegalAgeException;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;

import java.time.LocalDateTime;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort personPersistencePort) {
        this.userPersistencePort = personPersistencePort;
    }

    @Override
    public void saveUser(User user) {
        if(!hasLegalAge(user))
            throw new UserHasNotLegalAgeException();
        userPersistencePort.saveUser(user);
    }

    private boolean hasLegalAge(User person){
        LocalDateTime now = LocalDateTime.now();
        return now.getYear() - person.getBirthDate().getYear() >= 18;
    }
}
