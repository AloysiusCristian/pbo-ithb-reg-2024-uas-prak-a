package View;

import Model.EnumStatus;
import Model.PratamaExpressFrame;
import java.awt.*;
import java.io.File;

import javax.swing.*;

public class AddDetailTransaction {
    static File selectedFilePath;

    public static void addDetailTransaction(){

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

        JLabel idTransactionLabel = new JLabel("Id Transaction");
        idTransactionLabel.setBounds(50, 60, 150, 20);
        panel.add(idTransactionLabel);

        JTextField idTransaction = new JTextField();
        idTransaction.setBounds(50, 85, 300, 30);
        idTransaction.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(idTransaction);

        JComboBox<String> status = new JComboBox<>(new String[]{
            EnumStatus.DELIVERED.toString(),
            EnumStatus.PENDING.toString(),
            EnumStatus.TRANSIT.toString()
        });
        status.setBounds(50, 125, 300, 30);
        status.setMaximumSize(new Dimension(190,20));
        status.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(status);

        JLabel currentPositionLabel = new JLabel("Current Position");
        currentPositionLabel.setBounds(50, 170, 150, 20);
        panel.add(currentPositionLabel);

        JTextField currentPosition = new JTextField();
        currentPosition.setBounds(50, 205, 300, 30);
        currentPosition.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(currentPosition);

        JButton evidence = new JButton("Upload Photo");
        evidence.setBounds(50, 250, 150, 30);
        evidence.setBackground(new Color(0, 102, 204));
        evidence.setForeground(Color.WHITE);
        evidence.setFocusPainted(false);
        panel.add(evidence);

        evidence.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFilePath = fileChooser.getSelectedFile();

                File photoFolder = new File("Photos");
                if (!photoFolder.exists()) {
                    photoFolder.mkdir(); 
                }

                File destinationFile = new File(photoFolder, selectedFilePath.getName());

                try {
                    java.nio.file.Files.copy(
                            selectedFilePath.toPath(),
                            destinationFile.toPath(),
                            java.nio.file.StandardCopyOption.REPLACE_EXISTING
                    );
                    selectedFilePath = destinationFile;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Failed to upload photo: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

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
            if (idTransaction.getText().isEmpty() || currentPosition.getText().isEmpty() ||
                    evidence.getText().isEmpty()) {
                valid = false;
                JOptionPane.showMessageDialog(frame, "Please fill all required fields.");
            }
            if (valid) {
                Controller.TransactionSection.inputDetailDatatoDB(Integer.parseInt(idTransaction.getText()), status.getSelectedItem().toString(), currentPosition.getText(), selectedFilePath.getPath());
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
