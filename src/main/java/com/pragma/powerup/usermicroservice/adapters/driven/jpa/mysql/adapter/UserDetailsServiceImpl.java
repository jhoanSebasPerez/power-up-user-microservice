package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PrincipalUser;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String documentID) throws UsernameNotFoundException {
        UserEntity usuario = userRepository.findByEmail(documentID).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (usuario.getRole() == null) {
            throw new UsernameNotFoundException("User not found with documentID: " + documentID);
        }

        List<RoleEntity> roles = new ArrayList<>();
        roles.add(usuario.getRole());

        return PrincipalUser.build(usuario, roles);
    }
}
