package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JPanel;

import Model.PratamaExpressFrame;
import Model.SingletonManager;

public class Menu {
    public static void menu(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        PratamaExpressFrame frame = new PratamaExpressFrame("Menu");
        frame.setBounds(screenSize.width/2 - 400/2, screenSize.height/2 - 120/2, 400, 120);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        JButton login = new JButton("Login");
        JButton register = new JButton("Registrasi");
        JButton detailTransaction = new JButton("Detail Transaction");
        JButton addTransaction = new JButton("Add Transaction");
        JButton viewHistory = new JButton("View History");

        mainPanel.add(login);
        mainPanel.add(register);
        mainPanel.add(detailTransaction);
        mainPanel.add(addTransaction);
        mainPanel.add(viewHistory);

        login.addActionListener(e -> {
            Login.logIn();
            frame.dispose();
        });

        register.addActionListener(e -> {
            Register.register();
            frame.dispose();
        });

        if (SingletonManager.getInstance().getCustomer() == null) {
            addTransaction.setEnabled(false);
            viewHistory.setEnabled(false);
            detailTransaction.setEnabled(false);
        }
        else{
            addTransaction.setEnabled(true);
            viewHistory.setEnabled(true);
            detailTransaction.setEnabled(true);
        }

        addTransaction.addActionListener(e -> {
            AddTransaction.addTransaction();
            frame.dispose();
        });

        detailTransaction.addActionListener(e -> {
            AddDetailTransaction.addDetailTransaction();
            frame.dispose();
        });

        viewHistory.addActionListener(e -> {
            HistoryPaket.viewHistory();
            frame.dispose();
        });

        frame.add(mainPanel);

        frame.setVisible(true);
    }
}
