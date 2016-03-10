import com.github.dvdme.ForecastIOLib.ForecastIO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Created by tahnik on 10/03/2016.
 */
public class forecastController {
    @FXML private ImageView right_forecast;
    @FXML private ImageView forecastArtwork;
    @FXML private Label forecastSongName;
    @FXML private Label forecastSongArtist;
    static ArrayList<Song> tracks = new ArrayList<Song>();
    int nowPlaying = 0;

    @FXML
    public void changeMusicSuggestionLeft(){
        Song currentTrack = tracks.get(nowPlaying);
        forecastArtwork.setImage(currentTrack.getArtwork());
        forecastSongName.setText(currentTrack.getName());
        forecastSongArtist.setText(currentTrack.getArtist());
        System.out.println(nowPlaying);
        if(nowPlaying >= 9){
            nowPlaying = 0;
        }else {
            nowPlaying++;
        }
    }

    public static void getTracks() {
        MusicGrabber musicGrabber = new MusicGrabber("jazz");
        tracks = musicGrabber.getTopTracks();
    }
}
