package com.j2mediatek.myhome.controller;

import com.j2mediatek.myhome.model.Board;
import com.j2mediatek.myhome.model.User;
import com.j2mediatek.myhome.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserApiController {
    private final UserRepository repository;

    UserApiController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    List<User> all() {
        List<User> users = repository.findAll();
        log.debug("getBoards().size() 호출전");
        log.debug("getBoards().size() : {}", users.get(0).getBoards().size());
        log.debug("getBoards().size() 호출후");
        return users;
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    // Single item
    @GetMapping("/users/{id}")
    User findUser(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(user -> {
                    user.setBoards(newUser.getBoards());
/* orphanRemoval = true로 설정할 경우 기존 데이터를 clear하고 레코드를 추가를 해야한다.
                    user.getBoards().clear();
                    user.getBoards().addAll(newUser.getBoards());
*/
                    for (Board board : user.getBoards()) {
                        board.setUser(user);
                    }
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
