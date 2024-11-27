import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) {
        // 数据库连接参数
        String url = "jdbc:mysql://localhost:3306/bookdb?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
        String user = "root"; // 数据库用户名
        String password = "root"; // 数据库密码

        Connection conn = null;

        try {
            // 加载 MySQL 驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("数据库驱动加载成功！");

            // 建立数据库连接
            conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.println("数据库连接成功！");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("加载数据库驱动失败，请检查驱动路径是否正确！");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库连接失败，请检查 URL、用户名或密码是否正确！");
            e.printStackTrace();
        } finally {
            // 关闭连接
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("数据库连接已关闭！");
                } catch (SQLException e) {
                    System.out.println("关闭连接时发生错误！");
                    e.printStackTrace();
                }
            }
        }
    }
}
