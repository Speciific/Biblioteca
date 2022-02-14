package com.company;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class AddCarte extends JFrame {

    //declararea componentelor
    private JTextField anCarte;
    private JTextField autorCarte;
    private JButton butonClear;
    private JButton butonOK;
    private JTextField disponibilCarte;
    private JTextField edituraCarte;
    private JTextField numeCarte;

    //memorarea datelor pt o conexiune permanenta cu baza de date si declararea unor date ce vor modifica baza de date
    String url = "jdbc:mysql://localhost:3305/mysql";
    String username = "root";
    String password = "numaistiuparola";
    String Nume = "";
    String Autor = "";
    String Editura = "";
    int NrCarte;
    int An = 0;
    boolean Disponibil;
    Connection connx;

    public AddCarte() {
        initComponents();
        connx = databaseConnection();
    }

    public Connection databaseConnection(){
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    private void initComponents() {

        numeCarte = new JTextField();
        autorCarte = new JTextField();
        edituraCarte = new JTextField();
        anCarte = new JTextField();
        disponibilCarte = new JTextField();
        butonOK = new JButton();
        butonClear = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adaugare carti");
        //setResizable(false);
        setBackground(new Color(51, 0, 102));

        numeCarte.setText("Nume carte");

        autorCarte.setText("Autor");

        edituraCarte.setText("Editura");

        anCarte.setText("Anul");

        disponibilCarte.setText("Disponibil");

        butonOK.setText("OK");
        butonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonOKActionPerformed(evt);
            }
        });

        butonClear.setText("Clear");
        butonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonClearActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(172, 172, 172)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(disponibilCarte, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(numeCarte, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(autorCarte, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(edituraCarte, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(anCarte, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(butonOK)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(butonClear)))
                                .addContainerGap(202, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(numeCarte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(autorCarte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(edituraCarte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(anCarte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(disponibilCarte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(butonOK)
                                        .addComponent(butonClear))
                                .addContainerGap(129, Short.MAX_VALUE))
        );

        pack();
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

    //metoda de actionPerformed pt butonul Ok
    private void butonOKActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<Cartee> dataArray = userList();
        //memorarea datelor introduse in textfielduri
        Nume = numeCarte.getText();
        Autor = autorCarte.getText();
        Editura = edituraCarte.getText();
        An = Integer.parseInt(anCarte.getText());
        NrCarte = dataArray.get(dataArray.size()-1).getNrCarte();
        NrCarte++;
        Disponibil = Boolean.parseBoolean(disponibilCarte.getText());

        if(Nume.isEmpty() || Autor.isEmpty() || Editura.isEmpty() || anCarte.getText().isEmpty() || disponibilCarte.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Completati toate randurile");
        }else{
            try {
                //introducerea/memorarea unor date noi in baza de date
                String carteInsertSQLQuerty = "INSERT INTO Cart1 (NrCarte,Nume,Autor,Editura,Anul,Disponibil) VALUES (?,?,?,?,?,?)";
                PreparedStatement cartePST = this.connx.prepareStatement(carteInsertSQLQuerty);
                cartePST.setInt(1,NrCarte);
                cartePST.setString(2,Nume);
                cartePST.setString(3,Autor);
                cartePST.setString(4,Editura);
                cartePST.setInt(5,An);
                cartePST.setBoolean(6,Disponibil);
                int carteInsered = cartePST.executeUpdate();
                //verificare daca s a adaugat o carte
                if(carteInsered > 0){
                    JOptionPane.showMessageDialog(null, "Update facut cu succes");
                    //stergerea textului deja introdus in textfielduri
                    clearAllInputFields();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    //metoda de actionPerformed pt butonul Cautare Ok
    private void butonClearActionPerformed(java.awt.event.ActionEvent evt) {
        clearAllInputFields();
    }

    public static void main(String[] args) {

        new AddCarte().setVisible(true);

    }
    // metoda pentru "stergerea" textului introdus in textfielduri
    private void clearAllInputFields() {
        numeCarte.setText("");
        autorCarte.setText("");
        edituraCarte.setText("");
        anCarte.setText("");
        disponibilCarte.setText("");
    }


}

