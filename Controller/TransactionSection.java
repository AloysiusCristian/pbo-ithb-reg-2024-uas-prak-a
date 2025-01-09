package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TransactionSection {
    public static String[] getPackageCategory() {
        String[] category = new String[3];
        String query = "SELECT * FROM category_package";
        try{
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            ResultSet rs = st.executeQuery();
            int i = 0;
            while (rs.next()) {
                category[i] = rs.getString("category");
                i++;
            }
            return category;
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
            return category;
        }
    }
}
