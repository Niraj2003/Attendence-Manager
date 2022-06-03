import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AttendeceManager extends WindowAdapter implements ActionListener {
    JTextField tf1;
    JTextField tf2;
    JTextField tf3;
    JTextField tf4;
    JFrame f;
    JButton b1;
    JButton b2;

    AttendeceManager() {
        f = new JFrame();
        JLabel lx = new JLabel("Select Year:");
        JLabel l1 = new JLabel("Students's PRN No. ");
        JLabel l2 = new JLabel("Student's FirstName ");
        JLabel l3 = new JLabel("Students's LastName ");
        JLabel l =new JLabel("Form for VIIT Students ");

        b1 = new JButton("Submit");
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();

        DefaultListModel<String> lt1 = new DefaultListModel<>();
        JList<String> list = new JList<>(lt1);

        //Select Year
        lx.setBounds(20,100,200,30);
        list.setBounds(150,70,70,75);

        //PRN
        l1.setBounds(20, 160, 200, 30);
        tf1.setBounds(250,160,100,30);

        //Firstname
        l2.setBounds(20, 220, 200,30);
        tf2.setBounds(250, 220, 100, 30);

        //LastName
        l3.setBounds(20, 280, 200, 30);
        tf3.setBounds(250,280,100,30);

        //Submit Button
        b1.setBounds(180, 370, 100,40);
        b1.setBackground(Color.pink);

        //Heading 
        l.setBounds(180,15, 300, 50);

        //Submitted
        tf4.setBounds(130, 420, 200, 25);

        //Select Year Elements
        lt1.addElement("FY");
        lt1.addElement("SY");
        lt1.addElement("TY");
        lt1.addElement("BE");

        b1.addActionListener(this);
        f.addWindowListener (this);

        f.add(b1);
        f.add(l1);
        f.add(tf1);
        f.add(tf3);
        f.add(lx);
        f.add(list);
        f.add(l2);
        f.add(tf2);
        f.add(tf4);
        f.add(l3);
        f.add(l);

        f.setSize(500,500);
        f.setTitle("VIIT Students Details ");
        f.setLayout(null);
        f.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource()==b1){
            String dbURL = "jdbc:mysql://localhost:3306/students";
            String username = "root";
            String password ="Niraj@2003";
            Connection conn;
            try {
                conn = DriverManager.getConnection(dbURL, username, password);
                if (conn != null) {
                    System.out.println("Connected");
                }

                String PRN = tf1.getText();
                System.out.println(PRN);

                String FirstName = tf2.getText();
                System.out.println(FirstName);

                String LastName = tf3.getText();
                System.out.println(LastName);

                String sql = "INSERT INTO students.attendence (PRN,FirstName,LastName) VALUES (?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1,PRN);
                statement.setString(2,FirstName);
                statement.setString(3,LastName);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted>0) {
                    tf4.setText("Successfully Submitted");
                }
            } 
            catch (Exception a) {
                a.printStackTrace();
            } ;
        }
        else if (evt.getSource()==b2){
            tf4.setText(" ");
        }
    }

    @Override
    public void windowClosing (WindowEvent e) {
        f.dispose();
    }

    public static void main(String args[]) {
        new AttendeceManager();
    }
}

