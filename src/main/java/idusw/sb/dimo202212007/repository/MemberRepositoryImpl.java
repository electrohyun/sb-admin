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
        String sql = "insert into member(email, pw, fullname, phone, address) values(?,?,?,?,?)";
        try {
            return jdbcTemplate.update(sql, domain.getEmail(), domain.getPw(), domain.getFullname(), domain.getPhone(), domain.getAddress());
        } catch (Exception e) {
            System.err.println("insert 에러: " + e.getMessage());
            return 0;
        }
    }


//    @Override
//    public Member selectById(Member domain) {
//        String sql = "select * from member where email=?";
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
        String sql = "select * from tbl_member where id = ?";
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
        String sql = "select * from member where id = ?";
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
        String sql = "select * from member";
        return jdbcTemplate.query(sql, (rs, i) ->
                Member.builder()
                        .id(rs.getInt("id"))
                        .email(rs.getString("email"))
                        .pw(rs.getString("pw")) // password -> pw 수정
                        .fullname(rs.getString("fullname")) // fullame -> fullname 수정
                        .build()
        );
    }

    @Override
    public int update(Member domain) {
//        String sql = "update member set fullname=?, phone=?, address=? where email=? and pw=?";
        String sql = "update member set fullname=?, phone=?, address=? where id=?";
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
        String sql = "delete from member where email=? and pw=?";
        try {
            return jdbcTemplate.update(sql, domain.getEmail(), domain.getPw());
        } catch (Exception e) {
            System.err.println("Delete 에러: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public Member selectByEmailAndPassword(String email, String pw) {
        String sql = "select * from member where email = ? and pw = ?";
        try {
            List<Member> members = jdbcTemplate.query(sql, (rs, i) ->
                            Member.builder()
                                    .email(rs.getString("email"))
                                    .pw(rs.getString("pw"))
                                    .fullname(rs.getString("fullname")) // fullame -> fullname 수정
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
        String sql = "update member set pw = ? where email = ? and pw = ?";
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
        String sql = "update member set pw = ? where id = ?";
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
        String sql = "delete from member where id = ?";
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
        String sql = "select * from member where phone like ?";
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
}
