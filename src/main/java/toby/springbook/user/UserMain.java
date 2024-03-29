package toby.springbook.user;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import toby.springbook.user.dao.UserDao;
import toby.springbook.user.domain.User;

public class UserMain {
    public static void main(String[] args) {
        UserDao dao = new UserDao();
        dao.setDataSource(new SimpleDriverDataSource());

        User user = new User();
        user.setId("byunsw4");
        user.setName("변동하");
        user.setPassword("backend_prepared");

        dao.addUser(user);

        System.out.println(user.getId() + " ==> 등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println("user2 = " + user2.getName() + " / " + user2.getPassword());
        System.out.println(user2.getId() + " ==> 조회 성공");
    }
}
