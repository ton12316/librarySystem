import cn.itcast.dao.UsersDao;
import cn.itcast.domain.User;

public class UserDaoTest {
    public static void main(String[] args) {
        // 创建 UsersDao 实例
        UsersDao usersDao = new UsersDao();

        // 输入测试的用户名和密码
        String testUsername = "zs"; // 数据库中的用户名
        String testPassword = "123"; // 数据库中的密码

        // 调用方法查询用户
        User user = usersDao.findUserByUsernameAndPassword(testUsername, testPassword);

        // 测试输出结果
        if (user != null) {
            System.out.println("测试成功，查询到用户信息：");
            System.out.println("ID: " + user.getId());
            System.out.println("用户名: " + user.getUsername());
            System.out.println("密码: " + user.getPassword());
        } else {
            System.out.println("测试失败，未查询到用户。");
        }
    }
}

