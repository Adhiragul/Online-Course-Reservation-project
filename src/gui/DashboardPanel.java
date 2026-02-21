package gui;

import model.Course;
import model.Reservation;
import model.Student;
import service.CourseService;
import service.ReservationService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class DashboardPanel extends JPanel {

    private final MainFrame frame;
    private final CourseService courseService = new CourseService();
    private final ReservationService reservationService = new ReservationService();

    private Student currentStudent;

    private JLabel welcomeLabel;
    private JLabel statusLabel;

    private DefaultTableModel courseTableModel;
    private JTable courseTable;

    private DefaultTableModel reservationTableModel;
    private JTable reservationTable;

    public DashboardPanel(MainFrame frame) {
        this.frame = frame;
        setBackground(MainFrame.BG_DARK);
        setLayout(new BorderLayout(0, 0));
        buildUI();
    }

    // ═══════════════════════════════════════════════════════
    // BUILD UI
    // ═══════════════════════════════════════════════════════
    private void buildUI() {
        // ── Top Bar ─────────────────────────────────────────
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(MainFrame.BG_CARD);
        topBar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, MainFrame.BORDER),
                BorderFactory.createEmptyBorder(14, 24, 14, 24)));

        JPanel leftTop = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftTop.setOpaque(false);
        JLabel appIcon = new JLabel("|||");
        appIcon.setFont(new Font("Segoe UI", Font.BOLD, 22));
        appIcon.setForeground(MainFrame.ACCENT);
        leftTop.add(appIcon);

        welcomeLabel = new JLabel("Welcome, Student");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        welcomeLabel.setForeground(MainFrame.TEXT_PRIMARY);
        leftTop.add(welcomeLabel);
        topBar.add(leftTop, BorderLayout.WEST);

        JPanel rightTop = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightTop.setOpaque(false);

        JButton refreshBtn = MainFrame.createStyledButton("Refresh", MainFrame.ACCENT);
        refreshBtn.setPreferredSize(new Dimension(110, 36));
        refreshBtn.addActionListener(e -> refreshData());
        rightTop.add(refreshBtn);

        JButton logoutBtn = MainFrame.createStyledButton("Logout", MainFrame.DANGER);
        logoutBtn.setPreferredSize(new Dimension(100, 36));
        logoutBtn.addActionListener(e -> {
            currentStudent = null;
            frame.showLogin();
        });
        rightTop.add(logoutBtn);
        topBar.add(rightTop, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);

        // ── Center: Courses + Reservations ──────────────────
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 0, 0));
        centerPanel.setBackground(MainFrame.BG_DARK);

        centerPanel.add(buildCoursesSection());
        centerPanel.add(buildReservationsSection());

        add(centerPanel, BorderLayout.CENTER);

        // ── Bottom: Status Bar ──────────────────────────────
        JPanel bottomBar = new JPanel(new BorderLayout());
        bottomBar.setBackground(MainFrame.BG_CARD);
        bottomBar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, MainFrame.BORDER),
                BorderFactory.createEmptyBorder(8, 24, 8, 24)));
        statusLabel = new JLabel("Ready");
        statusLabel.setFont(MainFrame.FONT_LINK);
        statusLabel.setForeground(MainFrame.TEXT_SECONDARY);
        bottomBar.add(statusLabel, BorderLayout.WEST);

        add(bottomBar, BorderLayout.SOUTH);
    }

    // ── Courses Section ─────────────────────────────────────
    private JPanel buildCoursesSection() {
        JPanel section = new JPanel(new BorderLayout(0, 0));
        section.setBackground(MainFrame.BG_DARK);
        section.setBorder(BorderFactory.createEmptyBorder(12, 24, 6, 24));

        // Header row
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));

        JLabel sectionTitle = new JLabel("Available Courses");
        sectionTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        sectionTitle.setForeground(MainFrame.TEXT_PRIMARY);
        header.add(sectionTitle, BorderLayout.WEST);

        JButton reserveBtn = MainFrame.createStyledButton("Reserve Selected", MainFrame.SUCCESS);
        reserveBtn.setPreferredSize(new Dimension(160, 34));
        reserveBtn.addActionListener(e -> handleReserve());
        header.add(reserveBtn, BorderLayout.EAST);

        section.add(header, BorderLayout.NORTH);

        // Table
        String[] cols = { "ID", "Title", "Instructor", "Capacity", "Enrolled", "Available" };
        courseTableModel = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        courseTable = createStyledTable(courseTableModel);
        courseTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        courseTable.getColumnModel().getColumn(1).setPreferredWidth(220);
        courseTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        courseTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        courseTable.getColumnModel().getColumn(4).setPreferredWidth(70);
        courseTable.getColumnModel().getColumn(5).setPreferredWidth(70);

        JScrollPane scroll = new JScrollPane(courseTable);
        scroll.setBorder(BorderFactory.createLineBorder(MainFrame.BORDER, 1));
        scroll.getViewport().setBackground(MainFrame.BG_CARD);
        section.add(scroll, BorderLayout.CENTER);

        return section;
    }

    // ── Reservations Section ────────────────────────────────
    private JPanel buildReservationsSection() {
        JPanel section = new JPanel(new BorderLayout(0, 0));
        section.setBackground(MainFrame.BG_DARK);
        section.setBorder(BorderFactory.createEmptyBorder(6, 24, 12, 24));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));

        JLabel sectionTitle = new JLabel("My Reservations");
        sectionTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        sectionTitle.setForeground(MainFrame.TEXT_PRIMARY);
        header.add(sectionTitle, BorderLayout.WEST);

        JButton cancelBtn = MainFrame.createStyledButton("Cancel Selected", MainFrame.DANGER);
        cancelBtn.setPreferredSize(new Dimension(160, 34));
        cancelBtn.addActionListener(e -> handleCancel());
        header.add(cancelBtn, BorderLayout.EAST);

        section.add(header, BorderLayout.NORTH);

        String[] cols = { "ID", "Course", "Reserved On" };
        reservationTableModel = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        reservationTable = createStyledTable(reservationTableModel);
        reservationTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        reservationTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        reservationTable.getColumnModel().getColumn(2).setPreferredWidth(200);

        JScrollPane scroll = new JScrollPane(reservationTable);
        scroll.setBorder(BorderFactory.createLineBorder(MainFrame.BORDER, 1));
        scroll.getViewport().setBackground(MainFrame.BG_CARD);
        section.add(scroll, BorderLayout.CENTER);

        return section;
    }

    // ═══════════════════════════════════════════════════════
    // ACTIONS
    // ═══════════════════════════════════════════════════════
    private void handleReserve() {
        int row = courseTable.getSelectedRow();
        if (row < 0) {
            setStatus("Please select a course to reserve.", MainFrame.DANGER);
            return;
        }
        int courseId = (int) courseTableModel.getValueAt(row, 0);
        String courseTitle = (String) courseTableModel.getValueAt(row, 1);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Reserve a seat in \"" + courseTitle + "\"?",
                "Confirm Reservation",
                JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION)
            return;

        try {
            reservationService.reserveCourse(currentStudent.getId(), courseId);
            setStatus("✓ Successfully reserved \"" + courseTitle + "\"!", MainFrame.SUCCESS);
            refreshData();
        } catch (SQLException ex) {
            setStatus("✗ " + ex.getMessage(), MainFrame.DANGER);
        }
    }

    private void handleCancel() {
        int row = reservationTable.getSelectedRow();
        if (row < 0) {
            setStatus("Please select a reservation to cancel.", MainFrame.DANGER);
            return;
        }
        int reservationId = (int) reservationTableModel.getValueAt(row, 0);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Cancel reservation #" + reservationId + "?",
                "Confirm Cancellation",
                JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION)
            return;

        try {
            reservationService.cancelReservation(reservationId, currentStudent.getId());
            setStatus("✓ Reservation #" + reservationId + " cancelled.", MainFrame.SUCCESS);
            refreshData();
        } catch (SQLException ex) {
            setStatus("✗ " + ex.getMessage(), MainFrame.DANGER);
        }
    }

    // ═══════════════════════════════════════════════════════
    // DATA
    // ═══════════════════════════════════════════════════════
    public void setStudent(Student student) {
        this.currentStudent = student;
        welcomeLabel.setText("Welcome, " + student.getName());
        refreshData();
    }

    private void refreshData() {
        loadCourses();
        loadReservations();
    }

    private void loadCourses() {
        courseTableModel.setRowCount(0);
        try {
            List<Course> courses = courseService.listAllCourses();
            for (Course c : courses) {
                courseTableModel.addRow(new Object[] {
                        c.getId(),
                        c.getTitle(),
                        c.getInstructor(),
                        c.getCapacity(),
                        c.getEnrolledCount(),
                        c.getAvailableSeats()
                });
            }
            setStatus("Loaded " + courses.size() + " courses.", MainFrame.TEXT_SECONDARY);
        } catch (SQLException ex) {
            setStatus("✗ Error loading courses: " + ex.getMessage(), MainFrame.DANGER);
        }
    }

    private void loadReservations() {
        reservationTableModel.setRowCount(0);
        if (currentStudent == null)
            return;
        try {
            List<Reservation> reservations = reservationService.getMyReservations(currentStudent.getId());
            for (Reservation r : reservations) {
                reservationTableModel.addRow(new Object[] {
                        r.getId(),
                        r.getCourseTitle() != null ? r.getCourseTitle() : "Course #" + r.getCourseId(),
                        r.getReservationDate() != null ? r.getReservationDate().toString().substring(0, 19) : "N/A"
                });
            }
        } catch (SQLException ex) {
            setStatus("✗ Error loading reservations: " + ex.getMessage(), MainFrame.DANGER);
        }
    }

    private void setStatus(String message, Color color) {
        statusLabel.setText(message);
        statusLabel.setForeground(color);
    }

    // ═══════════════════════════════════════════════════════
    // STYLED TABLE HELPER
    // ═══════════════════════════════════════════════════════
    private JTable createStyledTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        table.setFont(MainFrame.FONT_TABLE);
        table.setForeground(MainFrame.TEXT_PRIMARY);
        table.setBackground(MainFrame.BG_CARD);
        table.setSelectionBackground(MainFrame.ACCENT);
        table.setSelectionForeground(Color.WHITE);
        table.setGridColor(MainFrame.BORDER);
        table.setRowHeight(32);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setIntercellSpacing(new Dimension(0, 1));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // alternating row colours
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value,
                    boolean isSelected, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, col);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? MainFrame.BG_CARD : MainFrame.TABLE_ROW_ALT);
                    c.setForeground(MainFrame.TEXT_PRIMARY);
                }
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                return c;
            }
        });

        // header styling — use a custom renderer to force colors
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(MainFrame.FONT_TABLE_HDR);
        tableHeader.setReorderingAllowed(false);
        tableHeader.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object value,
                    boolean isSelected, boolean hasFocus, int row, int col) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(t, value, isSelected, hasFocus, row, col);
                lbl.setBackground(new Color(15, 23, 42));
                lbl.setForeground(new Color(56, 189, 248));
                lbl.setFont(MainFrame.FONT_TABLE_HDR);
                lbl.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 2, 0, MainFrame.ACCENT),
                        BorderFactory.createEmptyBorder(6, 10, 6, 10)));
                lbl.setHorizontalAlignment(SwingConstants.LEFT);
                lbl.setOpaque(true);
                return lbl;
            }
        });

        return table;
    }
}
