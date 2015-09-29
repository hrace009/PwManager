package pwmanager;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.io.IOException;
import javax.swing.UIManager;
import org.json.JSONException;

public class PwManager {

    public static void main(String[] args) throws IOException, JSONException, ClassNotFoundException, Exception {
        try {
            UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        UI front = new UI();
    }

}
