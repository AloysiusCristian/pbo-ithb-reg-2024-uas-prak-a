package View;

import Model.PratamaExpressFrame;
import java.awt.*;
import javax.swing.*;

public class AddTransaction {
    public static void addTransaction() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        PratamaExpressFrame frame = new PratamaExpressFrame("Register Form");
        frame.setBounds(screenSize.width/2 - 400/2, screenSize.height/2 - 500/2, 400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 248, 255)); // Light blue background

        JLabel titleLabel = new JLabel("Add Transaction", SwingConstants.CENTER);
        titleLabel.setBounds(50, 10, 300, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 102, 204));
        panel.add(titleLabel);

        JLabel namaLabel = new JLabel("Nama");
        namaLabel.setBounds(50, 60, 150, 20);
        panel.add(namaLabel);

        JTextField nama = new JTextField();
        nama.setBounds(50, 85, 300, 30);
        nama.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(nama);

        JLabel alamatLabel = new JLabel("Alamat");
        alamatLabel.setBounds(50, 125, 150, 20);
        panel.add(alamatLabel);

        JTextField alamat = new JTextField();
        alamat.setBounds(50, 150, 300, 30);
        alamat.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(alamat);

        JLabel noTelpLabel = new JLabel("No Telepon");
        noTelpLabel.setBounds(50, 190, 150, 20);
        panel.add(noTelpLabel);

        JTextField noTelp = new JTextField();
        noTelp.setBounds(50, 225, 300, 30);
        noTelp.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(noTelp);

        JLabel beratLabel = new JLabel("Berat");
        beratLabel.setBounds(50, 255, 150, 20);
        panel.add(beratLabel);

        JTextField berat = new JTextField();
        berat.setBounds(50, 280, 300, 30);
        berat.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(berat);

        String[] packageTypeList = Controller.TransactionSection.getPackageCategory();

        JComboBox packageType = new JComboBox(packageTypeList);
        packageType.setBounds(50, 325, 300, 30);
        packageType.setMaximumSize(new Dimension(190,20));
        packageType.setSelectedItem(packageTypeList[0]);
        packageType.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(packageType);

        JButton simpan = new JButton("Simpan");
        simpan.setBounds(220, 390, 100, 30);
        simpan.setBackground(new Color(0, 102, 204));
        simpan.setForeground(Color.WHITE);
        simpan.setFocusPainted(false);
        panel.add(simpan);

        JButton back = new JButton("Back");
        back.setBounds(70, 390, 100, 30);
        back.setBackground(new Color(220, 20, 60)); 
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        panel.add(back);

        simpan.addActionListener(e -> {
            boolean valid = true;
            double packageWeight = 0;
            int cost = 0;
            if (nama.getText().isEmpty() || alamat.getText().isEmpty() || noTelp.getText().isEmpty() ||
                    berat.getText().isEmpty()) {
                valid = false;
                JOptionPane.showMessageDialog(frame, "Please fill all required fields.");
            }
            try {
                packageWeight = Double.parseDouble(berat.getText());
                if (packageWeight == 0) {
                    valid = false;
                }
                else{
                    if (packageWeight < 1) {
                        cost = 1;
                    }
                    else{
                        cost = (int)Math.round(packageWeight);
                    }
                }
            } catch (Exception f) {
                JOptionPane.showMessageDialog(null, "Berat harus angka!", "Weight", JOptionPane.INFORMATION_MESSAGE);
            }
            if (valid) {
                Controller.TransactionSection.inputDatatoDB(packageType.getSelectedItem().toString(), packageWeight, cost, nama.getText(), alamat.getText(), noTelp.getText());
                Menu.menu();
                frame.dispose();
            }
            else{
                JOptionPane.showMessageDialog(null, "Pastikan semua field sudah terisi dan benar", "Add Transaction", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        back.addActionListener(e -> {
            Menu.menu();
            frame.dispose();
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}   