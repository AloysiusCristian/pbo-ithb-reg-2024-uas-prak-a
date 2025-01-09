package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class RegisterSection {
    public static int checkUniquePhone(String username){
        String query = "select phone from customer where phone = ?";
        try{
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);

            st.setString(1, username);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return 0;
            }
            else{
                return 1;
            }  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Checking Unique Username", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public static int checkUniqueEmail(String email){
        String query = "select email from customer where email = ?";
        try{
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);

            st.setString(1, email);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return 0;
            }
            else{
                return 1;
            }  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Checking Unique Email", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public static boolean inputDatatoDB(String password, String email, String name, String address, String phone){
        try{
            String query = "INSERT INTO customer (password, email, name, address, phone)" + 
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setString(1, password);
            st.setString(2, email);
            st.setString(3, name);
            st.setString(4, address);
            st.setString(5, phone);

            st.execute();

            JOptionPane.showMessageDialog(null, "Registrasi Berhasil", "Registrasi", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Input Data", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
