package com.j2mediatek.myhome.service;

import com.j2mediatek.myhome.model.Role;
import com.j2mediatek.myhome.model.User;
import com.j2mediatek.myhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role role = new Role();
        role.setId(1l); // ROLE_USER(default);

        user.getRoles().add(role);
        return userRepository.save(user);
    }
}
