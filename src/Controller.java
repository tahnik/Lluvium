import com.github.dvdme.ForecastIOLib.ForecastIO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 * Created by tahnik on 09/03/2016.
 */
public class Controller implements Initializable{
    @FXML private ImageView main_artwork;
    @FXML private Label mainWeather;
    @FXML private Label mainDay;
    @FXML private Label mainWind;
    @FXML private Label mainWindDirection;
    @FXML private Label mainRain;
    @FXML private Label mainHumidity;

    /*
    static ArrayList<Song> tracks = new ArrayList<Song>();
    int nowPlaying = 0;
    */

    public void setWeather(){
        ForecastIO io = new ForecastIO("1b7e5f33c5e426dab13ba0bb2eb55913");
        io.setUnits(ForecastIO.UNITS_SI);
        io.setLang(ForecastIO.LANG_ENGLISH);
        io.getForecast("51.522910", "-0.043139");
        System.out.println(io.getCurrently());
        Double weatherDouble = Double.parseDouble(io.getCurrently().get("temperature").toString());
        mainWeather.setText(weatherDouble.intValue() + "°");
        Calendar calendar = Calendar.getInstance();
        long timeInMilli = (long) Double.parseDouble(io.getCurrently().get("time").toString() + "000");
        calendar.setTimeInMillis(timeInMilli);
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
        mainDay.setText(getDay(calendar.get(Calendar.DAY_OF_WEEK)));
        mainWind.setText(io.getCurrently().get("windSpeed").toString());
        mainRain.setText(io.getCurrently().get("precipIntensity").toString());
        mainHumidity.setText(io.getCurrently().get("humidity").toString());
        mainWindDirection.setText(io.getCurrently().get("windBearing").toString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setWeather();
    }

    public String getDay(int x){
        switch (x){
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday";
        }
        return "Sunday";
    }
}
