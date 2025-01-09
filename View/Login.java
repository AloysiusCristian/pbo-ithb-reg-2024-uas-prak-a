package View;

import Model.PratamaExpressFrame;
import java.awt.*;
import javax.swing.*;

public class Login {
    public static void logIn() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        final int FRAME_WIDTH = 400; // Set frame width
        final int FRAME_HEIGHT = 440; // Set frame height

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2); // Center frame horizontally
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2); // Center frame vertically

        PratamaExpressFrame frame = new PratamaExpressFrame("Login Menu"); // Create frame and set title

        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT); // Set frame bounds
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        panel.setBackground(new Color(240, 248, 255)); // Light blue background

        JLabel titleLabel = new JLabel("Pratama Express", SwingConstants.CENTER);
        titleLabel.setBounds(50, 20, 300, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(25, 25, 112)); // Dark blue text
        panel.add(titleLabel);

        JLabel noTelpLabel = new JLabel("Nomor Telepon:");
        noTelpLabel.setBounds(50, 100, 300, 30);
        noTelpLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        noTelpLabel.setForeground(Color.BLACK);
        panel.add(noTelpLabel);

        JTextField noTelp = new JTextField();
        noTelp.setBounds(50, 130, 300, 30);
        noTelp.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(noTelp);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 180, 300, 30);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setForeground(Color.BLACK);
        panel.add(passwordLabel);

        JPasswordField password = new JPasswordField();
        password.setBounds(50, 210, 300, 30);
        password.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(password);

        JButton submit = new JButton("Login");
        submit.setBounds(50, 270, 300, 40);
        submit.setFont(new Font("Arial", Font.BOLD, 16));
        submit.setBackground(new Color(100, 149, 237));
        submit.setForeground(Color.WHITE);
        submit.setFocusPainted(false);
        panel.add(submit);

        submit.addActionListener(e -> {
            Controller.LoginSection.login(noTelp.getText(), new String(password.getPassword()));
            frame.dispose();
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(50, 320, 300, 40);
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(220, 20, 60)); 
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        panel.add(backButton);

        backButton.addActionListener(e -> {
            Menu.menu();
            frame.dispose();
        });

        frame.setVisible(true);
        frame.add(panel);
    }
}
