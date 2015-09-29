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

public class refreshJtableGM {

    UI front;
    Connection con;
    Statement st;
    ResultSet rs;
    int count;

    public refreshJtableGM(Connection con, UI front) {
        this.con = con;
        this.front = front;

        String query = "SELECT DISTINCT ID,name,lastlogin FROM users  INNER JOIN point ON users.id = point.uid INNER JOIN auth  ON users.id = auth.userid";
        try {

            if (this.front.tbGM.getRowCount() > 0) {
                for (int i = this.front.tbGM.getRowCount() - 1; i > -1; i--) {
                    this.front.tbGM.removeRow(i);

                }
            }

            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                String[] contasStr = new String[]{String.valueOf(rs.getInt("ID")), rs.getString("name"), rs.getString("lastlogin")};
                this.front.tbGM.addRow(contasStr);
                this.count++;
            }
            this.front.jLabel9.setText(String.valueOf(this.count));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(front, "Não foi possivel atualizar a lista\nRazão: " + e.getMessage());
        }

    }

}
