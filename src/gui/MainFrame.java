package gui;

import model.Student;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {

    // ── Color Palette (Dark Theme) ──────────────────────────
    public static final Color BG_DARK = new Color(15, 23, 42);
    public static final Color BG_CARD = new Color(30, 41, 59);
    public static final Color BG_INPUT = new Color(51, 65, 85);
    public static final Color ACCENT = new Color(56, 189, 248); // sky blue
    public static final Color ACCENT_HOVER = new Color(125, 211, 252);
    public static final Color SUCCESS = new Color(52, 211, 153); // emerald
    public static final Color DANGER = new Color(251, 113, 133); // rose
    public static final Color TEXT_PRIMARY = new Color(248, 250, 252); // bright white
    public static final Color TEXT_SECONDARY = new Color(178, 190, 210);
    public static final Color BORDER = new Color(71, 85, 105);
    public static final Color TABLE_ROW_ALT = new Color(37, 50, 68);
    public static final Color TABLE_HEADER = new Color(51, 65, 85);

    // ── Fonts ───────────────────────────────────────────────
    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 26);
    public static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_LABEL = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_INPUT = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_BUTTON = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FONT_TABLE = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_TABLE_HDR = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_LINK = new Font("Segoe UI", Font.PLAIN, 12);

    private static final String CARD_LOGIN = "LOGIN";
    private static final String CARD_REGISTER = "REGISTER";
    private static final String CARD_DASHBOARD = "DASHBOARD";

    private final CardLayout cardLayout;
    private final JPanel contentPanel;

    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private DashboardPanel dashboardPanel;

    public MainFrame() {
        setTitle("Online Course Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 680);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG_DARK);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(BG_DARK);

        loginPanel = new LoginPanel(this);
        registerPanel = new RegisterPanel(this);
        dashboardPanel = new DashboardPanel(this);

        contentPanel.add(loginPanel, CARD_LOGIN);
        contentPanel.add(registerPanel, CARD_REGISTER);
        contentPanel.add(dashboardPanel, CARD_DASHBOARD);

        add(contentPanel);
        showLogin();
        setVisible(true);
    }

    // ── Navigation ──────────────────────────────────────────
    public void showLogin() {
        loginPanel.clearFields();
        cardLayout.show(contentPanel, CARD_LOGIN);
    }

    public void showRegister() {
        registerPanel.clearFields();
        cardLayout.show(contentPanel, CARD_REGISTER);
    }

    public void showDashboard(Student student) {
        dashboardPanel.setStudent(student);
        cardLayout.show(contentPanel, CARD_DASHBOARD);
    }

    // ── Helper: Create styled button ────────────────────────
    public static JButton createStyledButton(String text, Color bgColor) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(FONT_BUTTON);
        btn.setForeground(Color.WHITE);
        btn.setBackground(bgColor);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(180, 42));

        // hover effect
        Color hoverColor = bgColor.brighter();
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(hoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(bgColor);
            }
        });
        return btn;
    }

    // ── Helper: Create styled text field ────────────────────
    public static JTextField createStyledTextField() {
        JTextField field = new JTextField(20);
        field.setFont(FONT_INPUT);
        field.setForeground(TEXT_PRIMARY);
        field.setBackground(BG_INPUT);
        field.setCaretColor(TEXT_PRIMARY);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        field.setPreferredSize(new Dimension(300, 40));
        return field;
    }

    // ── Helper: Create styled password field ────────────────
    public static JPasswordField createStyledPasswordField() {
        JPasswordField field = new JPasswordField(20);
        field.setFont(FONT_INPUT);
        field.setForeground(TEXT_PRIMARY);
        field.setBackground(BG_INPUT);
        field.setCaretColor(TEXT_PRIMARY);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        field.setPreferredSize(new Dimension(300, 40));
        return field;
    }

    // ── Helper: Create a label ──────────────────────────────
    public static JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(FONT_LABEL);
        lbl.setForeground(TEXT_SECONDARY);
        return lbl;
    }
}
