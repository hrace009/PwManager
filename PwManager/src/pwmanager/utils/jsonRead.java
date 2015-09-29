package pwmanager.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class jsonRead {

    public jsonRead(mysqlconfig sqlconfig) throws IOException, JSONException {
        jsonWrite gravadados;
        String jsonData = "";
        BufferedReader br = null;
        File file = new File("conf.json");
        if (file.exists()) {
            try {
                String line;
                br = new BufferedReader(new FileReader("conf.json"));
                while ((line = br.readLine()) != null) {
                    jsonData += line + "\n";
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            JSONObject obj = new JSONObject(jsonData);
            sqlconfig.mysqlip = obj.getString("ip");
            sqlconfig.mysqlport = obj.getString("port");
            sqlconfig.mysqluser = obj.getString("user");
            sqlconfig.mysqlpasswd = obj.getString("passwd");
            sqlconfig.mysqldb = obj.getString("db");
            sqlconfig.sqlhash = obj.getInt("sqlhash");
            sqlconfig.apiurl = obj.getString("apiurl");
            sqlconfig.apipasswd = obj.getString("apipasswd");
        } else {
            sqlconfig.mysqlip = "127.0.0.1";
            sqlconfig.mysqlport = "3306";
            sqlconfig.mysqluser = "root";
            sqlconfig.mysqlpasswd = "";
            sqlconfig.mysqldb = "pw";
            sqlconfig.sqlhash = 0;
            sqlconfig.apiurl = "http://127.0.0.1/api/";
            sqlconfig.apipasswd = "";
            gravadados = new jsonWrite(sqlconfig);
        }
    }
}
