package toby.springbook.user.dao;

import java.sql.SQLException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import toby.springbook.user.domain.User;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = ac.getBean("userDao", UserDao.class);

        dao.addUser(new User("spring-user1", "스프링 사용자1", "spring-pw"));

        User user = dao.get("spring-user1");
        System.out.println("user = " + user);
    }
}
