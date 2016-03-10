/**
 * Created by tahnik on 10/03/2016.
 */
public class jsontest {
    public static void main(String[] args){
        MusicGrabber musicGrabber = new MusicGrabber("jazz");
        musicGrabber.getLastFMJSON();
        musicGrabber.setLastFM();
        musicGrabber.setSong();
    }
}
