package toby.springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.dao.EmptyResultDataAccessException;
import toby.springbook.user.dao.context.JDBCContext;
import toby.springbook.user.domain.User;

@NoArgsConstructor
@Setter
@Getter
public class UserDao {
    private JDBCContext jdbcContext;
    private DataSource dataSource;

    public UserDao(JDBCContext jdbcContext, DataSource dataSource) {
        this.jdbcContext = jdbcContext;
        this.dataSource = dataSource;
    }

    public void addUser(User user) throws SQLException {
        jdbcContext.workWithStatementStrategy(c -> {
            PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());

            return ps;
        });
    }

    public User get(String id) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = dataSource.getConnection();

            ps = c.prepareStatement("select * from users where id=?");
            ps.setString(1, id);

            rs = ps.executeQuery();

            User user = null;
            if(rs.next()) {
                user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }

            if(user == null) {
                throw new EmptyResultDataAccessException(1);
            }

            return user;
        } catch (SQLException e) {
            throw e;
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e){}
            }

            if(ps != null) {
                try{
                    ps.close();
                } catch (SQLException e){}
            }

            if(c != null) {
                try{
                    c.close();
                } catch (SQLException e){}
            }
        }
    }

    public void deleteAll() throws SQLException {
        jdbcContext.workWithStatementStrategy(c -> c.prepareStatement("delete from users"));
    }

    public int getCount() throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = dataSource.getConnection();

            ps = c.prepareStatement("select count(*) from users");

            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e){}
            }

            if(ps != null) {
                try{
                    ps.close();
                } catch (SQLException e){}
            }

            if(c != null) {
                try{
                    c.close();
                } catch (SQLException e){}
            }
        }
    }

}
