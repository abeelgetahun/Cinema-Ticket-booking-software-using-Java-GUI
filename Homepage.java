import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Homepage extends JFrame {
    private static final String LOGO_PATH = "C:/Users/hp/OneDrive/Documents/programs/vs code/java/Cinema/images/software/AmbesaLogoCicle.png";

    public Homepage() {
        setTitle("Ambessa Cinema");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY);

        // Top panel for logo, brand name, and navigation buttons
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        // Logo and brand name
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.setOpaque(false);
        logoPanel.add(createLogoLabel());
        logoPanel.add(new JLabel("Ambessa Cinema") {{
            setFont(new Font("Brush Script MT", Font.BOLD, 24));
        }});
        topPanel.add(logoPanel, BorderLayout.WEST);

        // Navigation buttons
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        navPanel.setOpaque(false);
        String[] buttonLabels = {"Home", "Movie", "Teams", "About"};
        for (String label : buttonLabels) {
            navPanel.add(new JButton(label) {{
                setContentAreaFilled(false);
                setBorderPainted(false);
                setFocusPainted(false);
                setForeground(Color.BLACK);
            }});
        }
        topPanel.add(navPanel, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Center panel with black box
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.BLACK);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Image on the left side of the black box
        try {
            Image img = ImageIO.read(new File(LOGO_PATH)).getScaledInstance(400, -1, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(img));
            centerPanel.add(imageLabel, BorderLayout.WEST);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Welcome message on the right side of the black box
        JPanel messagePanel = new JPanel(new GridBagLayout());
        messagePanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 100);

        JLabel welcomeLabel = new JLabel("<html><div style='text-align: center;'>Welcome to your<br>cinematic haven</div></html>");
        welcomeLabel.setFont(new Font("Mistral", Font.BOLD, 36));
        welcomeLabel.setForeground(Color.WHITE);
        messagePanel.add(welcomeLabel, gbc);

        JLabel subLabel = new JLabel("<html><div style='text-align: center;'>Indulge in the ultimate<br>movie experience with us</div></html>");
        subLabel.setFont(new Font("Mistral", Font.PLAIN, 30));
        subLabel.setForeground(Color.WHITE);
        messagePanel.add(subLabel, gbc);

        centerPanel.add(messagePanel, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);
    }
    

    private JLabel createLogoLabel() {
        try {
            Image img = ImageIO.read(new File("C:/Users/hp/OneDrive/Documents/programs/vs code/java/Cinema/images/software/AmbesaLogoCicle.png")).getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img);
            return new JLabel(icon) {{
                setPreferredSize(new Dimension(50, 50));
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            }};
        } catch (IOException e) {
            e.printStackTrace();
            return new JLabel("Logo");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Homepage().setVisible(true));
    }
}