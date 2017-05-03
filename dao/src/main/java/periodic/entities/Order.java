package periodic.entities;

import periodic.dao.Identified;


import java.sql.Date;
import java.util.List;

public class Order implements Identified<Integer>{

    private Integer id;
    private Integer idUser;
    private List<Book> bookList;
    private boolean state;
    private Date orderDate;

    public Order() {
    }

    public Order(Integer id, Integer idUser,
                 List<Book> bookList, boolean state,
                 Date dateOfOrder) {
        this.id = id;
        this.idUser = idUser;
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

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser= idUser;
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
