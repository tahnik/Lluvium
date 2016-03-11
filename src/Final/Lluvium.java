package Final;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by tahnik on 10/03/2016.
 */
public class Lluvium extends Application{
    int draggedAmountX = 0;
    int draggedAmountY = 0;
    double draggedX = 0;
    double draggedY = 0;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Lluvium");
        GridPane big_top = (GridPane) FXMLLoader.load(getClass().getResource("Lluvium_big.fxml"));
        GridPane big_track = (GridPane) FXMLLoader.load(getClass().getResource("Lluvium_big_track.fxml"));
        GridPane big_forecast = (GridPane) FXMLLoader.load(getClass().getResource("Lluvium_big_forecast.fxml"));
        GridPane big_settings = (GridPane) FXMLLoader.load(getClass().getResource("Lluvium_big_settings.fxml"));
        GridPane main_big = new GridPane();
        main_big.getColumnConstraints().add(new ColumnConstraints(1536));
        main_big.getRowConstraints().add(new RowConstraints(512));
        main_big.getRowConstraints().add(new RowConstraints(512));
        main_big.getRowConstraints().add(new RowConstraints(512));
        GridPane main_big_child = new GridPane();
        main_big_child.getColumnConstraints().add(new ColumnConstraints(768));
        main_big_child.getColumnConstraints().add(new ColumnConstraints(768));
        main_big_child.getRowConstraints().add(new RowConstraints(512));
        main_big_child.add(big_top, 0, 0);
        main_big_child.add(big_forecast, 1, 0);
        main_big.add(big_settings, 0, 2, 1, 1);
        main_big.add(big_track, 0, 1);
        main_big.add(main_big_child, 0, 0);
        big_track.setTranslateZ(1);
        big_settings.setTranslateZ(0);
        main_big.setStyle("-fx-background-color: #407efd");
        main_big_child.setStyle("-fx-background-color: #407efd");
        Scene big_main = new Scene(main_big, 768, 1024);
        primaryStage.setScene(big_main);
        primaryStage.show();

        System.out.println(big_forecast.getTranslateX());

        main_big_child.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                /* for handling swipe right and left */
                if(draggedX > (draggedX = event.getX())){
                    draggedAmountX++;
                }else{
                    draggedAmountX--;
                }
                //System.out.println(draggedAmount);
                //System.out.println("X: " + draggedX);
                if(draggedAmountX == 10 && main_big_child.getTranslateX() == 0){
                    TranslateTransition ff = new TranslateTransition(Duration.millis(1000), main_big_child);
                    ff.setByX(-768);
                    ff.play();
                    //System.out.println(big_forecast.getTranslateX());
                }
                if(draggedAmountX == (-10) && main_big_child.getTranslateX() == -768){
                    TranslateTransition ff = new TranslateTransition(Duration.millis(1000), main_big_child);
                    ff.setByX(768);
                    ff.play();
                    //System.out.println(big_forecast.getTranslateX());
                }
            }
        });
        main_big_child.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                draggedAmountX = 0;
            }
        });
        main_big.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                /* for handling swipe up and down */
                if(draggedY > (draggedY = event.getY())){
                    draggedAmountY++;
                }else{
                    draggedAmountY--;
                }
                //System.out.println(draggedAmount);
                System.out.println("X: " + draggedAmountY);
                if(draggedAmountY == 15 && main_big.getTranslateY() == 0){
                    TranslateTransition ff = new TranslateTransition(Duration.millis(1000), main_big);
                    ff.setByY(-512);
                    ff.play();
                    System.out.println(big_settings.getTranslateY());
                }
                if(draggedAmountY == (-15) && main_big.getTranslateY() == -512){
                    TranslateTransition ff = new TranslateTransition(Duration.millis(1000), main_big);
                    ff.setByY(512);
                    ff.play();
                    System.out.println(big_settings.getTranslateY());
                }
            }
        });
        main_big.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                draggedAmountY = 0;
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
