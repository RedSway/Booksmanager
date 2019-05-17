import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet(urlPatterns = {"/update"})
public class UpdateUserServlet extends HttpServlet {
    private List<Book> bookList;

    @Override
    public void init() throws ServletException {
        bookList = (List<Book>) getServletContext().getAttribute("bookList");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        for (Book book : bookList) {
            if (book.getId() == id) {
                req.setAttribute("book", book);
                break;
            }
        }

        req.getRequestDispatcher("/WEB-INF/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String author = req.getParameter("author");
            Date date = new SimpleDateFormat("dd.MM.yyyy").parse(req.getParameter("date"));
            float price = Float.parseFloat(req.getParameter("price"));

            DBHelper.updateById(id, name, author, date, price);

            for (Book book : bookList) {
                if (book.getId() == id) {
                    book.setName(name);
                    book.setAuthor(author);
                    book.setDate(date);
                    book.setPrice(price);

                    break;
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
