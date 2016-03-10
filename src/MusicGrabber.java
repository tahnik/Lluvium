import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by tahnik on 10/03/2016.
 */
public class MusicGrabber {
    JsonObject lastFM;
    String term = "";
    String JSONString = "";
    public MusicGrabber(String term){
        this.term = term;
    }
    public void getLastFMJSON(){
        URL url = null;
        String lastFMURL = "http://ws.audioscrobbler.com/2.0/?method=tag.gettoptracks&tag=" + term + "&api_key=5677b69d95c3fa7acba18f42c53a126b&format=json";
        try {
            url = new URL(lastFMURL);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String input;
            while((input = in.readLine()) != null){
                JSONString += input;
            }
            in.close();
        }catch (Exception e){
            System.err.println(e);
        }
    }
    public void setLastFM(){
        JsonParser parser = new JsonParser();
        //System.out.println(JSONString + "\n");
        lastFM = parser.parse(JSONString).getAsJsonObject();
    }
    public void setSong(){
        JsonArray tracks = lastFM.get("tracks").getAsJsonObject().get("track").getAsJsonArray();
        ArrayList<Song> topTracks = new ArrayList<Song>();
        //System.out.println(tracks);
        for(int i = 0 ; i < 10; i++) {
            Song song = new Song();
            JsonObject firstTrack = tracks.get(i).getAsJsonObject();
            //System.out.println(firstTrack);
            song.setName(firstTrack.get("name").toString());
            //System.out.println(firstTrack.get("artist").getAsJsonObject().get("name").toString());
            song.setArtist(firstTrack.get("artist").getAsJsonObject().get("name").toString());
            String imageString = replaceChar(firstTrack.get("image").getAsJsonArray().get(3).getAsJsonObject().get("#text").toString());
            //System.out.println(imageURL);
            try {
                URL imageURL = new URL(imageString);
                BufferedImage artwork = ImageIO.read(imageURL);
                song.setArtwork(SwingFXUtils.toFXImage(artwork, null));
            } catch (Exception e) {
                e.printStackTrace();
            }
            topTracks.add(song);
        }

        for(int i = 0; i < 10 ; i++){
            System.out.println(topTracks.get(i).getName());
        }
    }
    public String replaceChar(String temp){
        return temp.replace("\"", "");
    }
}
