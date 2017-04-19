package periodic;


import periodic.connection.HikaricpConnection;

public class Main {
    static HikaricpConnection hc;


    public static void main(String[] args) {
        hc = HikaricpConnection.getInstance();

    }
}
