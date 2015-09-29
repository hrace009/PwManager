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
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import org.json.JSONException;
import org.json.JSONObject;
import pwmanager.UI;

/**
 *
 * @author Adriano
 */
public class ApiManager {

    UI front;
    mysqlconfig sqlconfig;
    public String URLBuild;
    public String result;
    public String resultAcc;
    public Boolean isvalidapi = false;

    public ApiManager(mysqlconfig sqlconfig, UI front) throws NoSuchAlgorithmException, IOException, UnsupportedEncodingException, MalformedURLException, JSONException {
        this.sqlconfig = sqlconfig;
        this.front = front;
        isvalidapi = ApiCheck();
    }

    public String QueryName(int id) throws UnsupportedEncodingException, NoSuchAlgorithmException, IOException, MalformedURLException, JSONException {
        URLBuild = sqlconfig.apiurl + "index.php?password=" + md5.MD5(sqlconfig.apipasswd) + "&queryid&id=" + id;
        APIRequest(URLBuild);
        return this.result;
    }

    public void ManageTW(String type) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException, MalformedURLException, JSONException {
        URLBuild = sqlconfig.apiurl + "index.php?password=" + md5.MD5(sqlconfig.apipasswd) + "&tw_start&type=" + type;
        APIRequest(URLBuild);
    }
    
    public void QueryAcc(String name) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException, MalformedURLException, JSONException {
        URLBuild = sqlconfig.apiurl + "index.php?password=" + md5.MD5(sqlconfig.apipasswd) + "&QueryAcc&name=" + name;
        APIRequest(URLBuild);
    }

    public void floodGlobal(String channel, String mensagem) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException, MalformedURLException, JSONException {
        URLBuild = sqlconfig.apiurl + "index.php?password=" + md5.MD5(sqlconfig.apipasswd) + "&flood&channel=" + channel + "&msg=" + URLEncoder.encode(mensagem, "UTF-8");
        APIRequest(URLBuild);
    }

    public void Global(String mensagem) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException, MalformedURLException, JSONException {
        URLBuild = sqlconfig.apiurl + "index.php?password=" + md5.MD5(sqlconfig.apipasswd) + "&global&text=" + URLEncoder.encode(mensagem, "UTF-8");
        APIRequest(URLBuild);
    }

    public void ban(int id, int tempo, int tipo, String razao) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException, MalformedURLException, JSONException {
        URLBuild = sqlconfig.apiurl + "index.php?password=" + md5.MD5(sqlconfig.apipasswd) + "&ban&id=" + id + "&time=" + tempo*60 + "&tipo=" + tipo + "&razao=" + URLEncoder.encode(razao, "UTF-8");
        APIRequest(URLBuild);
    }

    public void Casamento(int id1, int id2) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException, MalformedURLException, JSONException {
        URLBuild = sqlconfig.apiurl + "index.php?password=" + md5.MD5(sqlconfig.apipasswd) + "&casar&id1=" + id1 + "&id2=" + id2;
        APIRequest(URLBuild);
    }

    public void renameCharacter(String id, String nomeantigo, String novonome) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException, MalformedURLException, JSONException {
        URLBuild = sqlconfig.apiurl + "index.php?password=" + md5.MD5(sqlconfig.apipasswd) + "&renameplayer&id=" + id + "&oldname=" + URLEncoder.encode(nomeantigo, "UTF-8") + "&newname=" + URLEncoder.encode(novonome, "UTF-8");
        APIRequest(URLBuild);
    }

    public void resetBank(String id) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException, MalformedURLException, JSONException {
        URLBuild = sqlconfig.apiurl + "index.php?password=" + md5.MD5(sqlconfig.apipasswd) + "&resetbank&id=" + id;
        APIRequest(URLBuild);
    }

    public String QueryID(String name) throws UnsupportedEncodingException, NoSuchAlgorithmException, IOException, MalformedURLException, JSONException {
        URLBuild = sqlconfig.apiurl + "index.php?password=" + md5.MD5(sqlconfig.apipasswd) + "&queryname&name=" + name;
        APIRequest(URLBuild);
        return this.result;
    }

    public Boolean ApiCheck() throws UnsupportedEncodingException, NoSuchAlgorithmException, IOException, MalformedURLException, JSONException {
        URLBuild = sqlconfig.apiurl + "index.php?password=" + md5.MD5(sqlconfig.apipasswd) + "&isvalid";
        APIRequest(URLBuild);
        return this.result.equals("true");
    }

    public String APIRequest(String request) throws MalformedURLException, IOException, JSONException {
        URL url = new URL(request);
        InputStream is = url.openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String jsonText = GetHash.readAll(rd);
        if (jsonText != null && !jsonText.equals("")) {
            JSONObject obj = new JSONObject(jsonText);
            this.result = obj.getString("result");
            if(obj.has("resultAcc")) {
                this.resultAcc = obj.getString("resultAcc");
            }
        }
        return result;
    }
}
