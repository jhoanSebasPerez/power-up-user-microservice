package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IUserPersistencePort {
    void saveUser(User user);

    boolean existsByDniNumber(String dniNumber);

    boolean existsByEmail(String email);
}
