package toby.springbook.user.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import toby.springbook.user.domain.User;

@SpringBootTest
public class UserDaoXmlTest {

    @Autowired
    UserDao userDao;

    User user1;
    User user2;
    User user3;

    @BeforeEach
    void setUp() {
        ApplicationContext ac = new GenericXmlApplicationContext("applicationContext.xml");
        userDao = ac.getBean("userDao", UserDao.class);
        user1 = new User("id1", "name1", "pw1");
        user2 = new User("id2", "name2", "pw2");
        user3 = new User("id3", "name3", "pw3");
    }

    @Test
    public void addAndGet() throws SQLException {
        userDao.deleteAll();
        assertThat(userDao.getCount()).isEqualTo(0);

        userDao.addUser(user1);
        userDao.addUser(user2);
        assertThat(userDao.getCount()).isEqualTo(2);

        User findUser1 = userDao.get(user1.getId());
        assertThat(user1.getName()).isEqualTo(findUser1.getName());
        assertThat(user1.getPassword()).isEqualTo(findUser1.getPassword());

        User findUser2 = userDao.get(user2.getId());
        assertThat(user2.getName()).isEqualTo(findUser2.getName());
        assertThat(user2.getPassword()).isEqualTo(findUser2.getPassword());
    }

    @Test
    void count() throws SQLException {
        userDao.deleteAll();
        assertThat(userDao.getCount()).isEqualTo(0);

        userDao.addUser(user1);
        assertThat(userDao.getCount()).isEqualTo(1);

        userDao.addUser(user2);
        assertThat(userDao.getCount()).isEqualTo(2);

        userDao.addUser(user3);
        assertThat(userDao.getCount()).isEqualTo(3);
    }

    @Test
    void getUserFailure() throws SQLException {
        userDao.deleteAll();
        assertThat(userDao.getCount()).isEqualTo(0);

        assertThatThrownBy(
                () -> userDao.get("unknown_id")
        ).isInstanceOf(EmptyResultDataAccessException.class);
    }
}
