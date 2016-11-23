package source;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen xl on 11/5/2016.
 */
public class SearchResultLoader {

    private List<ResultItem> results;
    double xOffset;
    double yOffset;

    public SearchResultLoader() throws IOException {
        this.results = new ArrayList<ResultItem>();
    }

    public void addItem(ResultItem resultItem) {
        this.results.add(resultItem);
    }

    public void showStage(String title) throws IOException {
        StackPane root = FXMLLoader.load(getClass().getResource("/layout/search_result.fxml"));

        ((Label) ((HBox) root.getChildren().get(1)).getChildren().get(1)).setText(title);


        ObservableList<Node> childlist = ((VBox) ((ScrollPane) root.getChildren().get(0)).getContent()).getChildren();
        if (results.size() == 0) {
            VBox box = new VBox();
            box.setMinSize(1076, 236);
            box.setMaxSize(1076, 236);

            ImageView imageView = new ImageView();
            imageView.setFitWidth(160);
            imageView.setFitHeight(160);
            imageView.setImage(new Image(getClass().getResource("/image/not_found.png").toString(), true));

            box.getChildren().add(imageView);
            VBox.setMargin(imageView, new Insets(16, 0, 16, 458));

            Label label = new Label();
            label.setMinSize(1076, 80);
            label.setMaxSize(1076, 80);
            label.setAlignment(Pos.CENTER);
            label.setFont(new Font("System Bold", 40));
            label.setTextFill(Paint.valueOf("#0000008A"));
            label.setText(I18N.convert("No Related Houses Found"));

            box.getChildren().add(label);

            childlist.add(box);
            VBox.setMargin(box, new Insets(24, 50, 0, 50));
        } else {
            for (ResultItem tmp : results) {
                StackPane stackpane = tmp.getItem();
                childlist.add(stackpane);
                VBox.setMargin(stackpane, new Insets(24, 50, 0, 50));
            }
        }

        Pane buttomMargin = new Pane();
        buttomMargin.setMinSize(1176, 24);
        buttomMargin.setMaxSize(1176, 24);
        childlist.add(buttomMargin);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed((event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged((event) -> {
            if (yOffset <= 80) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });

        ((HBox) root.getChildren().get(1)).getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            stage.close();
        });

        stage.show();
    }
}
