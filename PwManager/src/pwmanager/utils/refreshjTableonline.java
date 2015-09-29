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
package pwmanager.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import pwmanager.UI;

/**
 *
 * @author Adriano
 */
public class refreshjTableonline {

    UI front;
    Connection con;
    Statement st;
    ResultSet rs;

    public refreshjTableonline(Connection con, UI front) {
        this.con = con;
        this.front = front;
        
        String query = "SELECT * FROM users INNER JOIN point ON users.id = point.uid and point.zoneid=1";
        try {

            if (this.front.tbOnlines.getRowCount() > 0) {
                for (int i = this.front.tbOnlines.getRowCount() - 1; i > -1; i--) {
                    this.front.tbOnlines.removeRow(i);
                }
            }

            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                String[] contasStr = new String[]{String.valueOf(rs.getInt("ID")), rs.getString("name"), rs.getString("lastlogin")};
                this.front.tbOnlines.addRow(contasStr);
            }
            this.front.jLabel7.setText(String.valueOf(this.front.jTable2.getRowCount()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(front, "Não foi possivel atualizar a lista\nRazão: " + e.getMessage());
        }
        
    }
}
