package com.springbootcommunityweb.domain;

import com.springbootcommunityweb.domain.enums.BoardType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
@Getter
public class Board implements Serializable {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)    //기본 키가 자동으로 할당되도록 설정하는 어노테이션. 기본키 할당 전략을 선택할 수 있는데, 키 생성을 데이터베이스에 위임하는 IDENTITY 전략 사용
  private Long idx;

  @Column
  private String title;

  @Column
  private String subTitle;

  @Column
  private String content;

  @Column
  @Enumerated(EnumType.STRING)  //enum타입 매핑용 어노테이션. 자바enum형과 디비 데이터 변환을 지원한다. 실제로 자바 enum형이지만 디비의 string형으로 변환하여 저장하겠다고 선언한 것.
  private BoardType boardType;

  @Column
  private LocalDateTime createdDate;  //자바8에 추가된 기능. 대부분 날짜기능제공

  @Column
  private LocalDateTime updatedDate;

  @OneToOne(fetch = FetchType.LAZY) //도메인 Board와 Board가 필드값으로 갖고있는 user도메인의 PK인 user_idx 값이 저장된다. fetch는 eager와 lazy 두종류가 있는데 eager는 Board도메인을 조회할땐 즉시 관련 User객체를 함께 조회한다는 뜻이고 후자는 시점이 아닌 객체가 실제로 사용될 때 조회한다는 뜻이다.
  private User user;

  @Builder
  public Board(String title, String subTitle, String content, BoardType boardType, LocalDateTime createdDate, LocalDateTime updatedDate, User user){
    this.title = title;
    this.subTitle = subTitle;
    this.content = content;
    this.boardType = boardType;
    this.createdDate = createdDate;
    this.updatedDate = updatedDate;
    this.user = user;
  }
}
