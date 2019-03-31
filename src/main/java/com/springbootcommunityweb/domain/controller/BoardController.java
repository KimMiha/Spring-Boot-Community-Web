package com.springbootcommunityweb.domain.controller;

import com.springbootcommunityweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
public class BoardController {


  private final BoardService boardService;

  public BoardController(BoardService boardService) {
    this.boardService = boardService;
  }

  @GetMapping({"", "/"})
  public String board(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {  //필수값으로 idx를 받는다. 기본값 0 으로 조회시 board값은 null로 반
    model.addAttribute("board", boardService.findBoardByIdx(idx));
    return "board/form";
  }

  @GetMapping("/list")
  public String list(@PageableDefault Pageable pageable, Model model) {  //@PageableDefault 어노테이션의 파라미터인 size, sort, direction등을 사용하여 페이징처리에 대한 규약을 정의할수있다.
    model.addAttribute("boardList", boardService.findBoardList(pageable));
    return "board/list";  //src/resources/templates를 기준으로 데이터를 바인딩할 타기스이 뷰 경로 지정
  }
}
