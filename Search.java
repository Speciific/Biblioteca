package com.company;

import java.awt.*;
import java.sql.Statement;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Search extends JFrame {

    //declararea componentelor
    private JButton butonCautare;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JTextField textCautare;

    //memorarea datelor pt o conexiune permanenta cu baza de date
    String url = "jdbc:mysql://localhost:3305/mysql";
    String username = "root";
    String password = "numaistiuparola";
    String search = "";
    Connection connx;
    DefaultTableModel model;

    public Search() {
        initComponents();
        connx = databaseConnection();
        //apelarea datelor pentru tabel
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

    //adaugarea datelor din data de baze intr un vector si adaugarea lor sub forma unui tabel
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
            model.addRow(rows);
        }
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        textCautare = new JTextField();
        butonCautare = new JButton();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cautare carti");
        //setResizable(false);

        jPanel1.setBackground(new Color(0, 204, 102));

        butonCautare.setBackground(new Color(255, 255, 0));
        butonCautare.setText("Cauta");
        butonCautare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonCautareActionPerformed(evt);
            }
        });

        // initializarea unui tabel de tip obiect
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                //numele coloanelor
                new String [] {
                        "NrCarte", "Carte", "Autor", "Editura", "Anul", "Disponibil"
                }
        ) {
            // tipula variabilelor din tabel
            Class[] types = new Class [] {
                    Integer.class, String.class, String.class, String.class, Integer.class, Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });

        //initializarea unui scroll pt tabel
        jScrollPane1.setViewportView(jTable1);

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(64, 64, 64)
                                                .addComponent(textCautare, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE)
                                                .addGap(72, 72, 72)
                                                .addComponent(butonCautare))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(47, 47, 47)
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 606, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(textCautare, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(butonCautare))
                                .addGap(42, 42, 42)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(60, Short.MAX_VALUE))
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

    //metoda de actionPerformed pt butonul Cautare
    private void butonCautareActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<Cartee> dataArray = userList();
        model = (DefaultTableModel) jTable1.getModel();

        //setarea randurilor pt tabel acesta  fiind o pt a putea afisa datele din baza de date altfel vor fi randuri goale
        model.setRowCount(0);
        Object[] rows = new Object[6];

        search = textCautare.getText();
        // verificarea daca s a cautat ceva
        if (search.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nu ati cautat nimic");
        } else {
            for (int i = 0; i < dataArray.size(); i++) {
                rows[0] = dataArray.get(i).getNrCarte();
                rows[1] = dataArray.get(i).getNume();
                rows[2] = dataArray.get(i).getAutor();
                rows[3] = dataArray.get(i).getEditura();
                rows[4] = dataArray.get(i).getAnul();
                rows[5] = dataArray.get(i).getDisponibil();
                //verificare daca ce s a cautat este si in baza de date
                if(search.equals(rows[0])|| search.equals(rows[1])||search.equals(rows[2])||search.equals(rows[3])||search.equals(rows[4])||search.equals(rows[5])){
                    model.addRow(rows);
                }
            }
        }
    }



    public static void main(String[] args) {

        new Search().setVisible(true);

    }

}

