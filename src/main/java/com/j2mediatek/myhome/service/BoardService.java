package com.j2mediatek.myhome.service;

import com.j2mediatek.myhome.model.Board;
import com.j2mediatek.myhome.model.User;
import com.j2mediatek.myhome.repository.BoardRepository;
import com.j2mediatek.myhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public Board save(String username, Board board) {
        User user = userRepository.findByUsername(username);
        board.setUser(user);
        return boardRepository.save(board);
    }
}
