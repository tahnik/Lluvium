package Final;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.BooleanSupplier;

/**
 * Created by tahnik on 10/03/2016.
 */
public class trackController implements Initializable{
    @FXML private ImageView trackArtwork;
    @FXML private Label trackName;
    @FXML private Label trackArtist;
    @FXML private ImageView trackRightArrow;
    ArrayList<Song> tracks = new ArrayList<Song>();
    int nowPlaying = 0;
    Boolean leftButtonClicked = false;
    Boolean rightButtonClicked = false;

    @FXML
    public void changeMusicSuggestionRight(){
        /*
        if(leftButtonClicked){
            nowPlaying += 2;
        }else{
            nowPlaying++;
        }
        */
        if(nowPlaying == 9){
            nowPlaying = 0;
        }else {
            nowPlaying++;
        }
        System.out.println(nowPlaying);
        Song currentTrack = tracks.get(nowPlaying);
        trackArtwork.setImage(currentTrack.getArtwork());
        trackName.setText(currentTrack.getName());
        trackArtist.setText(currentTrack.getArtist());
        leftButtonClicked = false;
        rightButtonClicked = true;
    }
    @FXML
    public void changeMusicSuggestionLeft(){
        /*
        if(rightButtonClicked){
            nowPlaying -= 2;
        }else{
            nowPlaying--;
        }
        */
        if(nowPlaying == 0){
            nowPlaying = 9;
        }else {
            nowPlaying--;
        }
        System.out.println(nowPlaying);
        Song currentTrack = tracks.get(nowPlaying);
        trackArtwork.setImage(currentTrack.getArtwork());
        trackName.setText(currentTrack.getName());
        trackArtist.setText(currentTrack.getArtist());
        rightButtonClicked = false;
        leftButtonClicked = true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MusicGrabber musicGrabber = new MusicGrabber("pop");
        tracks = musicGrabber.getTopTracks();
    }
}
