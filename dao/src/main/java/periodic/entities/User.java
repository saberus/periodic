package periodic.entities;

import periodic.dao.Identified;
import periodic.entities.enums.ERolesUser;

import java.util.List;

public class User implements Identified<Integer> {

    private Integer id;
    private String login;
    private String password;
    private String email;
    private List<Order> orderList;
    private List<Book> bookList;
    private ERolesUser role;

    public User(Integer id, String login, String password,
                String email, List<Order> orderList, List<Book> bookList,
                ERolesUser role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.orderList = orderList;
        this.bookList = bookList;
        this.role = role;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public ERolesUser getRole() {
        return role;
    }

    public void setRole(ERolesUser role) {
        this.role = role;
    }
}
