
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LibraryManagementSystem extends JFrame {

    private JTable bookTable;
    private DefaultTableModel model;

    private JTextField idField;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField searchField;

    public LibraryManagementSystem() {

        setTitle("Library Management System");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        mainPanel.setBackground(Color.WHITE);

        // Heading
        JLabel heading = new JLabel(
            "LIBRARY MANAGEMENT SYSTEM",
            SwingConstants.CENTER
        );

        heading.setFont(new Font("Arial", Font.BOLD, 26));
        heading.setForeground(new Color(30, 80, 150));

        mainPanel.add(heading, BorderLayout.NORTH);

        // Input Panel
        JPanel inputPanel = new JPanel(
            new GridLayout(4, 2, 10, 10)
        );

        inputPanel.setBorder(
            BorderFactory.createTitledBorder("Book Details")
        );

        idField = new JTextField();
        titleField = new JTextField();
        authorField = new JTextField();
        searchField = new JTextField();

        inputPanel.add(new JLabel("Book ID:"));
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Book Title:"));
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Author Name:"));
        inputPanel.add(authorField);

        inputPanel.add(new JLabel("Search Book:"));
        inputPanel.add(searchField);

        // Table
        String[] columns = {
            "Book ID", "Book Title",
            "Author", "Status"
        };

        model = new DefaultTableModel(columns, 0);

        bookTable = new JTable(model);
        bookTable.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(bookTable);

        // Button Panel
        JPanel buttonPanel = new JPanel(
            new GridLayout(2, 3, 10, 10)
        );

        JButton addButton = new JButton("Add Book");
        JButton deleteButton = new JButton("Delete Book");
        JButton issueButton = new JButton("Issue Book");
        JButton returnButton = new JButton("Return Book");
        JButton searchButton = new JButton("Search");
        JButton clearButton = new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(issueButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(clearButton);

        // Add Book Action
        addButton.addActionListener(e -> addBook());

        // Delete Book Action
        deleteButton.addActionListener(e -> deleteBook());

        // Issue Book Action
        issueButton.addActionListener(e -> issueBook());

        // Return Book Action
        returnButton.addActionListener(e -> returnBook());

        // Search Action
        searchButton.addActionListener(e -> searchBook());

        // Clear Action
        clearButton.addActionListener(e -> clearFields());

        // Layout
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));

        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        setVisible(true);
    }

    // Add Book
    private void addBook() {

        String id = idField.getText().trim();
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();

        if (id.isEmpty() || title.isEmpty() ||
            author.isEmpty()) {

            JOptionPane.showMessageDialog(
                this, "Please fill all book details!"
            );

            return;
        }

        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().equals(id)) {

                JOptionPane.showMessageDialog(
                    this, "Book ID already exists!"
                );

                return;
            }
        }

        model.addRow(new Object[]{
            id, title, author, "Available"
        });

        JOptionPane.showMessageDialog(
            this, "Book Added Successfully!"
        );

        clearFields();
    }

    // Delete Book
    private void deleteBook() {

        int row = bookTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(
                this, "Please select a book!"
            );
            return;
        }

        if (!model.getValueAt(row, 3).equals("Available")) {
            JOptionPane.showMessageDialog(
                this, "Return the issued book before deleting!"
            );
            return;
        }

        model.removeRow(row);

        JOptionPane.showMessageDialog(
            this, "Book Deleted Successfully!"
        );
    }

    // Issue Book
    private void issueBook() {

        int row = bookTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(
                this, "Select a book to issue!"
            );
            return;
        }

        if (model.getValueAt(row, 3).equals("Issued")) {
            JOptionPane.showMessageDialog(
                this, "Book is already issued!"
            );
            return;
        }

        model.setValueAt("Issued", row, 3);

        JOptionPane.showMessageDialog(
            this, "Book Issued Successfully!"
        );
    }

    // Return Book
    private void returnBook() {

        int row = bookTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(
                this, "Select a book to return!"
            );
            return;
        }

        if (model.getValueAt(row, 3).equals("Available")) {
            JOptionPane.showMessageDialog(
                this, "This book is already available!"
            );
            return;
        }

        model.setValueAt("Available", row, 3);

        JOptionPane.showMessageDialog(
            this, "Book Returned Successfully!"
        );
    }

    // Search Book
    private void searchBook() {

        String search = searchField.getText()
                                   .trim()
                                   .toLowerCase();

        if (search.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "Enter Book ID or Title!"
            );
            return;
        }

        for (int i = 0; i < model.getRowCount(); i++) {

            String id = model.getValueAt(i, 0)
                             .toString().toLowerCase();

            String title = model.getValueAt(i, 1)
                                .toString().toLowerCase();

            if (id.contains(search) || title.contains(search)) {

                bookTable.setRowSelectionInterval(i, i);

                JOptionPane.showMessageDialog(
                    this, "Book Found!"
                );

                return;
            }
        }

        JOptionPane.showMessageDialog(
            this, "Book Not Found!"
        );
    }

    // Clear Fields
    private void clearFields() {

        idField.setText("");
        titleField.setText("");
        authorField.setText("");
        searchField.setText("");
        bookTable.clearSelection();
    }

    // Main Method
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new LibraryManagementSystem();
        });
    }
}
