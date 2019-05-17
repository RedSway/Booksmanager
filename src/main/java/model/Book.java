package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
    private int id;
    private String name;
    private String author;
    private Date date;
    private float price;

    public Book(int id, String name, String author, Date date, float price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.date = date;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return new SimpleDateFormat("dd.MM.yyyy").format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
