package idusw.sb.dimo202212007.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "b_202212007")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;
    private String content;
    private Long views;
    private String block;

    @ManyToOne
    private MemberEntity writer;
}
