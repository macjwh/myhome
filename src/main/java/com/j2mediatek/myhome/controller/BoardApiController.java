package com.j2mediatek.myhome.controller;

import com.j2mediatek.myhome.model.Board;
import com.j2mediatek.myhome.repository.BoardRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardApiController {
    private final BoardRepository repository;

    BoardApiController(BoardRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/boards")
    List<Board> all(@RequestParam(required = false, defaultValue="") String title,
                    @RequestParam(required = false, defaultValue="") String content) {
        if (!StringUtils.hasText(title) && !StringUtils.hasText(content)) {
            return repository.findAll();
        } else {
            return repository.findByTitleOrContent(title, content);
        }
    }

    @PostMapping("/boards")
    Board newBoard(@RequestBody Board newBoard) {
        return repository.save(newBoard);
    }

    // Single item
    @GetMapping("/boards/{id}")
    Board one(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/boards/{id}")
    Board replaceEmployee(@RequestBody Board newBoard, @PathVariable Long id) {

        return repository.findById(id)
                .map(board -> {
                    board.setTitle(newBoard.getTitle());
                    board.setContent(newBoard.getContent());
                    return repository.save(board);
                })
                .orElseGet(() -> {
                    newBoard.setId(id);
                    return repository.save(newBoard);
                });
    }

    @DeleteMapping("/boards/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
