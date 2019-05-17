import model.Book;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class ContextServlet implements ServletContextListener{
    private List<Book> booksList;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        booksList = DBHelper.loadDB();
        servletContext.setAttribute("bookList", booksList);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
