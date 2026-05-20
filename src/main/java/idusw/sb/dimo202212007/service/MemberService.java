package idusw.sb.dimo202212007.service;

import idusw.sb.dimo202212007.domain.Member;

import java.util.List;

public interface MemberService {
    int create(Member domain);
    Member readById(Member domain);
    List<Member> readAll();
    int update(Member domain);
    void delete(Member domain);
    Member readByEmailAndPassword(Member domain);
    Member updateByEmailAndPw(Member m, String newPw);
    Member updateById(Member m, String newPw);
    int deleteById(Member m);
    List<Member> readByPhone(String lastFourDigits);
}