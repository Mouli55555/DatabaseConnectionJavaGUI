import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyMainFrame {
    public static void frame() {
        // window creation
        JFrame mainFrame = new JFrame();
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);
        mainFrame.setTitle("Database Connection");
        Color framecolor = new Color(0, 0, 0);
        mainFrame.getContentPane().setBackground(framecolor);
        mainFrame.setSize(500, 250);
        mainFrame.setLocationRelativeTo(null);

        // Buttons
        int buttonWidth = 200;
        int buttonHeight = 75;
        int padding = 30;

        Color btncolor = new Color(254, 254, 0);

        JButton btn1 = new JButton("List Of Databases");
        btn1.setBounds(padding, 75, buttonWidth, buttonHeight);
        btn1.setBackground(btncolor);
        btn1.setFocusPainted(false);

        // Icon add
        ImageIcon img = new ImageIcon(MyMainFrame.class.getResource("/mysql.png"));
        mainFrame.setIconImage(img.getImage());

        // button press
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ShowDatabases.databasesButton();
            }
        });

        JButton btn2 = new JButton("Connect To A Database");
        btn2.setBounds((padding * 2) + buttonWidth, 75, buttonWidth, buttonHeight);
        btn2.setBackground(btncolor);
        btn2.setFocusPainted(false);

        //button press
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConnectToDatabase.connectToDatabase();
            }
        });

        // font
        Font myFont = new Font("Arial", Font.BOLD, 14);
        btn1.setFont(myFont);
        btn2.setFont(myFont);

        // window add
        mainFrame.add(btn1);
        mainFrame.add(btn2);

        // visibility
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        MyMainFrame.frame();
    }
}