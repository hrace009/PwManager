package pwmanager.utils;

import java.sql.*;
import javax.swing.JOptionPane;

public class MySQLcon {

    public static Connection conect(mysqlconfig sqlconfig) throws ClassNotFoundException {
        String conexao = "jdbc:mysql://" + sqlconfig.mysqlip + ":" + sqlconfig.mysqlport + "/" + sqlconfig.mysqldb;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(conexao, sqlconfig.mysqluser, sqlconfig.mysqlpasswd);
            
            return con;
        } catch (SQLException error) {
            
            JOptionPane.showMessageDialog(null, "Erro de comunicação entre servidores ! ( avá )\nVerifique se o firewall esta aberto e o endereço da conexão correto.\nConsidere verificar login e senha, e se devido usuário possui permissões.\nErro Detalhado: " + error.getMessage(), "Erro MySQL", 0);
            return null;
        }
    }
}
