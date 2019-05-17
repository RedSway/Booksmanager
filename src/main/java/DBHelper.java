import model.Book;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class DBHelper {
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/Bookmanager";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1z5x9c";

    static List<Book> loadDB() {
        Connection connection;
        PreparedStatement preparedStatement;
        List<Book> booksList = new ArrayList<Book>();

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String selectBooks = "SELECT * FROM Books";
            preparedStatement = connection.prepareStatement(selectBooks);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                Date date = resultSet.getDate("date");
                float price = resultSet.getFloat("price");

                booksList.add(new Book(id, name, author, date, price));
            }

            connection.close();

            return booksList;

        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
            e.printStackTrace();
            return null;
        }

    }

    static void addBook(String name, String author, Date date, float price) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String insert = "INSERT INTO books(name, author, date, price) VALUES(?,?,?,?)";
            preparedStatement = connection.prepareStatement(insert);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, author);
            preparedStatement.setDate(3, new java.sql.Date(date.getTime()));
            preparedStatement.setFloat(4, price);

            preparedStatement.execute();

            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static int selectMaxId() {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String selectMaxId = "SELECT MAX(id) FROM books";
            preparedStatement = connection.prepareStatement(selectMaxId);
            ResultSet resultSet = preparedStatement.executeQuery();

            int id = 0;
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            connection.close();
            return id;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    static void deleteById(int id) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            String delete = "DELETE FROM books WHERE id = " + id;

            preparedStatement = connection.prepareStatement(delete);
            preparedStatement.executeUpdate();

            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void updateById(int id, Map<String, String> map) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            StringBuilder update = new StringBuilder("UPDATE books SET ");
            for (Map.Entry<String, String> pair : map.entrySet()) {
                if (pair.getValue() != null && pair.getValue().length() != 0)
                    update.append(pair.getKey()).append(" = ").append("'").append(pair.getValue()).append("'").append(", ");
            }
            update.delete(update.lastIndexOf(","), update.length());
            update.append(" WHERE id = ").append(id);

            preparedStatement = connection.prepareStatement(update.toString());
            preparedStatement.executeUpdate();

            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
