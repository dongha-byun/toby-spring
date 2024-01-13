package toby.springbook.user.dao.context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.Getter;
import lombok.Setter;
import toby.springbook.user.dao.strategy.StatementStrategy;

@Setter
@Getter
public class JDBCContext {
    private DataSource dataSource;

    public JDBCContext() {}
    public JDBCContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = dataSource.getConnection();

            ps = stmt.makePreparedStatement(c);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try{
                    ps.close();
                } catch (SQLException e) {}
            }

            if(c != null) {
                try {
                    c.close();
                } catch (SQLException e) {}
            }
        }
    }
}
