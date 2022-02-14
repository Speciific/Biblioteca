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

public class Imprumut extends JFrame {

    //declararea componentelor
    private JToggleButton butonImprumuta;
    private JToggleButton butonReturneaza;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JTextField textImprumuta;
    private JTextField textReturneaza;

    //memorarea datelor pt o conexiune permanenta cu baza de date
    String url = "jdbc:mysql://localhost:3305/mysql";
    String username = "root";
    String password = "numaistiuparola";
    Connection connx;
    DefaultTableModel model;

    public Imprumut() {
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

    public ArrayList<Cartee> userList() {
        ArrayList<Cartee> userList = new ArrayList<Cartee>();
        String sql = "SELECT * FROM cart1";
        Statement st;
        ResultSet rs;

        try {
            st = connx.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Cartee user = new Cartee();
                user.setNrCarte(rs.getInt("NrCarte"));
                user.setAnul(rs.getInt("Anul"));
                user.setNume(rs.getString("Nume"));
                user.setAutor(rs.getString("Autor"));
                user.setEditura(rs.getString("Editura"));
                user.setDisponibil(rs.getBoolean("Disponibil"));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void populatejTable1FromMysqlDatabase() {
        ArrayList<Cartee> dataArray = userList();
        model = (DefaultTableModel) jTable1.getModel();

        model.setRowCount(0);
        Object[] rows = new Object[6];

        for (int i = 0; i < dataArray.size(); i++) {
            rows[0] = dataArray.get(i).getNrCarte();
            rows[1] = dataArray.get(i).getNume();
            rows[2] = dataArray.get(i).getAutor();
            rows[3] = dataArray.get(i).getEditura();
            rows[4] = dataArray.get(i).getAnul();
            rows[5] = dataArray.get(i).getDisponibil();
            boolean ok = dataArray.get(i).getDisponibil();
            if(ok)
                model.addRow(rows);
        }
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        textImprumuta = new JTextField();
        butonImprumuta = new JToggleButton();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        textReturneaza = new JTextField();
        butonReturneaza = new JToggleButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Imprumut");
        //setResizable(false);

        jPanel1.setForeground(new Color(102, 0, 153));

        jLabel1.setFont(new Font("Tahoma", 0, 24));
        jLabel1.setText("Carti disponibile");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "NrCarte", "Carte", "Autor", "Editura", "Anul", "Disponibil"
                }
        ) {
            Class[] types = new Class [] {
                    Integer.class, String.class, String.class, String.class, Integer.class, Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        butonImprumuta.setText("Imprumuta");
        butonImprumuta.setActionCommand("Imprumuta");
        butonImprumuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonImprumutaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new Font("Tahoma", 0, 24));
        jLabel2.setText("Ce carte vrei sa imprumuti?");

        jLabel3.setFont(new Font("Tahoma", 0, 24));
        jLabel3.setText("Ce carte vrei sa returnezi??");

        butonReturneaza.setText("Returneaza");
        butonReturneaza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonReturneazaActionPerformed(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(63, 63, 63)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(23, 23, 23)
                                                                .addComponent(textImprumuta, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(152, 152, 152)
                                                .addComponent(butonImprumuta)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addComponent(textReturneaza, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(116, 116, 116))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel3)
                                                        .addGap(72, 72, 72)))
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(butonReturneaza)
                                                .addGap(193, 193, 193))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(284, 284, 284)
                                                .addComponent(jLabel1))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(159, 159, 159)
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3))
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(butonImprumuta)
                                                        .addComponent(butonReturneaza))
                                                .addGap(91, 91, 91))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(45, 45, 45)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(textImprumuta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textReturneaza, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))))
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

    //metoda de actionPerformed pt butonul Imprumuta
    private void butonImprumutaActionPerformed(java.awt.event.ActionEvent evt) {
        String imprumutCarte = textImprumuta.getText();
        if(imprumutCarte.isEmpty()){
            JOptionPane.showMessageDialog(null, "Completati randul");
        }else{
            try {
                String carteInsertSQLQuerty = "UPDATE Cart1 set Disponibil = false where Nume=?";//where Nume=?
                PreparedStatement cartePST = this.connx.prepareStatement(carteInsertSQLQuerty);
                cartePST.setString(1, imprumutCarte);
                int carteInsered = cartePST.executeUpdate();
                if(carteInsered > 0){
                    JOptionPane.showMessageDialog(null, "Update facut cu succes");
                    clearAllInputFields();
                    populatejTable1FromMysqlDatabase();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    //metoda de actionPerformed pt butonul returneaza
    private void butonReturneazaActionPerformed(java.awt.event.ActionEvent evt) {
        String returnareCarte = textReturneaza.getText();

        if(returnareCarte.isEmpty()){
            JOptionPane.showMessageDialog(null, "Completati randul");
        }else{
            try {
                String carteInsertSQLQuerty = "UPDATE Cart1 set Disponibil = true where Nume=?";//where Nume=?
                PreparedStatement cartePST = this.connx.prepareStatement(carteInsertSQLQuerty);
                cartePST.setString(1, returnareCarte);
                int carteInsered = cartePST.executeUpdate();
                if(carteInsered > 0){
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
        new Imprumut().setVisible(true);
    }

    private void clearAllInputFields() {
        textImprumuta.setText("");
        textReturneaza.setText("");
    }
}

