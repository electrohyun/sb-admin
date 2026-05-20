package idusw.sb.dimo202212007.controller;

import jakarta.annotation.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/")
    public String goHome() {
        return "redirect:/main/index";
    }

    @GetMapping("/main/index")
    public String goIndex(@Nullable @RequestParam("p") String p, Model model) {
        model.addAttribute("attrname", p);
        return "./main/index";
    }

    @GetMapping("/misc/cards")
    public String goCards() {
        return "./misc/cards";
    }
}
