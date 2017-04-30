package periodic.constants;


public class SqlQueries {

    //Book queries
    public static final String BOOK_CREATE_QUERY = "INSERT INTO periodicdb.books (name_book, author_book, genre_book) VALUES (?, ?, ?)";
    public static final String BOOK_SELECT_QUERY = "SELECT * FROM periodicdb.books";
    public static final String BOOK_UPDATE_QUERY = "UPDATE periodicdb.books SET name_book = ?, author_book = ?, genre_book = ? WHERE id_book = ?";
    public static final String BOOK_DELETE_QUERY = "DELETE FROM periodicdb.books WHERE id_book = ?";

    //Order queries
    public static final String ORDER_CREATE_QUERY = "INSERT INTO periodicdb.orders (user_order, state_order, date_order) VALUES (?, ?, ?)";
    public static final String ORDER_SELECT_QUERY = "SELECT * FROM periodicdb.orders";
    public static final String ORDER_UPDATE_QUERY = "UPDATE periodic.orders SET state_order = ?, WHERE id_order = ?";
    public static final String ORDER_DELETE_QUERY = "DELETE FROM periodicdb.orders Where id_order = ?";

    //User queries
    public static final String USER_CREATE_QUERY = "INSERT INTO periodicdb.users (login_user, password_user, email_user, role_user) VALUES (?, ?, ?, ?)";
    public static final String USER_SELECT_QUERY = "SELECT * FROM periodicdb.users";
    public static final String USER_UPDATE_QUERY = "UPDATE periodicdb.users SET login_user = ?, password_user = ?, email_user = ?, role_user = ? WHERE id_user = ?";
    public static final String USER_DELETE_QUERY = "DELETE FROM periodicdb.users WHERE id_user = ?";

}
