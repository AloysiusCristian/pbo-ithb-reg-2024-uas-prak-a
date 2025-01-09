package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import Model.History;
import Model.SingletonManager;
import Model.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;

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

    public static boolean inputDatatoDB(String packageType, double packageWeight, int totalCost, String name, String address, String phone){
        try{
            String query = "INSERT INTO transaction (customer_id, package_type, package_weight, total_cost, created_at, receipt_name, receipt_address, receipt_phone)" + 
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setInt(1, SingletonManager.getInstance().getCustomer().getId());
            st.setString(2, packageType);
            st.setDouble(3, packageWeight);
            st.setInt(4, totalCost);
            st.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
            st.setString(6, name);
            st.setString(7, address);
            st.setString(8, phone);

            st.execute();

            JOptionPane.showMessageDialog(null, "Add Transaction Berhasil", "Transaction", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Input Data", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static boolean inputDetailDatatoDB(int idTransaksi, String status, String currentPosition, String evidence){
        try{
            String query = "INSERT INTO shipment_detail (transaction_id, status, current_position, evidence, date, updated_by)" + 
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setInt(1, idTransaksi);
            st.setString(2, status);
            st.setString(3, currentPosition);
            st.setString(4, evidence);
            st.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
            st.setString(6, SingletonManager.getInstance().getCustomer().getName());

            st.execute();

            JOptionPane.showMessageDialog(null, "Add Transaction Berhasil", "Transaction", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Input Data", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static ArrayList<History> getHistoryData(){
        ArrayList<History> historyList = new ArrayList<>();
        try{
            String query = "SELECT t.id AS tid, t.package_type, t.package_weight, t.total_cost, t.created_at, t.receipt_name, t.receipt_address, t.receipt_phone, MAX(sd.date) AS most_recent_date FROM transaction AS t INNER JOIN shipment_detail AS sd ON sd.transaction_id = t.id INNER JOIN customer AS c ON c.id = t.customer_id WHERE c.id = ? GROUP BY t.id, t.package_type, t.package_weight, t.total_cost, t.created_at;";
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            int custId = SingletonManager.getInstance().getCustomer().getId();
            st.setInt(1, custId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Transaction transaction = new Transaction(
                    rs.getInt("tid"),
                    custId,
                    rs.getString("package_type"),
                    rs.getDouble("package_weight"),
                    rs.getInt("total_cost"),
                    rs.getDate("created_at"),
                    rs.getString("receipt_name"),
                    rs.getString("receipt_address"),
                    rs.getString("receipt_phone"));
                History history = new History(transaction, rs.getDate("most_recent_date"));
                historyList.add(history);
            }

            return historyList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error dalam mengambil data", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return historyList;
    }
}
