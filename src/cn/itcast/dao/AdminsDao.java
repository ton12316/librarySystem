package cn.itcast.dao;

import cn.itcast.domain.Admin;
import cn.itcast.domain.User;
import cn.itcast.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminsDao {
    public Admin findAdminByUsernameAndPassword(String admin_name, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Admin admin = null;

        try {
            // 获取数据库连接
            conn = JDBCUtils.getConnection();

            // SQL 查询语句
            String sql = "SELECT * FROM admins WHERE admin_name = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, admin_name);
            pstmt.setString(2, password);

            // 执行查询
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // 如果找到管理员，封装到 Admin 对象
                admin = new Admin();
                admin.setAdmin_id(rs.getInt("admin_id"));
                admin.setAdmin_name(rs.getString("admin_name"));
                admin.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, pstmt, conn);
        }

        return admin; // 返回查询到的管理员对象（可能为 null）
    }

    /**
     * 将管理员信息插入到admins表
     * @param user 包含管理员用户名和密码的User对象
     * @return 插入是否成功
     */
    public boolean insert(Admin admin) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "INSERT INTO admins (admin_name, password) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, admin.getAdmin_name());
            pstmt.setString(2, admin.getPassword());

            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtils.release(null, pstmt, conn);
        }
    }

    public boolean isUsernameExist(String username) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 获取数据库连接
            conn = JDBCUtils.getConnection();

            // SQL 查询语句，检查用户名是否存在
            String sql = "SELECT COUNT(*) FROM admins WHERE admin_name = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            // 执行查询
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // 如果查询结果大于0，则表示用户名已存在
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, pstmt, conn);
        }
        return false; // 未找到用户名，则返回 false
    }

}
