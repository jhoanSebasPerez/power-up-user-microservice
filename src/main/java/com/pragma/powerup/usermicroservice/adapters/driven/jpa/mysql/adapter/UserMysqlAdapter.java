package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UserNotFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserMysqlAdapter implements IUserPersistencePort {
    private final IUserRepository personRepository;
    private final IUserEntityMapper personEntityMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity usersaved = personRepository.save(personEntityMapper.toEntity(user));
        return personEntityMapper.toUser(usersaved);
    }


    @Override
    public boolean existsByDniNumber(String dniNumber){
        return personRepository.existsByDniNumber(dniNumber);
    }

    @Override
    public boolean existsByEmail(String email){
        return personRepository.existsByEmail(email);
    }

    @Override
    public boolean isOwner(String userDni) {
        UserEntity user = personRepository.findByDniNumber(userDni).orElse(null);

        if(user == null)
            throw new UserNotFoundException();

        return user.getRole().getId().equals(Constants.OWNER_ROLE_ID);
    }
}
