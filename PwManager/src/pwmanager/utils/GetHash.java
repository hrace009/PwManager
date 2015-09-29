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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;

public class GetHash {

    String senhaquebrada;
    String senhapesquisada;
    int codigoretorno;

    public static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public GetHash(String senha, String login, mysqlconfig sqlconfig) throws MalformedURLException, IOException, JSONException {
        if (sqlconfig.sqlhash == 0) {
            senha = senha.substring(2);
            System.out.println(senha);
            String urlbuild = "http://api.md5crack.com/crack/HyilnvMR4KWHpfc1/" + senha;
            URL url = new URL(urlbuild);
            InputStream is = url.openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            if (jsonText != null) {
                JSONObject obj = new JSONObject(jsonText);
                senhaquebrada = obj.getString("phrase");
                senhapesquisada = obj.getString("parsed");
                codigoretorno = obj.getInt("code");
                senhaquebrada = senhaquebrada.replaceFirst(login, "");
                if (senhaquebrada.equals("null")) {
                    JOptionPane.showMessageDialog(null, "Não foi possivel quebrar senha !\nlogin: " + login + "\nCódigo pesquisado: " + senhapesquisada + "", "Erro !", 0);
                } else {
                    if (codigoretorno == 6) {
                        JOptionPane.showMessageDialog(null, "Senha quebrada com sucesso !\nlogin: " + login + "\nsenha: " + senhaquebrada + "\nCódigo pesquisado: " + senhapesquisada + "", "Sucesso !", 1);
                    }
                }

            } 
        } else {
                JOptionPane.showMessageDialog(null, "Disponível somente para senhas 0xMD5", "Erro !", 0);
            }
    }
}
