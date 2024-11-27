package cn.itcast;

import cn.itcast.dao.BooksDao;
import cn.itcast.domain.Book;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "showBooksServlet", urlPatterns="/showBooksServlet")
public class showBooksServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BooksDao books = new BooksDao();
        ArrayList<Book> booksList  = books.findAll();

        // 检查 booksList 是否为空
        if (booksList == null || booksList.isEmpty()) {
            // 打印调试信息到控制台
            System.out.println("书籍列表为空或未成功获取书籍数据！");
        } else {
            // 打印书籍总数（可选）
            System.out.println("书籍列表获取成功，共有 " + booksList.size() + " 本书。");
        }

        // 确保不为 null，以避免 JSP 出现空指针异常
        if (booksList == null) {
            booksList = new ArrayList<>(); // 创建一个空列表
        }

        // 将书籍列表设置为请求属性
        request.setAttribute("books", booksList);

        // 转发到 JSP 页面进行展示
        request.getRequestDispatcher("/showBooks.jsp").forward(request, response);
    }
}
