package toby.springbook.user.dao;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import toby.springbook.user.dao.context.JDBCContext;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setDataSource(dataSource());
        return userDao;
    }

    @Bean
    public AccountDao accountDao() {
        return new AccountDao(dataSource());
    }

    @Bean
    public MessageDao messageDao() {
        return new MessageDao(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        return new SimpleDriverDataSource();
    }

    @Bean
    public JDBCContext jdbcContext() {
        return new JDBCContext(dataSource());
    }
}
