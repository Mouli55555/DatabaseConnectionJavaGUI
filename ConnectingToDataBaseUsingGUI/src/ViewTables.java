import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.awt.*;

public class ViewTables {
    static Connection con;
    public static void viewTables() {
        String url = "jdbc:mysql://localhost:3306/";
        String tableName=null;
        while(tableName==null || tableName.isEmpty())
        {
            try{
                tableName = JOptionPane.showInputDialog("Enter table name");
                if(tableName==null){
                    return;
                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Invalid");
                return;
            }
        }

        try {
            con = DriverManager.getConnection(url + ConnectToDatabase.fdatabase, ConnectToDatabase.fuser, ConnectToDatabase.fpass);
            if (con != null) {

                // Window creation
                JFrame viewTableFrame = new JFrame();
                viewTableFrame.setResizable(false);
                viewTableFrame.setLayout(null);
                viewTableFrame.setTitle("Table: " + tableName);
                viewTableFrame.getContentPane().setBackground(Color.YELLOW);
                viewTableFrame.setSize(1400, 700);
                viewTableFrame.setLocationRelativeTo(null);

                // Icon add
                ImageIcon img = new ImageIcon(MyMainFrame.class.getResource("/mysql.png"));
                viewTableFrame.setIconImage(img.getImage());

                // Database query execution and data storing
                Statement st = con.createStatement();

                ResultSet rs = st.executeQuery("SELECT * FROM " + tableName);

                // Columns names and storing
                ResultSetMetaData attributeData = rs.getMetaData();
                int columnCount = attributeData.getColumnCount();

                String[] cNames = new String[columnCount];
                for(int i = 1;i<=columnCount;i++){
                    cNames[i-1]=attributeData.getColumnName(i);
                }

                //column data
                String[][] data = new String[1000][columnCount];
                int rowIndex = 0;
                while (rs.next()) {
                    for(int i=1;i<=columnCount;i++){
                        data[rowIndex][i-1]=rs.getString(i);
                    }
                    rowIndex++;
                }

            
                //Overriding isCellEditable method to make cells non editable
                DefaultTableModel model = new DefaultTableModel(data, cNames) {
                    public boolean isCellEditable(int row, int column) {
                        return false; 
                    }
                };

                //table creating
                JTable table = new JTable(model);
                table.setRowHeight(30);

                //Set font
                Font font = new Font("Arial", Font.BOLD, 18);
                table.setFont(font);

                // Creating scroll panel
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setBounds(10, 10, 1370, 650);
                scrollPane.getViewport().setBackground(Color.WHITE);

                // Visibility
                viewTableFrame.setVisible(true);
                viewTableFrame.add(scrollPane, BorderLayout.CENTER);
                viewTableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Table not found.");
        }
    }
    public static void main(String[] args) {
        ViewTables.viewTables();
    }
}