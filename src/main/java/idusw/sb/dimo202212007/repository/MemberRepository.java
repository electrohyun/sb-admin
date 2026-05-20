package idusw.sb.dimo202212007.repository;

import idusw.sb.dimo202212007.domain.Member;

import java.util.List;

public interface MemberRepository {
    int insert(Member domain);
    List<Member> selectAll();
    int update (Member domain);
    int delete (Member domain);
    Member selectById(Member domain);
    Member selectByEmailAndPassword(String email, String pw);
    Member selectByEmailAndPassword(Member domain);
    Member updateByEmailAndPw(Member m, String newPw);
    Member updateById(Member m, String newPw);
    int deleteById(Member m);
    List<Member> selectByPhone(String lastFourDigits);
}
