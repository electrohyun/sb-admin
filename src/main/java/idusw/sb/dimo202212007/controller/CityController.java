package idusw.sb.dimo202212007.controller;

import idusw.sb.dimo202212007.domain.City;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.sql.DataSource;
import java.sql.*;

@Controller
@RequestMapping("/city")
public class CityController {
    private final DataSource dataSource;

    // Autowired or 생성자 주입
    public CityController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/")
    public String getCities() {
        return "city/cities";
    }

    @GetMapping("/{id}")
    public String getCity(@PathVariable int id, Model model) {
        String sql = "SELECT * FROM city WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    model.addAttribute("city", City.builder()
                            .id(rs.getInt(1))
                            .name(rs.getString(2))
                            .population(rs.getInt(5))
                            .build());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
        return "city/city";
    }
}