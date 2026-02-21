package gui;

import model.Student;
import service.StudentService;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Registration screen with name, email, and password fields.
 */
public class RegisterPanel extends JPanel {

    private final MainFrame frame;
    private final StudentService studentService = new StudentService();

    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    public RegisterPanel(MainFrame frame) {
        this.frame = frame;
        setBackground(MainFrame.BG_DARK);
        setLayout(new GridBagLayout());
        buildUI();
    }

    private void buildUI() {
        JPanel card = new JPanel();
        card.setBackground(MainFrame.BG_CARD);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(MainFrame.BORDER, 1),
                BorderFactory.createEmptyBorder(35, 50, 35, 50)));

        // ── Icon ──
        JLabel iconLabel = new JLabel("OCRS", SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        iconLabel.setForeground(MainFrame.SUCCESS);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(iconLabel);
        card.add(Box.createVerticalStrut(8));

        // ── Title ──
        JLabel title = new JLabel("Create Account");
        title.setFont(MainFrame.FONT_TITLE);
        title.setForeground(MainFrame.TEXT_PRIMARY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(title);

        JLabel subtitle = new JLabel("Start reserving courses today");
        subtitle.setFont(MainFrame.FONT_SUBTITLE);
        subtitle.setForeground(MainFrame.TEXT_SECONDARY);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(subtitle);
        card.add(Box.createVerticalStrut(25));

        // ── Full Name ──
        JLabel nameLbl = MainFrame.createLabel("FULL NAME");
        nameLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(nameLbl);
        card.add(Box.createVerticalStrut(6));

        nameField = MainFrame.createStyledTextField();
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        nameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(nameField);
        card.add(Box.createVerticalStrut(14));

        // ── Email ──
        JLabel emailLbl = MainFrame.createLabel("EMAIL");
        emailLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(emailLbl);
        card.add(Box.createVerticalStrut(6));

        emailField = MainFrame.createStyledTextField();
        emailField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        emailField.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(emailField);
        card.add(Box.createVerticalStrut(14));

        // ── Password ──
        JLabel passLbl = MainFrame.createLabel("PASSWORD");
        passLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(passLbl);
        card.add(Box.createVerticalStrut(6));

        passwordField = MainFrame.createStyledPasswordField();
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(passwordField);
        card.add(Box.createVerticalStrut(8));

        // ── Status label ──
        statusLabel = new JLabel(" ");
        statusLabel.setFont(MainFrame.FONT_LINK);
        statusLabel.setForeground(MainFrame.DANGER);
        statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(statusLabel);
        card.add(Box.createVerticalStrut(14));

        // ── Register button ──
        JButton registerBtn = MainFrame.createStyledButton("Create Account", MainFrame.SUCCESS);
        registerBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        registerBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        registerBtn.addActionListener(e -> handleRegister());
        card.add(registerBtn);
        card.add(Box.createVerticalStrut(18));

        // ── Login link ──
        JLabel loginLink = new JLabel("Already have an account?  Sign in here");
        loginLink.setFont(MainFrame.FONT_LINK);
        loginLink.setForeground(MainFrame.ACCENT);
        loginLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLink.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                frame.showLogin();
            }

            public void mouseEntered(java.awt.event.MouseEvent e) {
                loginLink.setForeground(MainFrame.ACCENT_HOVER);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                loginLink.setForeground(MainFrame.ACCENT);
            }
        });
        card.add(loginLink);

        // Enter key triggers register
        passwordField.addActionListener(e -> handleRegister());
        nameField.addActionListener(e -> emailField.requestFocus());
        emailField.addActionListener(e -> passwordField.requestFocus());

        add(card);
    }

    private void handleRegister() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            statusLabel.setForeground(MainFrame.DANGER);
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        try {
            Student student = studentService.register(name, email, password);
            statusLabel.setForeground(MainFrame.SUCCESS);
            statusLabel.setText("Account created! ID: " + student.getId() + ". Redirecting...");

            // brief pause then switch to login
            Timer timer = new Timer(1500, e -> frame.showLogin());
            timer.setRepeats(false);
            timer.start();
        } catch (SQLException ex) {
            statusLabel.setForeground(MainFrame.DANGER);
            statusLabel.setText("Error: " + ex.getMessage());
        }
    }

    public void clearFields() {
        if (nameField != null)
            nameField.setText("");
        if (emailField != null)
            emailField.setText("");
        if (passwordField != null)
            passwordField.setText("");
        if (statusLabel != null)
            statusLabel.setText(" ");
    }
}
