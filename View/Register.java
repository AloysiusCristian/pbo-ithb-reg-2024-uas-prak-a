package View;

import Model.PratamaExpressFrame;
import java.awt.*;
import javax.swing.*;

public class Register {
    public static void register() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        PratamaExpressFrame frame = new PratamaExpressFrame("Register Form");
        frame.setBounds(screenSize.width/2 - 400/2, screenSize.height/2 - 500/2, 400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 248, 255)); // Light blue background

        JLabel titleLabel = new JLabel("Registration", SwingConstants.CENTER);
        titleLabel.setBounds(50, 10, 300, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 102, 204));
        panel.add(titleLabel);

        JLabel telpLabel = new JLabel("Nomor Telepon");
        telpLabel.setBounds(50, 60, 150, 20);
        panel.add(telpLabel);

        JTextField noTelp = new JTextField();
        noTelp.setBounds(50, 85, 300, 30);
        noTelp.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(noTelp);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 125, 150, 20);
        panel.add(emailLabel);

        JTextField email = new JTextField();
        email.setBounds(50, 150, 300, 30);
        email.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(email);

        JLabel namaLabel = new JLabel("Nama");
        namaLabel.setBounds(50, 190, 150, 20);
        panel.add(namaLabel);

        JTextField nama = new JTextField();
        nama.setBounds(50, 225, 300, 30);
        nama.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(nama);

        JLabel alamatLabel = new JLabel("Alamat");
        alamatLabel.setBounds(50, 255, 150, 20);
        panel.add(alamatLabel);

        JTextField alamat = new JTextField();
        alamat.setBounds(50, 280, 300, 30);
        alamat.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(alamat);

        JLabel passwordL = new JLabel("Password");
        passwordL.setBounds(50, 310, 150, 20);
        panel.add(passwordL);

        JPasswordField password = new JPasswordField();
        password.setBounds(50, 345, 300, 30);
        password.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(password);

        JButton register = new JButton("Registrasi");
        register.setBounds(220, 390, 100, 30);
        register.setBackground(new Color(0, 102, 204));
        register.setForeground(Color.WHITE);
        register.setFocusPainted(false);
        panel.add(register);

        JButton back = new JButton("Back");
        back.setBounds(70, 390, 100, 30);
        back.setBackground(new Color(220, 20, 60)); 
        back.setForeground(Color.WHITE);
        back.setFocusPainted(false);
        panel.add(back);

        register.addActionListener(e -> {
            boolean valid = true;
            if (noTelp.getText().isEmpty() || email.getText().isEmpty() || new String(password.getPassword()).isEmpty() ||
                    nama.getText().isEmpty() || alamat.getText().isEmpty()) {
                valid = false;
                JOptionPane.showMessageDialog(frame, "Please fill all required fields.");
            }
            if (Controller.RegisterSection.checkUniquePhone(noTelp.getText()) == 0) {
                valid = false;
                JOptionPane.showMessageDialog(frame, "Username is already taken!");
            }
            if (Controller.RegisterSection.checkUniqueEmail(email.getText()) == 0) {
                valid = false;
                JOptionPane.showMessageDialog(frame, "Email is already registered!");
            }
            if (valid) {
                Controller.RegisterSection.inputDatatoDB(new String(password.getPassword()), email.getText(), nama.getText(), alamat.getText(), noTelp.getText());
                Login.logIn();
                frame.dispose();
            } else {
                Register.register();
                frame.dispose();
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
