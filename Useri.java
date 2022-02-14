package com.company;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Useri extends JFrame {

    //declararea componentelor
    private JToggleButton butonAdd;
    private JTextField butonTelefon;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTable jTable1;
    private JTextArea jTextArea1;
    private JTextField textPass;
    private JTextField textUser;

    //memorarea datelor pt o conexiune permanenta cu baza de date si declararea unor date ce vor modifica baza de date
    String url = "jdbc:mysql://localhost:3305/mysql";
    String username = "root";
    String password = "numaistiuparola";
    String user = "";
    String telefon = "";
    String pass = "";
    Connection connx;
    DefaultTableModel model;

    public Useri() {
        initComponents();
        connx = databaseConnection();
        populatejTable1FromMysqlDatabase();
    }

    public Connection databaseConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public ArrayList<User> userList() {
        ArrayList<User> userList = new ArrayList<User>();
        String sql = "SELECT * FROM Users5";
        Statement st;
        ResultSet rs;

        try {
            st = connx.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Conectat");
            while (rs.next()) {
                User user = new User();
                user.setuser1(rs.getString("user1"));
                user.settelefon(rs.getString("telefon"));
                user.setpassword1(rs.getString("password1"));
                userList.add(user);
                System.out.println(user.getuser1ID());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void populatejTable1FromMysqlDatabase() {
        ArrayList<User> dataArray = userList();
        model = (DefaultTableModel) jTable1.getModel();

        model.setRowCount(0);
        String[] rows = new String[3];
        for (int i = 0; i < dataArray.size(); i++) {
            rows[0] = dataArray.get(i).getuser1();
            rows[1] = dataArray.get(i).gettelefon();
            //rows[2] = dataArray.get(i).getpassword1();
            model.addRow(rows);
        }
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        butonAdd = new JToggleButton();
        textUser = new JTextField();
        textPass = new JTextField();
        butonTelefon = new JTextField();
        jScrollPane2 = new JScrollPane();
        jTextArea1 = new JTextArea();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Users");
        //setResizable(false);

        jPanel1.setBackground(new Color(0, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "User", "Telefon"
                }
        ) {
            Class[] types = new Class [] {
                    String.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        butonAdd.setText("Add User");
        butonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonAddActionPerformed(evt);
            }
        });

        textUser.setText("User");

        textPass.setText("Pass");

        butonTelefon.setText("telefon");

        jTextArea1.setBackground(new Color(0, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Vrei sa-ti faci\nun cont nou?");
        jScrollPane2.setViewportView(jTextArea1);

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(18, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(47, 47, 47)
                                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(butonTelefon, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(textPass, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(textUser, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(56, 56, 56)
                                                                .addComponent(butonAdd)))
                                                .addGap(42, 42, 42))
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 21, Short.MAX_VALUE)
                                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(36, 36, 36)
                                                .addComponent(textUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(48, 48, 48)
                                                .addComponent(butonTelefon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(49, 49, 49)
                                                .addComponent(textPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(65, 65, 65)
                                                .addComponent(butonAdd)
                                                .addGap(115, 115, 115))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    //metoda de actionPerformed pt butonul  Add
    private void butonAddActionPerformed(java.awt.event.ActionEvent evt) {
        //memorarea datelor introduse in textfielduri
        user = textUser.getText();
        telefon = butonTelefon.getText();
        pass = textPass.getText();

        if(user.isEmpty() || pass.isEmpty() || telefon.isEmpty()){
            JOptionPane.showMessageDialog(null, "Completati toate randurile");
        }else{
            try {
                String userInsertSQLQuerty = "INSERT INTO Users5 (user1,telefon,password1) VALUES (?,?,?)";
                PreparedStatement userPST = this.connx.prepareStatement(userInsertSQLQuerty);

                userPST.setString(1,user);
                userPST.setString(2,telefon);
                userPST.setString(3,pass);

                int userInsered = userPST.executeUpdate();
                if(userInsered > 0){
                    JOptionPane.showMessageDialog(null, "Update facut cu succes");
                    clearAllInputFields();
                    populatejTable1FromMysqlDatabase();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        new Useri().setVisible(true);

    }



    private void clearAllInputFields() {
        textUser.setText("");
        textPass.setText("");
    }
}
