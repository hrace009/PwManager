/*
 * Copyright (C) 2015 Adriano
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pwmanager;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static pwmanager.cadastro.filtro;
import pwmanager.utils.mysqlconfig;
import pwmanager.utils.refreshJtable;

/**
 *
 * @author Adriano
 */
public class forcasenha extends javax.swing.JFrame {

    String conta;
    int id;
    Statement st = null;
    ResultSet rs = null;
    Connection con;
    mysqlconfig sqlconfig;
    UI front;
    Boolean senhavalida;
    String senhafinal;
    byte[] salt;

    public forcasenha(String conta, int id, Connection con, UI front, mysqlconfig sqlconfig) {
        initComponents();
        this.front = front;
        this.conta = conta;
        this.id = id;
        this.con = con;
        this.sqlconfig = sqlconfig;
        jTextField1.setText(String.valueOf(id));
        jTextField2.setText(conta);
        setVisible(true);
        setIconImage(front.imageapp);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Senha");
        setResizable(false);

        jTextField1.setEditable(false);
        jTextField1.setFocusable(false);

        jTextField2.setEditable(false);
        jTextField2.setFocusable(false);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel1.setText("ID");

        jLabel2.setText("Login");

        jLabel3.setText("Nova senha");

        jLabel4.setText("Nova senha");

        jButton1.setText("Confirmar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(jTextField2)
                    .addComponent(jTextField1)
                    .addComponent(jTextField5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if (jTextField3.getText().equals(jTextField5.getText())) {
            senhavalida = IsSenhaValid(jTextField3.getText());
            if (senhavalida) {

                if (sqlconfig.sqlhash == 0) {
                    try {
                        senhafinal = MD5(conta.concat(jTextField3.getText()));
                    } catch (UnsupportedEncodingException ex) {
                    } catch (NoSuchAlgorithmException ex) {
                    }
                    senhafinal = "0x".concat(senhafinal);
                }
                if (sqlconfig.sqlhash == 1) {
                    try {
                        byte[] codificado;
                        codificado = pw_encode(conta.concat(jTextField3.getText()), MessageDigest.getInstance("MD5"));
                        senhafinal = Base64.encode(codificado);
                    } catch (NoSuchAlgorithmException ex) {
                    } catch (UnsupportedEncodingException ex) {

                    }
                }

                if (sqlconfig.sqlhash == 2) {
                    try {
                        salt = pw_encode(conta.concat(jTextField3.getText()), MessageDigest.getInstance("MD5"));
                        senhafinal = new String(salt, "cp1252");
                    } catch (NoSuchAlgorithmException ex) {
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(cadastro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                try {
                    String query = "UPDATE users SET passwd='" + senhafinal + "' WHERE name='" + conta + "' AND ID='" + id + "';"; 
                    st = con.createStatement();
                    st.executeUpdate(query);
                    JOptionPane.showMessageDialog(rootPane, "Senha alterada com sucesso !\nNova senha definida: " + jTextField3.getText(), "Sucesso !", 1);
                    new refreshJtable(con, front);
                    this.dispose();
                }
                catch (SQLException e ) {
                    JOptionPane.showMessageDialog(rootPane, "Não foi possivel efetuar a troca de senha\nRazão: " + e.getMessage(), "Erro !", 0);
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "Senha invalida", "Erro!", 0);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Senhas não conferem", "Erro!", 0);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    public static boolean IsSenhaValid(String senha) {
        if (senha != null && (senha.trim().length() != 0) && (senha.length() >= 4 && senha.length() <= 12)) {
            return senha.matches(filtro);
        }
        return false;
    }

    public String MD5(String md5) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));//aplica a codificação
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {//converte os bytes para uma string hexadecimal
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();//retorna a string
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new java.security.NoSuchAlgorithmException("Não foi possível aplicar o hash");
        }
    }

    public byte[] pw_encode(String salt, MessageDigest alg) throws UnsupportedEncodingException {
        alg.reset();
        byte[] presenha = salt.getBytes("UTF-8");
        presenha = alg.digest(presenha);
        return presenha;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
