import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by tahnik on 10/03/2016.
 */
public class Lluvium_small extends Stage{
    int draggedAmountX = 0;
    int draggedAmountY = 0;
    double draggedX = 0;
    double draggedY = 0;

    public Lluvium_small() throws Exception {
        /*
        this.bigStage = primaryStage;
        primaryStage.setTitle("Lluvium");
        */
        GridPane small_top = (GridPane) FXMLLoader.load(getClass().getResource("Lluvium_small.fxml"));
        GridPane small_track = (GridPane) FXMLLoader.load(getClass().getResource("Lluvium_small_track.fxml"));
        GridPane small_forecast = (GridPane) FXMLLoader.load(getClass().getResource("Lluvium_small_forecast.fxml"));
        GridPane small_settings = (GridPane) FXMLLoader.load(getClass().getResource("Lluvium_small_settings.fxml"));

        GridPane main_small = new GridPane();
        main_small.getColumnConstraints().add(new ColumnConstraints(640));
        main_small.getRowConstraints().add(new RowConstraints(240));
        main_small.getRowConstraints().add(new RowConstraints(240));
        main_small.getRowConstraints().add(new RowConstraints(240));
        GridPane main_small_child = new GridPane();
        main_small_child.getColumnConstraints().add(new ColumnConstraints(320));
        main_small_child.getColumnConstraints().add(new ColumnConstraints(320));
        main_small_child.getRowConstraints().add(new RowConstraints(240));
        main_small_child.add(small_top, 0, 0);
        main_small_child.add(small_forecast, 1, 0);
        main_small.add(small_settings, 0, 2, 1, 1);
        main_small.add(small_track, 0, 1);
        main_small.add(main_small_child, 0, 0);
        small_track.setTranslateZ(1);
        small_settings.setTranslateZ(0);
        main_small.setStyle("-fx-background-color: #407efd");
        main_small_child.setStyle("-fx-background-color: #407efd");
        Scene big_main = new Scene(main_small, 320, 480);
        /*
        primaryStage.setScene(big_main);
        primaryStage.show();
        */

        this.setScene(big_main);
        this.show();

        //System.out.println(big_forecast.getTranslateX());

        main_small_child.setOnMouseDragged(new EventHandler<MouseEvent>(){
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
                if(draggedAmountX == 10 && main_small_child.getTranslateX() == 0){
                    TranslateTransition ff = new TranslateTransition(Duration.millis(1000), main_small_child);
                    ff.setByX(-320);
                    ff.play();
                    //System.out.println(big_forecast.getTranslateX());
                }
                if(draggedAmountX == (-10) && main_small_child.getTranslateX() == -320){
                    TranslateTransition ff = new TranslateTransition(Duration.millis(1000), main_small_child);
                    ff.setByX(320);
                    ff.play();
                    //System.out.println(big_forecast.getTranslateX());
                }
            }
        });
        main_small_child.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                draggedAmountX = 0;
            }
        });
        main_small.setOnMouseDragged(new EventHandler<MouseEvent>() {
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
                if(draggedAmountY == 15 && main_small.getTranslateY() == 0){
                    TranslateTransition ff = new TranslateTransition(Duration.millis(1000), main_small);
                    ff.setByY(-240);
                    ff.play();
                    System.out.println(small_settings.getTranslateY());
                }
                if(draggedAmountY == (-15) && main_small.getTranslateY() == -240){
                    TranslateTransition ff = new TranslateTransition(Duration.millis(1000), main_small);
                    ff.setByY(240);
                    ff.play();
                    System.out.println(small_settings.getTranslateY());
                }
            }
        });
        main_small.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                draggedAmountY = 0;
            }
        });
    }
}
