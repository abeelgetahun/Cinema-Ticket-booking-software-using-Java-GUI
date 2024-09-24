import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.io.IOException;

public class MoviePage extends JFrame {
    private JPanel movieContainer;
    private ArrayList<MoviePanel> moviePanels;
    private JScrollPane scrollPane;

    public MoviePage() {
        setTitle("AMBESSA Cinema - Movies");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.LIGHT_GRAY);

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);

        loadMovies();
        displayMovies();

        setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.setPreferredSize(new Dimension(getWidth(), 50));

        // Logo and brand name
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.setOpaque(false);
        logoPanel.add(createLogoLabel());
        logoPanel.add(new JLabel("Ambessa Cinema") {{
            setFont(new Font("Brush Script MT", Font.BOLD, 24));
            setForeground(Color.BLACK);
        }});
        headerPanel.add(logoPanel, BorderLayout.WEST);


        // Navigation buttons
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        navPanel.setOpaque(false);
         // initializing home button
        
         navPanel.add(new JButton("Home") {{
             setContentAreaFilled(false);
             setBorderPainted(false);
             setFocusPainted(false);
             setForeground(Color.BLACK);
             addActionListener(e -> {
                new Homepage().setVisible(true);
                MoviePage.this.dispose();
            });
            
         }});
         
         // initializing movie button
         
         navPanel.add(new JButton("Movie") {{
             setContentAreaFilled(false);
             setBorderPainted(false);
             setFocusPainted(false);
             setForeground(Color.BLACK);
             
         }});
 
         // initializing Team page

         navPanel.add(new JButton("Teams") {{
             setContentAreaFilled(false);
             setBorderPainted(false);
             setFocusPainted(false);
             setForeground(Color.BLACK);
         }});
 
         //initializing about page
 
         navPanel.add(new JButton("About") {{
             setContentAreaFilled(false);
             setBorderPainted(false);
             setFocusPainted(false);
             setForeground(Color.BLACK);
         }});
 

        headerPanel.add(navPanel, BorderLayout.EAST);

        
        return headerPanel;
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        movieContainer = new JPanel(new GridLayout(0, 3, 10, 15));
        movieContainer.setBackground(Color.BLACK);
        movieContainer.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));  // Add 20px padding top and bottom

        scrollPane = new JScrollPane(movieContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        return mainPanel;
    }

    private JLabel createLogoLabel() {
        try {
            Image img = ImageIO.read(new File("C:/Users/hp/OneDrive/Documents/programs/vs code/java/Cinema/images/software/AmbesaLogoCicle.png")).getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img);
            return new JLabel(icon) {{
                setPreferredSize(new Dimension(40, 40));
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            }};
        } catch (IOException e) {
            e.printStackTrace();
            return new JLabel("Logo");
        }
    }

    private void loadMovies() {
        moviePanels = new ArrayList<>();
        File folder = new File("C:\\Users\\hp\\OneDrive\\Documents\\programs\\vs code\\java\\Cinema\\images\\Movie");
        File[] listOfFiles = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg") || name.endsWith(" "));

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                try {
                    Image img = ImageIO.read(file);
                    String movieName = file.getName().replaceFirst("[.][^.]+ Birr", "");
                    // Remove the file extension
                int lastDotIndex = movieName.lastIndexOf('.');
                if (lastDotIndex != -1) {
                    movieName = movieName.substring(0, lastDotIndex);
                }
                    double price = 100 + Math.random() * 10; // Random price between $10 and $20
                    MoviePanel moviePanel = new MoviePanel(img, movieName, price);
                    moviePanels.add(moviePanel);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void displayMovies() {
        movieContainer.removeAll();
        for (MoviePanel panel : moviePanels) {
            movieContainer.add(panel);
        }
        movieContainer.revalidate();
        movieContainer.repaint();
    }

    private class MoviePanel extends JPanel {
        public MoviePanel(Image image, String name, double price) {
            setLayout(new BorderLayout(0, 5));  // Add 5px vertical gap
            setBackground(Color.BLACK);

            JLabel imageLabel = new JLabel(new ImageIcon(image.getScaledInstance(150, 225, Image.SCALE_SMOOTH)));
            add(imageLabel, BorderLayout.CENTER);

            JLabel nameLabel = new JLabel(name);
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setHorizontalAlignment(JLabel.CENTER);

            JLabel priceLabel = new JLabel(String.format("%.2f Birr", price));
            priceLabel.setForeground(Color.WHITE);
            priceLabel.setHorizontalAlignment(JLabel.CENTER);

            JPanel infoPanel = new JPanel(new GridLayout(2, 1, 0, 5));  // Add 5px vertical gap
            infoPanel.setBackground(Color.BLACK);
            infoPanel.add(nameLabel);
            infoPanel.add(priceLabel);

            add(infoPanel, BorderLayout.SOUTH);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MoviePage::new);
    }
}