package idusw.sb.dimo202212007.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
    @GetMapping("/list")
    public String getProducts() {
        return "products/list";
    }

    @GetMapping("/cards")
    public String goCards() {
        return "products/cards";
    }

    @GetMapping("/charts")
    public String getCharts() {
        return "products/charts";
    }

    @GetMapping("/utilities-animation")
    public String getUtilitiesAnimation() {
        return "products/utilities-animation";
    }

    @GetMapping("/utilities-border")
    public String getUtilitiesBorder() {
        return "products/utilities-border";
    }

    @GetMapping("/utilities-color")
    public String getUtilitiesColor() {
        return "products/utilities-color";
    }

    @GetMapping("/utilities-other")
    public String getUtilitiesOther() {
        return "products/utilities-other";
    }
}
