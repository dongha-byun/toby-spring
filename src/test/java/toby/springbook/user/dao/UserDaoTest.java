package toby.springbook.user.dao;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDaoTest {
    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
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
}
