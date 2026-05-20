package idusw.sb.dimo202212007.service;

import idusw.sb.dimo202212007.domain.Member;
import idusw.sb.dimo202212007.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository memberRepository) { // 생성자 주입
        this.memberRepository = memberRepository;
    }

    @Override
    public int create(Member domain) {
        int ret = memberRepository.insert(domain);
        return ret;
    }

    @Override
    public Member readById(Member domain) {
        Member member = memberRepository.selectById(domain);
        return member;
    }

    @Override
    public List<Member> readAll() {
        List<Member> members = memberRepository.selectAll();
        return members;
    }

    @Override
    public int update(Member domain) {
        System.out.println(domain);
        Member member = memberRepository.selectById(domain);
        if (member != null)
            return memberRepository.update(domain);
        else
            return 0;
    }

    @Override
    public void delete(Member domain) {
        memberRepository.delete(domain);
    }

    @Override
    public Member readByEmailAndPassword(Member domain) {
        Member member = memberRepository.selectByEmailAndPassword(domain);
        return member;
    }

    @Override
    public Member updateByEmailAndPw(Member m, String newPw) {
        return memberRepository.updateByEmailAndPw(m, newPw);
    }

    @Override
    public Member updateById(Member m, String newPw)  {
        return memberRepository.updateById(m, newPw);
    }

    @Override
    public int deleteById(Member m) {
        return memberRepository.deleteById(m);
    }

    @Override
    public List<Member> readByPhone(String lastFourDigits) {
        return memberRepository.selectByPhone(lastFourDigits);
    }

    @Override
    public List<Member> readAllByLikePhone(Member m) {
        List<Member> members = memberRepository.selectAllByLikePhone(m);
        return members;
    }
}
