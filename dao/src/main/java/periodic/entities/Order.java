package periodic.entities;

import periodic.dao.Identified;

import java.util.Date;
import java.util.List;

public class Order implements Identified<Integer>{

    private Integer id;
    private User user;
    private List<Book> bookList;
    private boolean state;
    private Date orderDate;

    public Order() {
    }

    public Order(Integer id, Integer idClient,
                 List<Book> bookList, boolean state,
                 Date dateOfOrder) {
        this.id = id;
        this.user = user;
        this.bookList = bookList;
        this.state = state;
        this.orderDate = dateOfOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user= user;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
