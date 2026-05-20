package idusw.sb.dimo202212007.repository;

import idusw.sb.dimo202212007.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemberRepositoryImpl(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

    @Override
    public int insert(Member domain) {
        String sql = "INSERT INTO member(email, pw, fullname, phone, address) VALUES(?,?,?,?,?)";
        try {
            return jdbcTemplate.update(sql, domain.getEmail(), domain.getPw(), domain.getFullname(), domain.getPhone(), domain.getAddress());
        } catch (Exception e) {
            System.err.println("insert 에러: " + e.getMessage());
            return 0;
        }
    }


//    @Override
//    public Member selectById(Member domain) {
//        String sql = "SELECT * FROM member WHERE email=?";
//        List<Member> members = jdbcTemplate.query(sql, (rs, i) ->
//                        Member.builder()
//                                .email(rs.getString("email"))
//                                .pw(rs.getString("pw"))
//                                .fullname(rs.getString("fullname"))
//                                .build()
//                , domain.getEmail());
//        System.out.println(members);
//        return members.isEmpty() ? null : members.get(0);
//    }

    @Override
    public Member selectById(Member m) {
        String sql = "SELECT * FROM member WHERE id = ?";
        try {
            Member member = jdbcTemplate.queryForObject(
                    sql,
                    (rs, i) -> Member.builder()
                            .id(rs.getInt("id"))
                            .email(rs.getString("email"))
                            .fullname(rs.getString("fullname"))
                            .pw(rs.getString("pw"))
                            .phone(rs.getString("phone"))
                            .address(rs.getString("address"))
                            .build(),
                    m.getId()
            );
            return member;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Member selectById(int id) {
        String sql = "SELECT * FROM member WHERE id = ?";
        List<Member> members = jdbcTemplate.query(sql, (rs, i) ->
                        Member.builder()
                                .id(rs.getInt("id"))
                                .email(rs.getString("email"))
                                .pw(rs.getString("pw"))
                                .fullname(rs.getString("fullname"))
                                .build()
                , id);
        return members.isEmpty() ? null : members.get(0);
    }

    @Override
    public List<Member> selectAll() {
        String sql = "SELECT * FROM member";
        return jdbcTemplate.query(sql, (rs, i) ->
                Member.builder()
                        .id(rs.getInt("id"))
                        .email(rs.getString("email"))
                        .pw(rs.getString("pw")) // password -> pw 수정
                        .fullname(rs.getString("fullname")) // fullame -> fullname 수정
                        .phone(rs.getString("phone"))
                        .address(rs.getString("address"))
                        .build()
        );
    }

    @Override
    public int update(Member domain) {
//        String sql = "UPDATE member SET fullname=?, phone=?, address=? WHERE email=? AND pw=?";
        String sql = "UPDATE member SET fullname=?, phone=?, address=? WHERE id=?";
        try {
            return jdbcTemplate.update(sql, domain.getFullname(), domain.getPhone(),
                    domain.getAddress(), domain.getId());
        } catch (Exception e) {
            System.err.println("Update 에러: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(Member domain) {
        String sql = "DELETE FROM member WHERE email=? AND pw=?";
        try {
            return jdbcTemplate.update(sql, domain.getEmail(), domain.getPw());
        } catch (Exception e) {
            System.err.println("Delete 에러: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public Member selectByEmailAndPassword(String email, String pw) {
        String sql = "SELECT * FROM member WHERE email = ? AND pw = ?";
        try {
            List<Member> members = jdbcTemplate.query(sql, (rs, i) ->
                            Member.builder()
                                    .id(rs.getInt("id"))
                                    .email(rs.getString("email"))
                                    .pw(rs.getString("pw"))
                                    .fullname(rs.getString("fullname")) // fullame -> fullname 수정
                                    .phone(rs.getString("phone"))
                                    .address(rs.getString("address"))
                                    .build()
                    , email, pw);
            return members.isEmpty() ? null : members.get(0);
        } catch (Exception e) {
            System.err.println("selectByEmailAndPassword 조회 에러: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Member selectByEmailAndPassword(Member domain) {
        return selectByEmailAndPassword(domain.getEmail(), domain.getPw());
    }

    @Override
    public Member updateByEmailAndPw(Member m, String newPw) {
        String sql = "UPDATE member SET pw = ? WHERE email = ? AND pw = ?";
        try {
            int result = jdbcTemplate.update(sql, newPw, m.getEmail(), m.getPw());

            if (result > 0) {
                System.out.println("암호 수정");
                // 수정 성공 후, 바뀐 비밀번호(newPw)로 조회해서 리턴
                return selectByEmailAndPassword(m.getEmail(), newPw);
            }
        } catch (Exception e) {
            System.err.println("updateByEmailAndPw 실행 에러: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Member updateById(Member m, String newPw) {
        String sql = "UPDATE member SET pw = ? WHERE id = ?";
        try {
            int result = jdbcTemplate.update(sql, newPw, m.getId());

            if (result > 0) {
                return selectById(m.getId());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public int deleteById(Member m) {
        String sql = "DELETE FROM member WHERE id = ?";
        try {
            return jdbcTemplate.update(sql, m.getId());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Member> selectByPhone(String lastFourDigits) {
        // 전화번호 뒷자리 4자리로 끝나는 데이터 검색 (%nnnn)
        String sql = "SELECT * FROM member WHERE phone LIKE ?";
        String searchPattern = "%" + lastFourDigits;

        try {
            return jdbcTemplate.query(sql, (rs, i) ->
                            Member.builder()
                                    .id(rs.getInt("id"))
                                    .email(rs.getString("email"))
                                    .pw(rs.getString("pw"))
                                    .fullname(rs.getString("fullname"))
                                    .phone(rs.getString("phone"))
                                    .build()
                    , searchPattern);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Member> selectAllByLikePhone(Member m) {
        String sql = "SELECT * FROM member WHERE phone LIKE ?";
        try {
            List<Member> memberList = jdbcTemplate.query(
                    sql,
                    (rs, i) -> Member.builder()
                            .id(rs.getInt("id"))
                            .email(rs.getString("email"))
                            .fullname(rs.getString("fullname"))
                            .phone(rs.getString("phone"))
                            .address(rs.getString("address"))
                            .build(),
                    "%" + m.getPhone()
            );
            return memberList;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
