package idusw.sb.dimo202212007;


import idusw.sb.dimo202212007.domain.Member;
import idusw.sb.dimo202212007.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MemberRepositoryTests {
    @Autowired
    MemberRepository memberRepository;

    @Test
    void saveMember() {
        Member member = Member.builder()
                .email("id7@induk.ac.kr")
                .pw("cometrue")
                .build();
        memberRepository.insert(member);
        findAllMember();
    }
    // 전체 회원 조회
    @Test
    void findAllMember() {
        List<Member> members = memberRepository.selectAll();
        members.forEach(member->{System.out.println(member.toString());});
    }

    // 회원 정보 조회
    @Test
    void findByEmail() {
        Member member = Member.builder().email("id1@induk.ac.kr").build();
        Member retMember = memberRepository.selectById(member);
        System.out.println(retMember.toString());
        // Assertions.assertEquals(retMember.getFirstName(), "2");
    }
    // 회원 정보 수정
    @Test
    void updateMember() {
        Member member = Member.builder()
                .email("id1@induk.ac.kr")
                .pw("dream")
                .fullname("11")
                .build();
        memberRepository.update(member);
        findAllMember();
    }

    // 회원 정보 삭제
    @Test
    void deleteMemberById() {
        Member member = Member.builder()
                .email("id4@induk.ac.kr")
                .pw("cometrue")
                .build();
        memberRepository.delete(member);
        findAllMember();
    }

    @Test
    void updateMemberById() {
        Member member = Member.builder()
                .id(1)
                .build();
        String newPw = "updatedPassword123";
        Member updated = memberRepository.updateById(member, newPw);
        if(updated != null) {
            System.out.println(updated.toString());
        }
    }

    @Test
    void deleteMemberRealById() {
        Member member = Member.builder()
                .id(1)
                .build();
        int result = memberRepository.deleteById(member);
        System.out.println(result);
        findAllMember();
    }

    @Test
    void findByPhone() {
        String lastFour = "1234";
        List<Member> members = memberRepository.selectByPhone(lastFour);
        if(members != null) {
            members.forEach(m -> System.out.println(m.toString()));
        }
    }

}
