import com.github.dvdme.ForecastIOLib.ForecastIO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 * Created by tahnik on 10/03/2016.
 */
public class mainController implements Initializable{
    @FXML private Label mainDegree;
    @FXML private Label mainDay;
    @FXML private Label mainWind;
    @FXML private Label mainWindDirection;
    @FXML private Label mainRain;
    @FXML private Label mainHumidity;
    @FXML private ImageView weatherTopArt;
    private static String weatherCondition;
    Double weatherDouble;

    public void setWeather(){
        try {
            ForecastIO io = new ForecastIO("1b7e5f33c5e426dab13ba0bb2eb55913");
            io.setUnits(ForecastIO.UNITS_SI);
            io.setLang(ForecastIO.LANG_ENGLISH);
            io.getForecast("51.522910", "-0.043139");
            System.out.println(io.getCurrently());
            weatherDouble = Double.parseDouble(io.getCurrently().get("temperature").toString());
            mainDegree.setText(weatherDouble.intValue() + "°");
            Calendar calendar = Calendar.getInstance();
            long timeInMilli = (long) Double.parseDouble(io.getCurrently().get("time").toString() + "000");
            calendar.setTimeInMillis(timeInMilli);
            System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
            mainDay.setText(getDay(calendar.get(Calendar.DAY_OF_WEEK)));
            mainWind.setText(io.getCurrently().get("windSpeed").toString());
            mainRain.setText(io.getCurrently().get("precipIntensity").toString());
            mainHumidity.setText(io.getCurrently().get("humidity").toString());
            mainWindDirection.setText(io.getCurrently().get("windBearing").toString());
            weatherCondition = replaceChar(io.getCurrently().get("summary").toString());
        }catch (Exception e){
            System.err.println("Couldn't load weather data");
        }
    }

    public void changeToFar(){
        Double far = weatherDouble + 32;
        mainDegree.setText(far.intValue() + "");
    }

    public void changeTopArt(){
        if(weatherCondition.toLowerCase().contains("cloudy")){
            Image image = new Image("/resources/rain_top_final.png");
            System.out.println(image.getHeight());
            weatherTopArt.setImage(image);
        }else if(weatherCondition.toLowerCase().contains("rain")){
            Image image = new Image("/resources/rain_top_final.png");
            System.out.println(image.getHeight());
            weatherTopArt.setImage(image);
        }else if(weatherCondition.toLowerCase().contains("snow")){
            Image image = new Image("/resources/rain_top_final.png");
            System.out.println(image.getHeight());
            weatherTopArt.setImage(image);
        }else if(weatherCondition.toLowerCase().contains("fog")){
            Image image = new Image("/resources/rain_top_final.png");
            System.out.println(image.getHeight());
            weatherTopArt.setImage(image);
        }else{
            Image image = new Image("/resources/sun_top_final.png");
            System.out.println(image.getHeight());
            weatherTopArt.setImage(image);
        }
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
    public static String getWeatherCondition(){
        return weatherCondition;
    }
    public String replaceChar(String temp){
        return temp.replace("\"", "");
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setWeather();
        changeTopArt();
    }
}
