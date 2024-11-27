package cn.itcast.dao;

import cn.itcast.domain.Book;
import cn.itcast.domain.User;
import cn.itcast.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UsersDao {
    // 添加用户的操作
    public boolean insert(User user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // 获取数据库连接
            conn = JDBCUtils.getConnection();

            // SQL 插入语句
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);

            // 设置占位符参数
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());

            // 执行更新操作
            int rows = pstmt.executeUpdate();
            return rows > 0; // 插入成功返回 true
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 插入失败返回 false
        } finally {
            // 释放资源
            JDBCUtils.release(null, pstmt, conn);
        }
    }

    // 查询所有的User对象
    public ArrayList<User> findAll() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<User> userList = new ArrayList<User>();
        try {
            // 获得数据的连接
            conn = JDBCUtils.getConnection();
            // 获得Statement对象
            stmt = conn.createStatement();
            // 发送SQL语句
            String sql = "SELECT * FROM users";
            rs = stmt.executeQuery(sql);
            // 处理结果集
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                userList.add(user);
            }
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, stmt, conn);
        }
        return null;
    }

    // 根据用户名和密码查询用户
    public User findUserByUsernameAndPassword(String username, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            // 获取数据库连接
            conn = JDBCUtils.getConnection();

            // SQL 查询语句
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            // 执行查询
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // 如果找到用户，封装到 User 对象
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, pstmt, conn);
        }

        return user; // 返回查询到的用户对象（可能为 null）
    }

    public boolean isUsernameExist(String username) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 获取数据库连接
            conn = JDBCUtils.getConnection();

            // SQL 查询语句，检查用户名是否存在
            String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
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
