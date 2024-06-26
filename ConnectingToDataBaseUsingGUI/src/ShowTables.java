import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.awt.*;
public class ShowTables {
    static Connection con;
    public static void showTables(){
        String url = "jdbc:mysql://localhost:3306/";
    try{
        con = DriverManager.getConnection(url+ConnectToDatabase.fdatabase , ConnectToDatabase.fuser, ConnectToDatabase.fpass );
        if (con!=null){

            //window creation
            JFrame showTableFrame = new JFrame();
            showTableFrame.setResizable(false);
            showTableFrame.setLayout(null);
            showTableFrame.setTitle("List Of Tables");
            showTableFrame.getContentPane().setBackground(Color.YELLOW);
            showTableFrame.setSize(1000, 500);
            showTableFrame.setLocationRelativeTo(null);

            // Icon add
            ImageIcon img = new ImageIcon(MyMainFrame.class.getResource("/mysql.png"));
            showTableFrame.setIconImage(img.getImage());

            //Database query execution and data storing
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SHOW TABLES");

            ArrayList<String> tables = new ArrayList<>();
            while(rs.next()){
                tables.add(rs.getString(1));
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

            for(String base : tables){
                showdatabases.append(base+"\n");
            }

            //creating scroll pannel
            JScrollPane scrollPane = new JScrollPane(showdatabases);
            scrollPane.setBounds(10, 10, 970, 450);
            scrollPane.getViewport().setBackground(Color.WHITE);
            
            //visability
            showTableFrame.setVisible(true);
            showTableFrame.add(scrollPane, BorderLayout.CENTER);
            showTableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        con.close();
    }
    catch(SQLException e){
        JOptionPane.showMessageDialog(null,"Login details incorrect or database not found.");
    }
  }
} 