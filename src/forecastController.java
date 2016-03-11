package Final;

import com.github.dvdme.ForecastIOLib.FIODaily;
import com.github.dvdme.ForecastIOLib.ForecastIO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 * Created by tahnik on 10/03/2016.
 */
public class forecastController implements Initializable{
    @FXML private Label firstDay;
    @FXML private Label secondDay;
    @FXML private Label thirdDay;
    @FXML private Label fourthDay;
    @FXML private Label firstWeather;
    @FXML private Label secondWeather;
    @FXML private Label thirdWeather;
    @FXML private Label fourthWeather;
    @FXML private Label firstRain;
    @FXML private Label secondRain;
    @FXML private Label thirdRain;
    @FXML private Label fourthRain;
    @FXML private Label firstWind;
    @FXML private Label secondWind;
    @FXML private Label thirdWind;
    @FXML private Label fourthWind;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ForecastIO io = new ForecastIO("1b7e5f33c5e426dab13ba0bb2eb55913");
            io.setUnits(ForecastIO.UNITS_SI);
            io.setLang(ForecastIO.LANG_ENGLISH);
            io.getForecast("51.522910", "-0.043139");
            FIODaily fioDaily = new FIODaily(io);

            Calendar calendar = Calendar.getInstance();
            if (fioDaily.days() < 0) {
                System.out.println("No Data");
            } else {
                for (int i = 1; i < fioDaily.days() - 3; i++) {
                    String[] h = fioDaily.getDay(i).getFieldsArray();
                    System.out.println("Day #" + i);
                    calendar.add(Calendar.DATE, 1);
                    System.out.println(Arrays.toString(h));
                    switch (i) {
                        case 1:
                            firstDay.setText(getDay(calendar.get(Calendar.DAY_OF_WEEK)));
                            firstRain.setText(fioDaily.getDay(i).getByKey(h[16]));
                            firstWeather.setText(fioDaily.getDay(i).getByKey(h[1]));
                            firstWind.setText(fioDaily.getDay(i).getByKey(h[11]));
                        case 2:
                            secondDay.setText(getDay(calendar.get(Calendar.DAY_OF_WEEK)));
                            secondRain.setText(fioDaily.getDay(i).getByKey(h[16]));
                            secondWeather.setText(fioDaily.getDay(i).getByKey(h[1]));
                            secondWind.setText(fioDaily.getDay(i).getByKey(h[11]));
                        case 3:
                            thirdDay.setText(getDay(calendar.get(Calendar.DAY_OF_WEEK)));
                            thirdRain.setText(fioDaily.getDay(i).getByKey(h[16]));
                            thirdWeather.setText(fioDaily.getDay(i).getByKey(h[1]));
                            thirdWind.setText(fioDaily.getDay(i).getByKey(h[11]));
                        case 4:
                            fourthDay.setText(getDay(calendar.get(Calendar.DAY_OF_WEEK)));
                            fourthRain.setText(fioDaily.getDay(i).getByKey(h[16]));
                            fourthWeather.setText(fioDaily.getDay(i).getByKey(h[1]));
                            fourthWind.setText(fioDaily.getDay(i).getByKey(h[11]));
                    }
                }
            }
        }catch (Exception e){
            System.err.print("Couldn't load forecast");
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
}
