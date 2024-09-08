import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;

public class CreateAccountForm extends JFrame {
    private JTextField[] fields = new JTextField[6];
    private JLabel userNameErrorLabel;

    public CreateAccountForm() {
        setTitle("Create Account");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY);

        JButton backButton = new JButton("Back");
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setForeground(Color.BLACK);
        backButton.addActionListener(e -> {
            new Loginpage().setVisible(true);
            this.dispose();
        });

        JPanel formPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
        };
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        formPanel.add(new JLabel("Create An Account", JLabel.CENTER) {{
            setFont(new Font("Brush Script MT", Font.BOLD, 30));
        }}, gbc);

        String[] labels = {"First Name", "Last Name", "User Name", "Password", "Email", "Phone Number"};
        for (int i = 0; i < labels.length; i++) {
            formPanel.add(new JLabel(labels[i]), gbc);
            fields[i] = i == 3 ? new JPasswordField(20) : new JTextField(20);
            fields[i].setBorder(new CompoundBorder(
                new MatteBorder(1, 1, 3, 3, new Color(0, 0, 0, 50)), // Shadow border
                new LineBorder(Color.LIGHT_GRAY) // Main border
            ));
            formPanel.add(fields[i], gbc);
            if (i == 2) {
                userNameErrorLabel = new JLabel("", JLabel.CENTER);
                userNameErrorLabel.setForeground(Color.RED);
                formPanel.add(userNameErrorLabel, gbc);
            }
        }

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(e -> createAccount());
        createAccountButton.setBackground(new Color(0, 120, 215));
        createAccountButton.setForeground(Color.WHITE);
        formPanel.add(createAccountButton, gbc);

        JPanel westPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        westPanel.setOpaque(false);
        westPanel.add(backButton);

        mainPanel.add(westPanel, BorderLayout.WEST);
        mainPanel.add(new JPanel(new GridBagLayout()) {{
            setOpaque(false);
            add(formPanel);
        }}, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void createAccount() {
        String[] data = new String[6];
        for (int i = 0; i < 6; i++) {
            data[i] = fields[i].getText();
        }
    
        if (data[2].isEmpty() || data[3].isEmpty() || data[4].isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.");
            return;
        }
    
        if (isUsernameTaken(data[2])) {
            userNameErrorLabel.setText("Username already exists");
            return;
        }
    
        try {
            Files.write(Paths.get("users.txt"),
                    (data[2] + ", "+ data[4] + ", " + data[3] + "\n").getBytes(),
                    StandardOpenOption.APPEND, StandardOpenOption.CREATE);
    
    
    
            String[] fileNames = {"firstname: ", ", lastname: ", ", username: ", ", password: ", ", email: ", ", phonenumber: "};
            for (int i = 0; i < 6; i++) {
                Files.write(Paths.get("accounts.txt"), (fileNames[i]+data[i]).getBytes(),
                        StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            }
            Files.write(Paths.get("accounts.txt"), ("\n").getBytes(),
                    StandardOpenOption.APPEND, StandardOpenOption.CREATE);
    
            JOptionPane.showMessageDialog(this, "Account created successfully!");
            new Loginpage().setVisible(true);
            this.dispose();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error creating account: " + ex.getMessage());
        }
    }
    

    private boolean isUsernameTaken(String username) {
        try {
            return Files.lines(Paths.get("users.txt"))
                    .anyMatch(line -> line.split(",")[0].equals(username));
        } catch (IOException e) {
            return false;
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CreateAccountForm().setVisible(true));
    }
    }