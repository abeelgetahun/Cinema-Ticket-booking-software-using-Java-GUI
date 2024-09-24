import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class About extends JFrame {
    private static final String LOGO_PATH = "C:/Users/hp/OneDrive/Documents/programs/vs code/java/Cinema/images/software/AmbesaLogoCicle.png";

    public About() {
        setTitle("Ambessa Cinema - About");
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
        
        String[] navButtons = {"Home", "Movie", "Team", "About"};
        for (String buttonText : navButtons) {
            navPanel.add(new JButton(buttonText) {{
                setContentAreaFilled(false);
                setBorderPainted(false);
                setFocusPainted(false);
                setForeground(Color.BLACK);
                addActionListener(e -> {
                    if (!buttonText.equals("About")) {
                        // Placeholder for navigation logic
                        JOptionPane.showMessageDialog(About.this, "Navigating to " + buttonText);
                        // About.this.dispose();
                    }
                });
            }});
        }

        topPanel.add(navPanel, BorderLayout.EAST);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Center panel with black box
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.BLACK);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        // Title "About Our Cinema App"
        JLabel titleLabel = new JLabel("About Our Cinema App");
        titleLabel.setFont(new Font("Segoe Script", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        centerPanel.add(titleLabel, BorderLayout.NORTH);

        // About content
        JTextPane aboutContent = new JTextPane();
        aboutContent.setContentType("text/html");
        String htmlContent = "<html><body style='width: 380px; text-align: center; font-family: Comic Sans MS; font-size: 16px; color: white;'>" +
            "<p>Welcome to our Cinema App! This project showcases our dedication to creating " +
            "a user-friendly platform for movie enthusiasts.</p>" +
            "<p>For more insights into our code and future updates, feel free to follow my " +
            "GitHub account: <a href='https://github.com/abeelgetahun' style='color: #4CAF50;'>abeelgetahun</a>. Your support " +
            "means a lot to us!</p>" +
            "<p>Thank you for being part of our cinematic journey!</p>" +
            "</body></html>";
        aboutContent.setText(htmlContent);
        aboutContent.setEditable(false);
        aboutContent.setBackground(Color.BLACK);
        aboutContent.setOpaque(false);

        JPanel contentWrapper = new JPanel(new GridBagLayout());
        contentWrapper.setBackground(Color.BLACK);
        contentWrapper.add(aboutContent);

        centerPanel.add(contentWrapper, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private JLabel createLogoLabel() {
        try {
            Image img = ImageIO.read(new File(LOGO_PATH)).getScaledInstance(50, 50, Image.SCALE_SMOOTH);
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
        SwingUtilities.invokeLater(() -> new About().setVisible(true));
    }
}