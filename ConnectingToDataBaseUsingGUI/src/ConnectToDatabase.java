import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
public class ConnectToDatabase {

    static Connection con;
    public static String fuser;
    public static String fpass;
    public static String fdatabase;

    public static void connectToDatabase(){

        String url = "jdbc:mysql://localhost:3306/";
        String user = null;
        String pass = null;
        String database = null;
        while (user==null || user.isEmpty()) {
        try{
            user = JOptionPane.showInputDialog("Enter user name");
            if(user==null) {
                return;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Invalid");
            return;
        }
        finally{
        fuser = user;
        }
        }
        while (pass==null || pass.isEmpty()) {  
            try{
                pass = JOptionPane.showInputDialog("Enter user password");
                if(pass==null) {
                    return;
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Invalid");
                return;
            }
            finally{
            fpass = pass;
            }
        }
        while(database==null || database.isEmpty()){
            try{
                database = JOptionPane.showInputDialog("Enter database to connect");
                if(database==null){
                return;
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Invalid");
                return;
            }
            finally{
                fdatabase = database;
            }
        }
        try{
            con = DriverManager.getConnection(url+database, user, pass);
            if(con!=null){

            //window creation
            JFrame connectedframe = new JFrame();
            connectedframe.setResizable(false);
            connectedframe.setLayout(null);
            connectedframe.setTitle("Connected to "+fdatabase);
            connectedframe.getContentPane().setBackground(Color.BLACK);
            connectedframe.setSize(500, 250);
            connectedframe.setLocationRelativeTo(null);

            //label
            JLabel lbl1 = new JLabel();
            lbl1.setText("Connected to " + fdatabase);
            lbl1.setBounds(30,15,450,60);
            lbl1.setForeground(Color.GREEN);


            
            // Icon add
            ImageIcon img = new ImageIcon(MyMainFrame.class.getResource("/mysql.png"));
            connectedframe.setIconImage(img.getImage());

            // Buttons
            int buttonWidth = 200;
            int buttonHeight = 75;
            int padding = 30;

            Color btncolor = new Color(254, 254, 0);

            JButton btn1 = new JButton("Show Tables");
            btn1.setBounds(padding, 75, buttonWidth, buttonHeight);
            btn1.setBackground(btncolor);
            btn1.setFocusPainted(false);

            // button press
            btn1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ShowTables.showTables();
                }
            });

            JButton btn2 = new JButton("Show details");
            btn2.setBounds((padding * 2) + buttonWidth, 75, buttonWidth, buttonHeight);
            btn2.setBackground(btncolor);
            btn2.setFocusPainted(false);

            // button press
            btn2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ViewTables.viewTables();
                }
            });

            // font
            Font myFont = new Font("Arial", Font.BOLD, 14);
            btn1.setFont(myFont);
            btn2.setFont(myFont);
            Font myFont2 = new Font("Arial",Font.BOLD,45);
            Font myFont3 = new Font("Arial",Font.BOLD,40);
            Font myFont4 = new Font("Arial",Font.BOLD,37);
            Font myFont5 = new Font("Arial",Font.BOLD,30);
            if(database.length()<=5){
                lbl1.setFont(myFont2);
            }
            else if(database.length()>5 && database.length()<=7){
                lbl1.setFont(myFont3);
            }
            else if(database.length()>7 && database.length()<=10){
                lbl1.setFont(myFont4);
            }
            else{
                lbl1.setFont(myFont5);
            }
        

            //frame add
            connectedframe.add(btn1);
            connectedframe.add(btn2);
            connectedframe.add(lbl1);
           
           //visability
            connectedframe.setVisible(true);
            connectedframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        con.close();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Login details incorrect or database not found.");
        }
    }
    public static void main(String[] args) {
        ConnectToDatabase.connectToDatabase();
    }
}    