package gui;

import model.Student;
import service.StudentService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class LoginPanel extends JPanel {

    private final MainFrame frame;
    private final StudentService studentService = new StudentService();

    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    public LoginPanel(MainFrame frame) {
        this.frame = frame;
        setBackground(MainFrame.BG_DARK);
        setLayout(new GridBagLayout());
        buildUI();
    }

    private void buildUI() {
        // Card container
        JPanel card = new JPanel();
        card.setBackground(MainFrame.BG_CARD);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(MainFrame.BORDER, 1),
                BorderFactory.createEmptyBorder(40, 50, 40, 50)));


        JLabel iconLabel = new JLabel("OCRS", SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        iconLabel.setForeground(MainFrame.ACCENT);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(iconLabel);
        card.add(Box.createVerticalStrut(10));


        JLabel title = new JLabel("Welcome Back");
        title.setFont(MainFrame.FONT_TITLE);
        title.setForeground(MainFrame.TEXT_PRIMARY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(title);

        JLabel subtitle = new JLabel("Sign in to your account");
        subtitle.setFont(MainFrame.FONT_SUBTITLE);
        subtitle.setForeground(MainFrame.TEXT_SECONDARY);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(subtitle);
        card.add(Box.createVerticalStrut(30));

        JLabel emailLbl = MainFrame.createLabel("EMAIL");
        emailLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(emailLbl);
        card.add(Box.createVerticalStrut(6));

        emailField = MainFrame.createStyledTextField();
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        emailField.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(emailField);
        card.add(Box.createVerticalStrut(18));


        JLabel passLbl = MainFrame.createLabel("PASSWORD");
        passLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(passLbl);
        card.add(Box.createVerticalStrut(6));

        passwordField = MainFrame.createStyledPasswordField();
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(passwordField);
        card.add(Box.createVerticalStrut(8));


        statusLabel = new JLabel(" ");
        statusLabel.setFont(MainFrame.FONT_LINK);
        statusLabel.setForeground(MainFrame.DANGER);
        statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(statusLabel);
        card.add(Box.createVerticalStrut(14));

        
        JButton loginBtn = MainFrame.createStyledButton("Sign In", MainFrame.ACCENT);
        loginBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        loginBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginBtn.addActionListener(e -> handleLogin());
        card.add(loginBtn);
        card.add(Box.createVerticalStrut(20));

   
        JLabel registerLink = new JLabel("Don't have an account?  Register here");
        registerLink.setFont(MainFrame.FONT_LINK);
        registerLink.setForeground(MainFrame.ACCENT);
        registerLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLink.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                frame.showRegister();
            }

            public void mouseEntered(java.awt.event.MouseEvent e) {
                registerLink.setForeground(MainFrame.ACCENT_HOVER);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                registerLink.setForeground(MainFrame.ACCENT);
            }
        });
        card.add(registerLink);


        passwordField.addActionListener(e -> handleLogin());
        emailField.addActionListener(e -> passwordField.requestFocus());

        add(card);
    }

    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (email.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        try {
            Student student = studentService.login(email, password);
            if (student != null) {
                statusLabel.setText(" ");
                frame.showDashboard(student);
            } else {
                statusLabel.setText("Invalid email or password.");
            }
        } catch (SQLException ex) {
            statusLabel.setText("Error: " + ex.getMessage());
        }
    }

    public void clearFields() {
        if (emailField != null)
            emailField.setText("");
        if (passwordField != null)
            passwordField.setText("");
        if (statusLabel != null)
            statusLabel.setText(" ");
    }
}
