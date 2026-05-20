package idusw.sb.dimo202212007.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/errors")
public class ErrorController {
    @GetMapping("/404")
    public String get404() {
        return "errors/404";
    }
    @GetMapping("/blank")
    public String getBlank() {
        return "errors/blank";
    }
}
