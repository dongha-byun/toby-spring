package toby.springbook.user.dao;

import java.util.List;
import javax.sql.DataSource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import toby.springbook.user.domain.User;

@NoArgsConstructor
@Getter
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void addUser(User user) {
        jdbcTemplate.update("insert into users(id, name, password) values(?, ?, ?)",
                user.getId(), user.getName(), user.getPassword());
    }

    public User get(String id) {
        return jdbcTemplate.queryForObject("select * from users where 1=1 and id = ?",
                getUserRowMapper(),
                id
        );
    }

    public void deleteAll() {
        jdbcTemplate.update("delete from users");
    }

    public int getCount() {
        Integer count = jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
        assert count != null;
        return count;
    }

    public List<User> getAll() {
        return jdbcTemplate.query("select * from users order by id",
                getUserRowMapper());
    }

    private RowMapper<User> getUserRowMapper() {
        return (rs, rowNum) -> new User(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("password")
        );
    }
}
