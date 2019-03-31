package com.springbootcommunityweb;

import com.springbootcommunityweb.domain.Board;
import com.springbootcommunityweb.domain.User;
import com.springbootcommunityweb.domain.enums.BoardType;
import com.springbootcommunityweb.repository.BoardRepository;
import com.springbootcommunityweb.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
                                                                  // 애플리케이션 컨텍스트 : 빈의 생성과 관계설정같은 제어를 담당하는 IOC 객체를 빈 팩토리라 부르며 이러한 빈 팩토리를 더확장한 개념
@RunWith(SpringRunner.class)  //어노테이션에 정의된 클래스를 호출한다. JUnit의 확장 기능을 지정하여 각 테스트 시 독립적인 애플리케이션 컨텍스트를 보장한다.
@DataJdbcTest //스프링부트에서 JPA테스트를 위함. 첫 설계시 엔티티 간의 관계 설정 및 기능 테스트를 가능하게 도와줌. 테스트가 끝날때마다 자동 롤백을 해주어 편리한 테스트 가능.
public class JpaMappingTest {
  private final String boardTestTitle = "test";
  private final String email = "test@gmail.com";

  @Autowired
  UserRepository userRepository;

  @Autowired
  BoardRepository boardRepository;

  @Before //각 테스트가 실행되기 전에 실행될 메서드 선언
  public void init(){
    User user = userRepository.save(User.builder()
            .name("havi")
            .password("test")
            .email(email)
            .createdDate(LocalDateTime.now())
            .build()
    );

    boardRepository.save(Board.builder()
            .title(boardTestTitle)
            .subTitle("subtitle")
            .content("content bla bla")
            .boardType(BoardType.free)
            .createdDate(LocalDateTime.now())
            .updatedDate(LocalDateTime.now())
            .user(user).build()
    );

  }

  @Test //실제 테스트가 진행될 메서드 선언
  public void 제대로_생성됐는지_테스트(){
    User user = userRepository.findByEmail(email);  //init()에서 저장된 user를 email로 조회
    assertThat(user.getName(), is("havi"));
    assertThat(user.getPassword(), is("test"));
    assertThat(user.getEmail(), is(email));

    Board board = boardRepository.findByUser(user);
    assertThat(board.getTitle(), is(boardTestTitle));
    assertThat(board.getSubTitle(), is("subtitle"));
    assertThat(board.getContent(), is("content bla bla"));
    assertThat(board.getBoardType(), is(BoardType.free));
  }
}
