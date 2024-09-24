import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Teams extends JFrame {
    private static final String LOGO_PATH = "C:/Users/hp/OneDrive/Documents/programs/vs code/java/Cinema/images/software/AmbesaLogoCicle.png";
    private static final String[] TEAM_MEMBERS = {"Abel Getahun", "Abreham Kifle", "Akililu Desalegn", "Jaleta Kebede", "Liben Adugna", "Yasin Shalo"};
    private static final String[] TEAM_IMAGE_PATHS = {
        "C:/Users/hp/OneDrive/Documents/programs/vs code/java/Cinema/images/teams/Abel.png",
        "C:/Users/hp/OneDrive/Documents/programs/vs code/java/Cinema/images/teams/Abreham.png",
        "C:/Users/hp/OneDrive/Documents/programs/vs code/java/Cinema/images/teams/Akililu.png",
        "C:/Users/hp/OneDrive/Documents/programs/vs code/java/Cinema/images/teams/Jaleta.png",
        "C:/Users/hp/OneDrive/Documents/programs/vs code/java/Cinema/images/teams/Liben.png",
        "C:/Users/hp/OneDrive/Documents/programs/vs code/java/Cinema/images/teams/yyy.png"
    };

    public Teams() {
        setTitle("Ambessa Cinema - Team");
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
        
        navPanel.add(new JButton("Home") {{
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setForeground(Color.BLACK);
            addActionListener(e -> {
                new Homepage().setVisible(true);
                Teams.this.dispose();
            });
        }});

        navPanel.add(new JButton("Movie") {{
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setForeground(Color.BLACK);
            addActionListener(e -> {
                new MoviePage().setVisible(true);
                Teams.this.dispose();
            });
        }});

        navPanel.add(new JButton("Team") {{
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setForeground(Color.BLACK);
        }});

        navPanel.add(new JButton("About") {{
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setForeground(Color.BLACK);
            addActionListener(e -> {
                new About().setVisible(true);
                Teams.this.dispose();
            });
        }});

        topPanel.add(navPanel, BorderLayout.EAST);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Center panel with black box
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.BLACK);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 100, 20));

        // Title "Our Teams"
        JLabel titleLabel = new JLabel("Our Teams");
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0)); // Reduced space below the title
        centerPanel.add(titleLabel, BorderLayout.NORTH);

        // Team members panel
        JPanel teamPanel = new JPanel(new GridBagLayout());
        teamPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 20, 20, 20);

        for (int i = 0; i < TEAM_MEMBERS.length; i++) {
            ImageIcon profileIcon = loadProfileImage(TEAM_IMAGE_PATHS[i]);
            JPanel memberPanel = createMemberPanel(TEAM_MEMBERS[i], profileIcon);
            
            gbc.gridx = i < 3 ? 0 : 1;
            gbc.gridy = i % 3;
            
            teamPanel.add(memberPanel, gbc);
        }

        JPanel centeringPanel = new JPanel(new GridBagLayout());
        centeringPanel.setOpaque(false);
        centeringPanel.add(teamPanel);

        centerPanel.add(centeringPanel, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private ImageIcon loadProfileImage(String imagePath) {
        try {
            Image img = ImageIO.read(new File(imagePath)).getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private JPanel createMemberPanel(String member, ImageIcon profileIcon) {
        JPanel memberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        memberPanel.setOpaque(false);

        if (profileIcon != null) {
            JLabel imageLabel = new JLabel(profileIcon);
            memberPanel.add(imageLabel);
        } else {
            JLabel placeholderLabel = new JLabel();
            placeholderLabel.setPreferredSize(new Dimension(50, 50));
            memberPanel.add(placeholderLabel);
        }

        JLabel nameLabel = new JLabel(member);
        nameLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        nameLabel.setForeground(Color.WHITE);
        memberPanel.add(nameLabel);

        return memberPanel;
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
        SwingUtilities.invokeLater(() -> new Teams().setVisible(true));
    }
}