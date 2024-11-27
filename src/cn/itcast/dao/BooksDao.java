package cn.itcast.dao;

import cn.itcast.domain.Book;
import cn.itcast.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BooksDao {
    // 查询所有的User对象
    public ArrayList<Book> findAll() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Book> books = new ArrayList<Book>();
        try {
            // 获得数据的连接
            conn = JDBCUtils.getConnection();
            // 获得Statement对象
            stmt = conn.createStatement();
            // 发送SQL语句
            String sql = "SELECT * FROM books";
            rs = stmt.executeQuery(sql);
            // 处理结果集
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("book_id"));
                book.setIsbn(rs.getString("ISBN"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setCategory(rs.getString("category"));
                book.setBookStatus(rs.getString("book_status"));
                books.add(book);
            }
            return books;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, stmt, conn);
        }
        return null;
    }
}
