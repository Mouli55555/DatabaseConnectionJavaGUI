import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
public class ShowDatabases {
    static Connection con;
    public static void databasesButton(){
        String url = "jdbc:mysql://localhost:3306/";
        String user=null;
        while(user==null || user.isEmpty())
        {
            try{
                user = JOptionPane.showInputDialog("Enter user name");
                if(user==null){
                    return;
                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Invalid");
                return;
            }
        }
        String pass=null;
        while(pass==null || pass.isEmpty())
        {
            try{
                pass = JOptionPane.showInputDialog("Enter user password");
                if(pass==null){
                return;
            }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "Invalid");
                return;
            }
        }

    try{
        con = DriverManager.getConnection(url, user, pass);
        if (con!=null){

            //window creation
            JFrame databaseFrame = new JFrame();
            databaseFrame.setResizable(false);
            databaseFrame.setLayout(null);
            databaseFrame.setTitle("List Of Databases");
            databaseFrame.getContentPane().setBackground(Color.YELLOW);
            databaseFrame.setSize(1000, 500);
            databaseFrame.setLocationRelativeTo(null);

            // Icon add
            ImageIcon img = new ImageIcon(MyMainFrame.class.getResource("/mysql.png"));
            databaseFrame.setIconImage(img.getImage());

            //Database query execution and data storing
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SHOW DATABASES");

            ArrayList<String> databases = new ArrayList<>();
            while(rs.next()){
                databases.add(rs.getString(1));
            }


            //Text area
            JTextArea showdatabases = new JTextArea();
            showdatabases.setEditable(false);

            
            // Set text color
            showdatabases.setForeground(Color.WHITE);
            showdatabases.setBackground(Color.BLACK);

            // Set font
            Font font = new Font("Arial", Font.BOLD, 50);
            showdatabases.setFont(font);

            for(String base : databases){
                showdatabases.append(base+"\n");
            }

            //creating scroll pannel
            JScrollPane scrollPane = new JScrollPane(showdatabases);
            scrollPane.setBounds(10, 10, 970, 450);
            scrollPane.getViewport().setBackground(Color.WHITE);
            
            //visability
            databaseFrame.setVisible(true);
            databaseFrame.add(scrollPane, BorderLayout.CENTER);
            databaseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        con.close();
    }
    catch(SQLException e){
        JOptionPane.showMessageDialog(null,"Login details incorrect.");
    }
}
    public static void main(String[] args) {
        ShowDatabases.databasesButton();
    }
}