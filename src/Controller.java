import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import jdk.nashorn.internal.parser.JSONParser;

import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by tahnik on 09/03/2016.
 */
public class Controller{
    @FXML private ImageView right_forecast;
    @FXML private ImageView main_artwork;
    @FXML private ImageView forecastArtwork;

    @FXML
    public void changeMusicSuggestion(){
        URL url = null;
        String iTunesInfo = "";
        try {
            url = new URL("https://itunes.apple.com/search?term=faded");
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
        System.out.println(test.getAsJsonObject().getAsJsonArray("results").get(0).getAsJsonObject());
        try{
            String modifiedURL = test.getAsJsonObject().getAsJsonArray("results").get(0).getAsJsonObject().get("artworkUrl100").toString();
            modifiedURL = modifiedURL.replace("\"", "");
            URL imageURL = new URL(modifiedURL);
            BufferedImage artwork = ImageIO.read(imageURL);
            forecastArtwork.setImage(SwingFXUtils.toFXImage(artwork, null));
        }catch (Exception e){
            System.err.println(e);
        }
    }
}
