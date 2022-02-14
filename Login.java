package com.company;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;

public class Login extends JFrame {

    //declararea componentelor
    private JLabel admin;
    private JButton butonCancel;
    private JButton butonLogin;
    private JPanel jPanel1;
    private JPasswordField parola;
    private JTextField user;

    //memorarea datelor pt o conexiune permanenta cu baza de date
    String url = "jdbc:mysql://localhost:3305/mysql";
    String username = "root";
    String password = "numaistiuparola";
    Connection connx;

    //metoda pentru login
    public Login() {
        //initializarea partii grafice
        initComponents();
        //memorarea conexiunii intr o variabila
        connx = databaseConnection();
    }

    //metoda pentru conectare cu serverul
    public Connection databaseConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, username, password);  // conectare la baza de date
            return conn;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    //metoda pentru memorarea datelor intr un array list
    public ArrayList<User> userList() {
        ArrayList<User> userList = new ArrayList<User>();
        String sql = "SELECT * FROM Users5";
        Statement st;
        ResultSet rs;
        try {
            st = connx.createStatement();
            rs = st.executeQuery(sql);
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

    //partea grafica
    private void initComponents() {

        // declararea componentelor
        jPanel1 = new JPanel();
        admin = new JLabel();
        user = new JTextField();
        parola = new JPasswordField();
        butonLogin = new JButton();
        butonCancel = new JButton();

        //setarea daca se apasa pe butonul prestabilit close se opreste tot programul
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Logare"); // setarea titlului ferestrei
        setIconImage(new ImageIcon("image.jpg").getImage()); // setarea unei iconite pentru fereastra
        //setResizable(false); // nu se mai poate redimensiona fereastra


        jPanel1.setBackground(new Color(255, 255, 255)); // setarea jpanelului ca bg si setarea culorii fundalului

        admin.setFont(new Font("Tahoma", 0, 48)); // setarea unui font ales pentru text
        admin.setText("Admin"); // setarea textului

        // setarea unui border pt textfield si setarea unui font ales
        user.setBorder(BorderFactory.createTitledBorder(null, "User", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new Font("Tahoma", 0, 14)));

        parola.setBorder(BorderFactory.createTitledBorder(null, "Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new Font("Tahoma", 0, 14)));

        butonLogin.setFont(new Font("Tahoma", 0, 24));
        butonLogin.setText("Login");
        //setarea unei actiuni pentru butonul de login
        butonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonLoginActionPerformed(evt);
            }
        });

        butonCancel.setFont(new Font("Tahoma", 0, 24));
        butonCancel.setText("Cancel");
        butonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonCancelActionPerformed(evt);
            }
        });

        //setarea unui grup de layout si costumizarea elementelor din acest grup atat pe orizontal cat si pe vertical
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);

        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(168, 168, 168)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(user)
                                                .addComponent(parola, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 65, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(admin)
                                                .addGap(61, 61, 61)))
                                .addContainerGap(172, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(butonLogin)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(butonCancel)
                                .addGap(116, 116, 116))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(admin)
                                .addGap(90, 90, 90)
                                .addComponent(user, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(parola, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(butonLogin)
                                        .addComponent(butonCancel))
                                .addGap(99, 99, 99))
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

    //metoda de actionPerformed pt butonul Login
    private void butonLoginActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<User> dataArray = userList();
        int k = 0;
        String[] rows = new String[3];

        //verificare daca spatiile sunt goale
        if (user.getText().isEmpty() || parola.getText().isEmpty()) {
            // se afiseza un mesajDialog
            JOptionPane.showMessageDialog(Login.this, "Nu ati introdus user-ul sau parola");
            //daca nu sunt introduse date in textfield si se apasa pe butonul de login atunci se capteaza focusul pe componenta textfield user/parola
            user.grabFocus();
            parola.grabFocus();
            return;
        }
        for (int i = 0; i < dataArray.size(); i++) {
            k++;
            //memoreaza datele din baza de date in vectorul rows
            rows[0] = dataArray.get(i).getuser1();
            rows[2] = dataArray.get(i).getpassword1();
            //verifica daca textul dat in textfield urile sunt la fel cu variabilele din baza de date
            if (user.getText().equals(rows[0]) && parola.getText().equals(rows[2])) {
                JOptionPane.showMessageDialog(Login.this, "Logare reusita");// se afiseza un mesajDialog
                dispose();// inchidere fereastra
                Alegere u1 = new Alegere(); // se deschide urmatoarea fereastra
                u1.setVisible(true); // este vizibila
                break;
            } else {
                if(k>=dataArray.size()) // se verifica daca datele date in textfielduri nu au fost la fel cu una din datele din data de baza
                    JOptionPane.showMessageDialog(Login.this, "Nu ati introdus user-ul sau parola corecta");// se afiseza un mesajDialog
            }
        }
        //System.exit(0);
    }

    //metoda de actionPerformed pt butonul Cancel
    private void butonCancelActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    } //se opreste aplicatia

    //metoda main
    public static void main(String[] args) {
        new Login().setVisible(true);
    }

}
