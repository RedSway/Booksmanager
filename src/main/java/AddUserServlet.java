import model.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddUserServlet extends HttpServlet {
    private List<Book> bookList;

    @Override
    public void init() throws ServletException {
        bookList = (List<Book>) getServletContext().getAttribute("bookList");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            String author = req.getParameter("author");
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("date"));
            float price = Float.parseFloat(req.getParameter("price"));

            DBHelper.addBook(name, author, date, price);
            int id = DBHelper.selectMaxId();
            bookList.add(new Book(id, name, author, date, price));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
