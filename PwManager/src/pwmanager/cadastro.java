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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import pwmanager.utils.md5;
import pwmanager.utils.mysqlconfig;
import pwmanager.utils.refreshJtable;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author Adriano
 */
public class cadastro extends javax.swing.JFrame {

    byte[] salt;
    String login;
    Boolean isloginvalido;
    String senha;
    Boolean issenhavalido;
    String senhafinal;
    String email;
    Boolean isemailvalido;
    String pergunta;
    String resposta;
    String genero;
    String presenha;
    Blob blob;
    public static String filtro = "^[A-Za-z0-9_.]+$";
    Statement st = null;
    ResultSet rs = null;
    Connection con;
    mysqlconfig sqlconfig;
    md5 md5 = new md5();
    UI front;

    public cadastro(Connection con, mysqlconfig sqlconfig, UI front) {
        initComponents();
        setVisible(true);
        this.con = con;
        this.sqlconfig = sqlconfig;
        this.front = front;
        setIconImage(this.front.imageapp);
        this.front.setEnabled(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                front.setEnabled(true);
                dispose();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastrar conta");
        setResizable(false);

        jLabel1.setText("Login");

        jLabel2.setText("Senha");

        jLabel3.setText("Repetir senha");

        jLabel4.setText("Email");

        jLabel5.setText("Pergunta secreta");

        jLabel6.setText("Resposta");

        jLabel7.setText("Genêro");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Feminino" }));

        jButton1.setText("Cadastrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)
                            .addComponent(jTextField4)
                            .addComponent(jTextField5)
                            .addComponent(jTextField6)
                            .addComponent(jComboBox1, 0, 124, Short.MAX_VALUE))))
                .addGap(26, 26, 26))
            .addGroup(layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jButton1)
                .addContainerGap(104, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        login = jTextField1.getText();
        isloginvalido = IsLoginValid(login);
        senha = jTextField2.getText();
        issenhavalido = IsSenhaValid(senha);
        issenhavalido = jTextField2.getText().equals(jTextField3.getText());
        email = jTextField4.getText();
        isemailvalido = isEmailValid(email);
        pergunta = jTextField5.getText();
        resposta = jTextField6.getText();
        genero = String.valueOf(jComboBox1.getSelectedIndex() + 1);
        login = login.toLowerCase();
        
        // TIPOS DE SENHA 
        
        
        if (sqlconfig.sqlhash == 0) {
            try {
                senhafinal = MD5(login.concat(senha));
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }
            senhafinal = "0x".concat(senhafinal);
        }
        if (sqlconfig.sqlhash == 1) {
            try {
                byte[] codificado;
                codificado = pw_encode(login.concat(senha), MessageDigest.getInstance("MD5"));
                senhafinal = Base64.encode(codificado);
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }

        if (sqlconfig.sqlhash == 2) {
            try {

                salt = pw_encode(login.concat(senha), MessageDigest.getInstance("MD5"));
                senhafinal = new String(salt, "cp1252");
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }
        
        // FIM TIPOS DE SENHA
        
        
        //EFETUA CADASTRO
        if (isloginvalido && issenhavalido && isemailvalido && pergunta.length() >= 4 && resposta.length() >= 4) {
            try {
                String query = "call adduser('" + login + "','" + senhafinal + "','" + pergunta + "','" + resposta + "','" + login + "','0.0.0.0','" + email + "','0','0','0','0','0','0','" + Integer.valueOf(genero) + "','0000-00-00 00:00:00','0','" + senhafinal + "')";
                st = con.createStatement();
                rs = st.executeQuery(query);
                JOptionPane.showMessageDialog(rootPane, "Cadastro efetuado com sucesso", "Sucesso!", 1);
                new refreshJtable(con, front);
            } catch (SQLException e) {
                int ex;
                e.printStackTrace();
                ex = e.getErrorCode();
                if (ex == 1062) {
                    JOptionPane.showMessageDialog(rootPane, "Login já em uso / Chave duplicada", "Erro", 0);
                }
            }
        }
        
        // FIM EFETUA CADASTRO

    }//GEN-LAST:event_jButton1ActionPerformed
    
    //Metodos DE FILTRAGEM
    
    public static boolean isEmailValid(String email) {
        if ((email == null) || (email.trim().length() == 0)) {
            return false;
        } else {
            String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
            Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }

    }

    public static boolean IsLoginValid(String login) {
        if (login != null && (login.trim().length() != 0) && (login.length() >= 4 && login.length() <= 12)) {
            return login.matches(filtro);
        }
        return false;
    }

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
            e.printStackTrace();
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
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables

}
