import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.server.ExportException;
import java.util.ArrayList;

/**
 * Created by tahnik on 10/03/2016.
 */
public class MusicGrabber {
    JsonObject lastFM;
    String term = "";
    String JSONString = "";
    ArrayList<Song> topTracks = new ArrayList<Song>();

    public MusicGrabber(String term){
        this.term = term;
        try {
            getLastFMJSON();
            setLastFM();
            setSong();
        }catch (Exception e){
            System.err.println("Couldn't load music data");
        }
    }
    public ArrayList<Song> getTopTracks(){
        return this.topTracks;
    }
    public void getLastFMJSON(){
        URL url = null;
        String lastFMURL = "http://ws.audioscrobbler.com/2.0/?method=tag.gettoptracks&tag=" + term +
                "&api_key=5677b69d95c3fa7acba18f42c53a126b&format=json";
        BufferedReader in = null;
        try {
            url = new URL(lastFMURL);
            System.out.println("Loading tracks...");
            URLConnection conn = url.openConnection();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String input;
            while((input = in.readLine()) != null){
                JSONString += input;
            }
            System.out.println("Finished loading tracks...");
        }catch (Exception e){
            System.err.println(e);
        }
        try {
            if (in != null) {
                in.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        /*
        PrintWriter disco = null;
        try {
            disco = new PrintWriter("offline/disco.txt");
            disco.write(JSONString);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (disco != null) {
                disco.close();
            }
        }
        */
    }
    public void setLastFM(){
        JsonParser parser = new JsonParser();
        //System.out.println(JSONString + "\n");
        lastFM = parser.parse(JSONString).getAsJsonObject();
    }
    public void setSong(){
        JsonArray tracks = lastFM.get("tracks").getAsJsonObject().get("track").getAsJsonArray();
        //System.out.println(tracks);
        for(int i = 0 ; i < 10; i++) {
            Song song = new Song();
            JsonObject firstTrack = tracks.get(i).getAsJsonObject();
            //System.out.println(firstTrack);
            song.setName(firstTrack.get("name").toString());
            //System.out.println(firstTrack.get("artist").getAsJsonObject().get("name").toString());
            song.setArtist(firstTrack.get("artist").getAsJsonObject().get("name").toString());
            String imageString = replaceChar(firstTrack.get("image").getAsJsonArray().get(3).getAsJsonObject().get("#text").toString());
            System.out.println(imageString);
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
