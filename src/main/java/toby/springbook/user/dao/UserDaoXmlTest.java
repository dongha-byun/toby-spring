package toby.springbook.user.dao;

import java.sql.SQLException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import toby.springbook.user.domain.User;

public class UserDaoXmlTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ApplicationContext ac = new GenericXmlApplicationContext("applicationContext.xml");

        UserDao userDao = ac.getBean("userDao", UserDao.class);

        User user = new User("id1", "name1", "pw1");
        userDao.addUser(user);
        System.out.println("user = " + user);

        UserDao findDao = ac.getBean("userDao", UserDao.class);
        User findUser = findDao.get("id1");
        System.out.println("findUser = " + findUser);
    }
}
