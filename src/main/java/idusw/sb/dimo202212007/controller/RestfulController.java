package idusw.sb.dimo202212007.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/api/v1"))
public class RestfulController {
    @GetMapping("/members")
    public String getMemberList() {
        return "<h1>Member List</h1>";
    }
}
