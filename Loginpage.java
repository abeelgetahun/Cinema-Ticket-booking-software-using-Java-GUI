import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Loginpage extends JFrame {
    private JTextField usernameOrEmailField;
    private JPasswordField passwordField;
    private JLabel errorLabel;

    public Loginpage() {
        setTitle("Cinema አምበሳ");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(75, 50, 75, 0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;

        mainPanel.add(new LeftBox(), gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(75, 0, 75, 50);
        mainPanel.add(new RightBox(), gbc);

        add(mainPanel);
    }

    class LeftBox extends JPanel {
        private Image backgroundImage;

        public LeftBox() {
            setPreferredSize(new Dimension(450, 350));
            setLayout(new BorderLayout());

            try {
                backgroundImage = ImageIO.read(new File("C:/Users/hp/OneDrive/Documents/programs/vs code/java/Cinema/images/software/LoginImage.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            JLabel welcomeLabel = new JLabel("<html><center>Welcome Back<br>to<br>Lion Cinema</center></html>", JLabel.CENTER);
            welcomeLabel.setFont(new Font("Mistral", Font.BOLD, 40));
            welcomeLabel.setForeground(Color.WHITE);
            add(welcomeLabel, BorderLayout.CENTER);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Path2D path = new Path2D.Float();
            path.moveTo(30, 0);
            path.lineTo(getWidth(), 0);
            path.lineTo(getWidth(), getHeight());
            path.lineTo(30, getHeight());
            path.quadTo(0, getHeight(), 0, getHeight() - 30);
            path.lineTo(0, 30);
            path.quadTo(0, 0, 30, 0);
            path.closePath();

            g2d.setClip(path);

            if (backgroundImage != null) {
                g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    class RightBox extends JPanel {
        public RightBox() {
            setPreferredSize(new Dimension(350, 350));
            setBackground(Color.WHITE);
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(7, 20, 7, 20);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JLabel usernameOrEmailLabel = new JLabel("Username/Email:");
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(usernameOrEmailLabel, gbc);

            usernameOrEmailField = new JTextField(20);
            gbc.gridy = 1;
            add(usernameOrEmailField, gbc);

            JLabel passwordLabel = new JLabel("Password:");
            gbc.gridy = 2;
            add(passwordLabel, gbc);

            passwordField = new JPasswordField(20);
            gbc.gridy = 3;
            add(passwordField, gbc);

            errorLabel = new JLabel("");
            errorLabel.setForeground(Color.RED);
            gbc.gridy = 4;
            add(errorLabel, gbc);

            JButton loginButton = new JButton("LOGIN");
            loginButton.setBackground(new Color(0, 120, 215));
            loginButton.setForeground(Color.WHITE);
            loginButton.setFocusPainted(false);
            gbc.gridy = 5;
            gbc.insets = new Insets(10, 20, 10, 20);
            add(loginButton, gbc);

            loginButton.addActionListener(e -> handleLogin());

            JButton forgotPasswordButton = new JButton("Forgot Password?");
            forgotPasswordButton.setBorderPainted(false);
            forgotPasswordButton.setContentAreaFilled(false);
            forgotPasswordButton.setForeground(Color.BLUE);
            gbc.gridy = 6;
            gbc.insets = new Insets(10, 20, 10, 20);
            add(forgotPasswordButton, gbc);

            JButton noAccountButton = new JButton("Don't have an account?");
            noAccountButton.setBorderPainted(false);
            noAccountButton.setContentAreaFilled(false);
            noAccountButton.setForeground(Color.BLUE);
            gbc.gridy = 7;
            add(noAccountButton, gbc);

            noAccountButton.addActionListener(e -> {
                CreateAccountForm createAccountForm = new CreateAccountForm();
                createAccountForm.setVisible(true);
                Loginpage.this.dispose();
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Path2D path = new Path2D.Float();
            path.moveTo(0, 0);
            path.lineTo(getWidth() - 30, 0);
            path.quadTo(getWidth(), 0, getWidth(), 30);
            path.lineTo(getWidth(), getHeight() - 30);
            path.quadTo(getWidth(), getHeight(), getWidth() - 30, getHeight());
            path.lineTo(0, getHeight());
            path.closePath();

            g2d.setClip(path);

            g2d.setColor(getBackground());
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    private void handleLogin() {
        String usernameOrEmail = usernameOrEmailField.getText().trim();
        String password = new String(passwordField.getPassword());

        try {
            List<String> lines = Files.readAllLines(Paths.get("users.txt"));
            boolean credentialsMatch = false;

            for (String line : lines) {
                String[] parts = line.split("\\s*,\\s*");
                if (parts.length == 3) {
                    String username = parts[0].trim();
                    String email = parts[1].trim();
                    String storedPassword = parts[2].trim();

                    if ((usernameOrEmail.equals(username) || usernameOrEmail.equals(email)) && password.equals(storedPassword)) {
                        credentialsMatch = true;
                        break;
                    }
                }
            }

            if (credentialsMatch) {
                errorLabel.setText("");
                // Call Homepage class
                Homepage homepage = new Homepage();
                homepage.setVisible(true);
                this.dispose();
            } else {
                errorLabel.setText("Match not Found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            errorLabel.setText("Error reading user data");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Loginpage().setVisible(true));
    }
}