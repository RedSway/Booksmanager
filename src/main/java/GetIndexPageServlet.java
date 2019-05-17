import model.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetIndexPageServlet extends HttpServlet {

    private static String index = "/WEB-INF/index.jsp";

    private List<Book> bookList;

    @Override
    public void init() throws ServletException {
        bookList = DBHelper.loadDB();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("bookList", bookList);
        req.getRequestDispatcher(index).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String delete = req.getParameter("delete");
            String update = req.getParameter("update");
            String ok = req.getParameter("ok");

            if (ok != null) {
                String name = req.getParameter("name");
                String author = req.getParameter("author");
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("date"));
                float price = Float.parseFloat(req.getParameter("price"));

                DBHelper.addBook(name, author, date, price);
                int id = DBHelper.selectMaxId();
                bookList.add(new Book(id, name, author, date, price));

            } else if (delete != null) {
                int id = Integer.parseInt(req.getParameter("id"));

                DBHelper.deleteById(id);

                for (int i = 0; i < bookList.size(); i++) {
                    if (bookList.get(i).getId() == id) {
                        bookList.remove(i);
                        break;
                    }
                }


            } else if (update != null) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("name", req.getParameter("name"));
                map.put("author", req.getParameter("author"));
                map.put("date", req.getParameter("date"));
                map.put("price", req.getParameter("price"));

                DBHelper.updateById(Integer.parseInt(req.getParameter("id")), map);

                for (Book book : bookList) {
                    if (book.getId() == Integer.parseInt(req.getParameter("id"))) {
                        if (req.getParameter("name") != null && req.getParameter("name").length() != 0)
                            book.setName(req.getParameter("name"));

                        if (req.getParameter("author") != null && req.getParameter("author").length() != 0)
                            book.setAuthor(req.getParameter("author"));

                        if (req.getParameter("date") != null && req.getParameter("date").length() != 0)
                            book.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("date")));

                        if (req.getParameter("price") != null && req.getParameter("price").length() != 0)
                            book.setPrice(Float.parseFloat(req.getParameter("price")));

                        break;
                    }
                }
            }

            doGet(req, resp);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

}

