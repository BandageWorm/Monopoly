package source;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    double xOffset;
    double yOffset;
//    public static String lan="ja";
//    public static String con="JP";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/layout/root.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed((event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged((event) -> {
            if (yOffset <= 80) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void init(){
        I18N.setDefault();
    }

    public static void main(String[] args) {
        CurrentRate.writeDefaultRate();
        CurrentRate.getRate();
        launch(args);
    }
}
