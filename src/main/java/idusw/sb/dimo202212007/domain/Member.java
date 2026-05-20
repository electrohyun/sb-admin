package idusw.sb.dimo202212007.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Member {
    private int id;
    private String email;
    private String fullname;
    private String pw;
    private String phone;
    private String address;
}