package dbclass;

import javax.swing.*;
import java.sql.*;

public class ConnectionSQL {

    private Connection con;

    private String url = "jdbc:mysql://127.0.0.1:3306/magpie";
    private String login = "root";
    private String password = "";

    public ConnectionSQL(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://127.0.0.1:3306/magpie";
            login = "root";
            password = "";
            try {
                con = DriverManager.getConnection(url, login, password);
            } catch (SQLException e){
                System.out.println("Ошибка подключения к БД");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to load class.");
            e.printStackTrace();
        }
    }

    public DefaultListModel getListIdUsers()
    {
        String query = "SELECT id FROM users_fl;";

        DefaultListModel dlm = new DefaultListModel();

        try {
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while(rs.next())
                {
                    dlm.addElement(rs.getString("id"));
                }
                stmt.close();
            } finally {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dlm;
    }

    public void qNonQuery(String query) {
        try {
            try {
                Statement stmt = con.createStatement();
                stmt.executeUpdate(query);
                stmt.close();
            } finally {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  //  dbc.qNonQuery("insert into logs(text, ChatId) values('"+mes.Text+"',"+mes.Chat.Id.ToString()+");");


    public void TestSQL()
    {
        try {

            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM users_fl");
                while (rs.next()) {
                    String str = rs.getString("FirstName") + " " + rs.getString("LastName");
                    System.out.println("Contact:" + str);
                }
                rs.close();
                stmt.close();
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
