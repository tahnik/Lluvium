import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.parser.Scanner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by tahnik on 09/03/2016.
 */
public class Lluvium extends Application{
    int draggedAmount = 0;
    double draggedX = 0;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Lluvium");
        GridPane Lluvium_big_main = (GridPane) FXMLLoader.load(getClass().getResource("main.fxml"));
        GridPane Lluvium_big_forecast = (GridPane) FXMLLoader.load(getClass().getResource("forecast_big.fxml"));
        GridPane main_big = new GridPane();
        main_big.getColumnConstraints().add(new ColumnConstraints(768));
        main_big.getColumnConstraints().add(new ColumnConstraints(768));
        main_big.getRowConstraints().add(new RowConstraints(1024));
        main_big.add(Lluvium_big_main, 0, 0);
        main_big.add(Lluvium_big_forecast, 1, 0);
        Scene big_main = new Scene(main_big, 768, 1024);
        primaryStage.setScene(big_main);
        primaryStage.show();
        Controller.getTracks();

        System.out.println(Lluvium_big_forecast.getTranslateX());

        big_main.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                draggedAmount = 0;
            }
        });

        /*
        big_forecast.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                draggedAmount = 0;
            }
        });
        */

        big_main.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                if(draggedX > (draggedX = event.getX())){
                    draggedAmount++;
                }else{
                    draggedAmount--;
                }
                System.out.println(draggedAmount);
                //System.out.println("X: " + draggedX);
                if(draggedAmount == 10 && main_big.getTranslateX() == 0){
                    TranslateTransition ff = new TranslateTransition(Duration.millis(1000), main_big);
                    ff.setByX(-768);
                    ff.play();
                    System.out.println(Lluvium_big_forecast.getTranslateX());
                }
                if(draggedAmount == (-10) && main_big.getTranslateX() == -768){
                    TranslateTransition ff = new TranslateTransition(Duration.millis(1000), main_big);
                    ff.setByX(768);
                    ff.play();
                    System.out.println(Lluvium_big_forecast.getTranslateX());
                }
                /*
                if(Lluvium_big_main.getTranslateX() > -765){
                    primaryStage.setScene(big_forecast);
                }
                */
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
