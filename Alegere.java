package com.company;

import javax.swing.*;
import java.awt.*;

public class Alegere extends JFrame {

    //declararea componentelor
    private JButton adaugareCarte;
    private JButton cautareCarte;
    private JButton cautareUser;
    private JButton imprumutareCarte;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JPanel jPanel2;

    //metoda pentru fereastra
    public Alegere() {
        //initializare grafica
        initComponents();
    }

    //partea grafica
    private void initComponents() {

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jPanel2 = new JPanel();
        cautareCarte = new JButton();
        adaugareCarte = new JButton();
        imprumutareCarte = new JButton();
        cautareUser = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Biblioteca");
        //setResizable(false);
        ImageIcon imagine = new ImageIcon("Carte.jpg");
        jLabel1.setIcon(imagine);

        jPanel1.setBackground(new Color(255, 255, 255));

        jLabel2.setFont(new Font("Tahoma", 2, 36));
        jLabel2.setText("Biblioteca");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(58, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(96, 96, 96))
        );

        jPanel2.setBackground(new Color(255, 0, 102));

        cautareCarte.setFont(new Font("Tahoma", 0, 24));
        cautareCarte.setText("Search");
        cautareCarte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cautareCarteActionPerformed(evt);
            }
        });

        adaugareCarte.setFont(new Font("Tahoma", 0, 24));
        adaugareCarte.setText("Adaugare carte");
        adaugareCarte.setToolTipText("");
        adaugareCarte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adaugareCarteActionPerformed(evt);
            }
        });

        imprumutareCarte.setFont(new Font("Tahoma", 0, 24));
        imprumutareCarte.setText("Imprumutare carte");
        imprumutareCarte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprumutareCarteActionPerformed(evt);
            }
        });

        cautareUser.setFont(new Font("Tahoma", 0, 24));
        cautareUser.setText("Users");
        cautareUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cautareUserActionPerformed(evt);
            }
        });

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(cautareCarte)
                                        .addComponent(cautareUser))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(adaugareCarte)
                                                .addGap(100, 100, 100))
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(imprumutareCarte)
                                                .addGap(81, 81, 81))))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(adaugareCarte)
                                        .addComponent(cautareUser))
                                .addGap(59, 59, 59)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(imprumutareCarte)
                                        .addComponent(cautareCarte))
                                .addContainerGap(85, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }

    //metoda de actionPerformed pt butonul Cautare Carte
    private void cautareCarteActionPerformed(java.awt.event.ActionEvent evt) {
        //declararea unei variabile de tip Search si afisarea ei pe ecran
        Search u1 = new Search();
        u1.setVisible(true);
    }

    //metoda de actionPerformed pt butonul Cautare User
    private void cautareUserActionPerformed(java.awt.event.ActionEvent evt) {
        //declararea unei variabile de tip User si afisarea ei pe ecran
        Useri u2 = new Useri();
        u2.setVisible(true);
    }

    //metoda de actionPerformed pt butonul Adaugare Carte
    private void adaugareCarteActionPerformed(java.awt.event.ActionEvent evt) {
        //declararea unei variabile de tip AddCarte si afisarea ei pe ecran
        AddCarte u3 = new AddCarte();
        u3.setVisible(true);
    }

    //metoda de actionPerformed pt butonul Imprumutare Carte
    private void imprumutareCarteActionPerformed(java.awt.event.ActionEvent evt) {
        //declararea unei variabile de tip Imprumut si afisarea ei pe ecran
        Imprumut u4 = new Imprumut();
        u4.setVisible(true);
    }

    public static void main(String[] args) {

        new Alegere().setVisible(true);

    }

}

