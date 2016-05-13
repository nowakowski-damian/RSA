/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thirteendolars.windows;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import javax.swing.ImageIcon;

/**
 *
 * @author damian
 */
public class InfoDialog extends javax.swing.JDialog implements MouseListener {

    private final String EMAIL = "damian.nowakowski@aol.com";
    private final String WEBSITE = "www.student.agh.edu.pl/damiann";
    private final String GITHUB = "www.github.com/thirteendollars/RSA";
    private final String CONTENT = "RSA encryption/decryption desktop application has been written by me "
            + "in order to complete Cryptography course at my University."
            + "Feel free to copy and re-use app code as needed,but I would appreciate it "
            + "if you give me a credit for any work of mine that you use.";

    private javax.swing.JLabel tittleLabel;
    private javax.swing.JLabel contentLabel;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JTextPane websiteLabel;
    private javax.swing.JTextPane mailLabel;
    private javax.swing.JTextPane githubLabel;
    private javax.swing.JLabel contactLabel;
    private javax.swing.JLabel sourceLabel;

    public InfoDialog(java.awt.Frame parent) {
        super(parent);
        initComponents();
        this.setResizable(false);
        this.setTitle("Info");
        this.setLocationRelativeTo(getContentPane());
    }

    private void initComponents() {

        imageLabel = new javax.swing.JLabel(new ImageIcon(InfoDialog.class.getResource("/agh_logo.png")));
        tittleLabel = new javax.swing.JLabel();
        contentLabel = new javax.swing.JLabel();
        websiteLabel = new javax.swing.JTextPane();
        mailLabel = new javax.swing.JTextPane();
        githubLabel = new javax.swing.JTextPane();

        contactLabel = new javax.swing.JLabel();
        sourceLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tittleLabel.setText("About RSA");
        tittleLabel.setFont(new Font("Serif", Font.BOLD, 20));

        contentLabel.setText("<html>" + CONTENT + "</html>");

        websiteLabel.setText(WEBSITE);
        websiteLabel.setFont(new Font("Serif", Font.BOLD, 13));
        websiteLabel.setEditable(false);
        websiteLabel.setBackground(null);
        websiteLabel.setBorder(null);
        websiteLabel.setForeground(Color.blue);
        websiteLabel.addMouseListener(this);

        mailLabel.setText(EMAIL);
        mailLabel.setFont(new Font("Serif", Font.BOLD, 13));
        mailLabel.setEditable(false);
        mailLabel.setBackground(null);
        mailLabel.setBorder(null);
        mailLabel.setForeground(Color.blue);
        mailLabel.addMouseListener(this);

        githubLabel.setText(GITHUB);
        githubLabel.setFont(new Font("Serif", Font.BOLD, 13));
        githubLabel.setEditable(false);
        githubLabel.setBackground(null);
        githubLabel.setBorder(null);
        githubLabel.setForeground(Color.blue);
        githubLabel.addMouseListener(this);
        contactLabel.setText("Contact:");
        sourceLabel.setText("Repository:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(imageLabel, 187, 187, 187)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(githubLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(mailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(websiteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(contentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tittleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(contactLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sourceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        ))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(imageLabel, 345, 345, 345)
                .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(tittleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(contentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(contactLabel)
                        .addGap(5, 5, 5)
                        .addComponent(websiteLabel)
                        .addGap(5, 5, 5)
                        .addComponent(mailLabel)
                        .addGap(15, 15, 15)
                        .addComponent(sourceLabel)
                        .addGap(5, 5, 5)
                        .addComponent(githubLabel)
                ));

        pack();
    }

    private void openURL(String url) {

        if (Desktop.isDesktopSupported()) {
            try {

                if (url == EMAIL) {
                    url = "mailto:" + url;
                    URI uri = new URI(url);
                    Desktop.getDesktop().mail(uri);
                } else {
                    url = "http://" + url;
                    URI uri = new URI(url);
                    Desktop.getDesktop().browse(uri);
                }
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == websiteLabel) {
            openURL(WEBSITE);
        } else if (e.getSource() == mailLabel) {
            openURL(EMAIL);
        } else if (e.getSource() == githubLabel) {
            openURL(GITHUB);
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }

}
