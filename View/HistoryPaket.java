package View;

import Model.History;
import Model.PratamaExpressFrame;
import Model.Transaction;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HistoryPaket {
    public static void viewHistory() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        final int FRAME_WIDTH = screenWidth; // Set frame width
        final int FRAME_HEIGHT = screenHeight; // Set frame height

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2); // Center frame horizontally
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2); // Center frame vertically

        PratamaExpressFrame frame = new PratamaExpressFrame("Login Menu"); // Create frame and set title

        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT); // Set frame bounds
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        panel.setBackground(new Color(240, 248, 255)); // Light blue background

        ArrayList<History> historyList = Controller.TransactionSection.getHistoryData();
        
        String[][] historyString = new String[historyList.size()][7];
        
        int i = 0;
        for (History history : historyList) {
            Transaction transaction = history.getTransaction();
            historyString[i][0] = "" + transaction.getId();
            historyString[i][1] = "" + transaction.getPackage_type();
            historyString[i][2] = "" + transaction.getPackage_weight();
            historyString[i][3] = "" + transaction.getTotal_cost();
            historyString[i][4] = transaction.getCreated_at().toString();
            historyString[i][5] = history.getDate().toString();
            i++;
        }

        // Kolom untuk tabel
        String[] columns = {"Transaction ID", "Package Type", "Package Weight", "Total Cost", "Created At", "Updated At", "Show Detail"};

        // Membuat model tabel dan JTable
        DefaultTableModel tableModel = new DefaultTableModel(historyString, columns);
        JTable table = new JTable(tableModel);

        // Menambahkan JScrollPane untuk tabel
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.add(panel);
    }
}
