import com.google.gson.JsonElement;
import javafx.scene.image.Image;

/**
 * Created by tahnik on 10/03/2016.
 */
public class Song {
    private String artist;
    private String name;
    private String genre;
    private String album;
    Image artwork;

    public void setArtist(String artist){
        this.artist = removeChar(artist);
    }
    public void setName(String name){
        this.name = removeChar(name);
    }
    public void setGenre(String genre){
        this.genre = removeChar(genre);
    }
    public void setAlbum(String album){
        this.album = removeChar(album);
    }

    public void setArtwork(Image artwork) {
        this.artwork = artwork;
    }
    public String removeChar(String temp){
        return temp.replace("\"", "");
    }

    public String getArtist() {
        return artist;
    }

    public String getName() {
        return name;
    }
}
