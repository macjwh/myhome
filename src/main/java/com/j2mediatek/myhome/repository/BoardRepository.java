package com.j2mediatek.myhome.repository;

import com.j2mediatek.myhome.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Null;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTitle(String title);
    List<Board> findByTitleIsNull();
    List<Board> findByTitleStartingWith(String title);
    List<Board> findByTitleEndingWith(String title);
    List<Board> findByTitleOrderByTitleDesc(String title);
    List<Board> findByTitleOrContent(String title, String content);
    Page<Board> findAll(Pageable pageable);
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
