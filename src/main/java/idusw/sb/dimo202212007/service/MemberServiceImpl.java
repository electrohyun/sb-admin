package idusw.sb.dimo202212007.service;

import idusw.sb.dimo202212007.domain.Member;
import idusw.sb.dimo202212007.entity.MemberEntity;
import idusw.sb.dimo202212007.repository.MemberRepository;
import idusw.sb.dimo202212007.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;
    private UserRepository userRepository;
    public MemberServiceImpl(MemberRepository memberRepository, UserRepository userRepository) { // 생성자 주입
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Member> readAllJpa() {
        List<MemberEntity> listMemberEntity = userRepository.findAll();
        List<Member> members = new ArrayList<>();
        for (MemberEntity memberEntity : listMemberEntity) {
            Member member = Member.builder()
                    .id(memberEntity.getId())
                    .email(memberEntity.getEmail())
                    .fullname(memberEntity.getFullname())
                    .phone(memberEntity.getPhone())
                    .address(memberEntity.getAddress())
                    .build();
            members.add(member);
        }
        return members;
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
