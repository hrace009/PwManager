package pwmanager.utils;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONException;
import org.json.simple.JSONObject;

public class jsonWrite {

    public jsonRead recuperadados;

    public jsonWrite(mysqlconfig sqlconfig) throws IOException, JSONException {

        JSONObject obj = new JSONObject();
        obj.put("ip", sqlconfig.mysqlip);
        obj.put("port", sqlconfig.mysqlport);
        obj.put("user", sqlconfig.mysqluser);
        obj.put("passwd", sqlconfig.mysqlpasswd);
        obj.put("db", sqlconfig.mysqldb);
        obj.put("sqlhash", String.valueOf(sqlconfig.sqlhash));
        obj.put("apiurl", sqlconfig.apiurl);
        obj.put("apipasswd", String.valueOf(sqlconfig.apipasswd));
        FileWriter file = new FileWriter("conf.json");
        try {
            file.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.flush();
            file.close();
            recuperadados = new jsonRead(sqlconfig);
        }
    }

}
