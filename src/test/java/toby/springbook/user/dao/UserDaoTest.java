package toby.springbook.user.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import toby.springbook.user.domain.User;

@Transactional
@SpringBootTest
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Test
    public void addAndGet() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao daoByAc1 = ac.getBean("userDao", UserDao.class);
        UserDao daoByAc2 = ac.getBean("userDao", UserDao.class);

        System.out.println("daoByAc1 = " + daoByAc1);
        System.out.println("daoByAc2 = " + daoByAc2);
        System.out.println("daoByAc1 == daoByAc2 => " + (daoByAc1 == daoByAc2));

        UserDao daoByFactory1 = new DaoFactory().userDao();
        UserDao daoByFactory2 = new DaoFactory().userDao();

        System.out.println("daoByFactory1 = " + daoByFactory1);
        System.out.println("daoByFactory2 = " + daoByFactory2);
        System.out.println("daoByFactory1 == daoByFactory2 = " + (daoByFactory1 == daoByFactory2));
    }

    @Test
    void getAll() {
        User user1 = new User("one", "1번", "one_password");
        User user2 = new User("two", "2번", "two_password");
        User user3 = new User("three", "3번", "three_password");
        userDao.deleteAll();
        List<User> users0 = userDao.getAll();
        assertThat(users0).hasSize(0);

        userDao.addUser(user1);
        List<User> users1 = userDao.getAll();
        assertThat(users1).hasSize(1);
        checkSameUser(user1, users1.get(0));

        userDao.addUser(user2);
        List<User> users2 = userDao.getAll();
        assertThat(users2).hasSize(2);
        checkSameUser(user1, users2.get(0));
        checkSameUser(user2, users2.get(1));

        userDao.addUser(user3);
        List<User> users3 = userDao.getAll();
        assertThat(users3).hasSize(3);
        checkSameUser(user1, users3.get(0));
        checkSameUser(user2, users3.get(1));
        checkSameUser(user3, users3.get(2));
    }

    private void checkSameUser(User user1, User user) {
        assertThat(user1).isEqualTo(user);
    }
}
