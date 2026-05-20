package idusw.sb.dimo202212007.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="m2027")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @Column(length = 30, nullable = false)
    private String pw;

    @Column(length = 30, nullable = false)
    private String fullname;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String address;
}
