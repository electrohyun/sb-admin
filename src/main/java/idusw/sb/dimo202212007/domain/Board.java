package idusw.sb.dimo202212007.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    private Long bno;
    private String title;
    private String content;
    private Long views;

    private Long writerId;
    private String writerEmail;
    private String writerName;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private int replyCount;
}
