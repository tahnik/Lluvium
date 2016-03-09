import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.image.ImageView;
import jdk.nashorn.internal.parser.JSONParser;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by tahnik on 09/03/2016.
 */
public class Controller{
    @FXML private ImageView right_forecast;

    @FXML
    public void changeMusicSuggestion(){
        URL url = null;
        String iTunesInfo = "";
        try {
            url = new URL("https://itunes.apple.com/search?term=jack+johnson");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String input;
            while((input = in.readLine()) != null){
                iTunesInfo += input;
            }
            in.close();
        }catch (Exception e){
            System.err.println(e);
        }
        JsonParser iTunes = new JsonParser();
        JsonElement test= iTunes.parse(iTunesInfo);
        System.out.println(test.getAsJsonObject().get("resultCount"));
    }
}
