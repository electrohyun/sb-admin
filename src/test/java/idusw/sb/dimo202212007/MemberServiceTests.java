package idusw.sb.dimo202212007;

import idusw.sb.dimo202212007.domain.Member;
import idusw.sb.dimo202212007.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MemberServiceTests {
    @Autowired
    private MemberService memberService;

    @Test
    public void readAllByLikePhone() {
        Member member = Member.builder()
                .phone("9000")
                .build();

        List<Member> members = memberService.readAllByLikePhone(member);
        for (Member m : members) {
            System.out.println(m);
        }
    }

    @Test
    public void readAllJPA() {
        List<Member> members = memberService.readAllJpa();
        for (Member member : members) {
            System.out.println(member);
        }
    }
}
