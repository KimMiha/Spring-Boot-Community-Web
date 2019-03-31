package com.springbootcommunityweb.repository;

import com.springbootcommunityweb.domain.Board;
import com.springbootcommunityweb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
  Board findByUser(User user);
}
