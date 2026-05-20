package idusw.sb.dimo202212007.controller;

import idusw.sb.dimo202212007.domain.Member;
import idusw.sb.dimo202212007.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    // Spring 4.3이후 생략가능
    @Autowired // DI(Dependency Injection) - Constructor 활용
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PutMapping("/update")
    public String update(@ModelAttribute("member") Member dto, Model model, HttpSession session) {
        int ret = memberService.update(dto);
        if (ret >= 1) {
            return "redirect:/main/index";
        } else {
            return "/errors/blank";
        }
    }

    @GetMapping("/info")
    public String getInfo(@RequestParam("id") String id, Model model) {
        Member member = memberService.readById(Member.builder()
                .id(Integer.parseInt(id))
                .build());
        model.addAttribute("dto", member);
        return "/members/info";
    }

    @GetMapping("/reg-form")
    public String getRegForm(Model model) {
        model.addAttribute("dto", Member.builder().build());
        return "/members/register";
    }


    @PostMapping("/register")
    public String postRegister(@ModelAttribute("dto") Member dto, Model model) {
        System.out.println(dto.getEmail());

        memberService.create(dto);
        model.addAttribute("dto", dto);

        return "/main/index";
    }


    @GetMapping("/search")
    public String searchByPhone(@RequestParam("phone") String phone, Model model) {
        // 서비스의 readByPhone 호출
        List<Member> members = memberService.readByPhone(phone);

        // 결과를 모델에 담아 다시 페이지로 전달
        model.addAttribute("members", members);

        // 로그인 페이지에 정보를 담은 객체(dto)가 필요하다면 함께 추가
        model.addAttribute("dto", Member.builder().build());

        return "members/login"; // 현재 파일의 경로
    }

    @GetMapping("/login-form")
    public String getLoginForm(Model model) {
        model.addAttribute("dto", Member.builder().build());
        return "/members/login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute("dto") Member dto, Model model, HttpSession session) {
        System.out.println(dto.getEmail());
        System.out.println(dto.getPw());

        Member member = memberService.readByEmailAndPassword(dto);

        if (member != null) {
            session.setAttribute("dto", member);
//            model.addAttribute("dto", member);
            return "/main/index";
        } else {
//            model.addAttribute("dto", dto);
//            model.addAttribute("msg", "이메일 또는 비밀번호가 일치하지 않습니다.");
            return "/errors/404";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/forgot-form")
    public String getForgotForm() {
        return "/members/forgot-password"; // templates/members/forgot-password.html 을 찾아감
    }

    @PostMapping("/update-pw")
    public String updatePassword(
            @RequestParam("email") String email,
            @RequestParam("pw") String pw,
            @RequestParam("newPw") String newPw,
            Model model) {

        Member m = Member.builder()
                .email(email)
                .pw(pw)
                .build();

        Member result = memberService.updateByEmailAndPw(m, newPw);

        if (result != null) {
            return "redirect:/members/login-form";
        } else {
            model.addAttribute("msg", "정보가 일치하지 않습니다.");
            return "/members/forgot-password"; // 다시 폼으로 (경로 확인!)
        }
    }

    @GetMapping("/list")
    public String getList(Model model) {
        List<Member> members = memberService.readAll();
        model.addAttribute("members", members);
        System.out.println(members);
        return "/members/list";
    }

    @GetMapping("/buttons")
    public String getButtons() {
        return "/members/buttons";
    }
}
