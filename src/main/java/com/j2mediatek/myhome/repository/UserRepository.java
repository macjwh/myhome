package com.j2mediatek.myhome.repository;

import com.j2mediatek.myhome.model.Board;
import com.j2mediatek.myhome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
