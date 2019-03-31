package com.springbootcommunityweb.service;

import com.springbootcommunityweb.domain.Board;
import com.springbootcommunityweb.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

  private final BoardRepository boardRepository;

  public BoardService(BoardRepository boardRepository){
    this.boardRepository = boardRepository;
  }

  public Page<Board> findBoardList(Pageable pageable){
    //기본 페이지 크기안 10으로 새로운 PageRequest객체를 만들어 페이징 처리된 게시글 리스트 반환
    pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());
    return boardRepository.findAll(pageable);
  }

  public Board findBoardByIdx(Long idx){
    return boardRepository.findById(idx).orElse(new Board()); //board의 idx값을 사용하여 board 객체 반환
  }
}
