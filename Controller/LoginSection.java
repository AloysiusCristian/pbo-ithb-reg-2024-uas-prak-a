package Controller;

import Model.Customer;
import Model.SingletonManager;
import View.Login;
import View.Menu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class LoginSection {
    public static void login(String noTelp, String password) {
        String query = "select * from customer where phone = ?";
        try {
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setString(1, noTelp);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    Customer cust = new Customer(
                        rs.getString("address"),
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("email"));
                    SingletonManager.getInstance().setCustomer(cust);
                    JOptionPane.showMessageDialog(null, "Login Berhasil", "Login", JOptionPane.DEFAULT_OPTION);
                    Menu.menu();
                } else {
                    JOptionPane.showMessageDialog(null, "Login Gagal\nPassword Salah", "Login Gagal",
                            JOptionPane.DEFAULT_OPTION);
                    Login.logIn();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Login Gagal\nUsername not found", "Login Gagal",
                        JOptionPane.DEFAULT_OPTION);
                Login.logIn();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
        DatabaseHandler.disconnect();
    }
}
