 import javafx.application.Application;
 import javafx.event.ActionEvent;
 import javafx.event.EventHandler;
 import javafx.scene.Scene;
 import javafx.scene.control.Button;
 import javafx.scene.layout.FlowPane;
 import javafx.scene.layout.StackPane;
 import javafx.stage.Stage;
/*
 * Created by tahnik on 11/03/2016.
 */
public class Lluvium extends Application{
    Stage LluviumBig;
    Stage LluviumSmall;
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lluvium Controller");
        Button btn = new Button();
        Button btn2 = new Button();
        Button btn3 = new Button();
        Button btn4 = new Button();


        btn.setText("Open Tablet Version");
        btn2.setText("Close Tablet Version");
        btn3.setText("Open Mobile Version");
        btn4.setText("Close Mobile Version");

        btn.setPrefWidth(300);
        btn2.setPrefWidth(300);
        btn3.setPrefWidth(300);
        btn4.setPrefWidth(300);


        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    LluviumBig = new Lluvium_big();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    LluviumBig.close();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        });

        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    LluviumSmall = new Lluvium_small();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    LluviumSmall.close();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        });

        FlowPane root = new FlowPane();
        root.getChildren().add(btn);
        root.getChildren().add(btn2);
        root.getChildren().add(btn3);
        root.getChildren().add(btn4);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.show();
    }
}
