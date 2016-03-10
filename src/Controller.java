import com.google.gson.Gson;
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
import java.lang.reflect.Array;
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
    }
}
