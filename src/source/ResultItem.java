package source;

import com.sun.istack.internal.NotNull;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 * Created by Chen xl on 11/5/2016.
 */
public class ResultItem {

    private final boolean LEFT = true;
    private final boolean RIGHT = false;

    private Label district_label = new Label();
    private Label district_content = new Label();
    private Label area_label = new Label();
    private Label area_content = new Label();
    private Label types_label = new Label();
    private Label types_content = new Label();
    private Label price_label = new Label();
    private Label price_content = new Label();
    private Label salable_area_label = new Label();
    private Label salable_area_content = new Label();
    private Label construct_area_label = new Label();
    private Label construct_area_content = new Label();
    private Label bedroom_label = new Label();
    private Label bedroom_content = new Label();
    private Label livingroom_label = new Label();
    private Label livingroom_content = new Label();
    private Label location_label = new Label();
    private Label location_content = new Label();
    private Label release_date_label = new Label();
    private Label release_date_content = new Label();
    private ImageView image = new ImageView();

    public ResultItem() {
        district_label.setFont(new Font("System Bold", 15));
        area_label.setFont(new Font("System Bold", 15));
        types_label.setFont(new Font("System Bold", 15));
        price_label.setFont(new Font("System Bold", 15));
        salable_area_label.setFont(new Font("System Bold", 15));
        construct_area_label.setFont(new Font("System Bold", 15));
        bedroom_label.setFont(new Font("System Bold", 15));
        livingroom_label.setFont(new Font("System Bold", 15));
        location_label.setFont(new Font("System Bold", 15));
        release_date_label.setFont(new Font("System Bold", 15));

        district_label.setTextFill(Paint.valueOf("#0000008A"));
        area_label.setTextFill(Paint.valueOf("#0000008A"));
        types_label.setTextFill(Paint.valueOf("#0000008A"));
        price_label.setTextFill(Paint.valueOf("#0000008A"));
        salable_area_label.setTextFill(Paint.valueOf("#0000008A"));
        construct_area_label.setTextFill(Paint.valueOf("#0000008A"));
        bedroom_label.setTextFill(Paint.valueOf("#0000008A"));
        livingroom_label.setTextFill(Paint.valueOf("#0000008A"));
        location_label.setTextFill(Paint.valueOf("#0000008A"));
        release_date_label.setTextFill(Paint.valueOf("#0000008A"));

        district_label.setAlignment(Pos.CENTER_RIGHT);
        area_label.setAlignment(Pos.CENTER_RIGHT);
        types_label.setAlignment(Pos.CENTER_RIGHT);
        price_label.setAlignment(Pos.CENTER_RIGHT);
        salable_area_label.setAlignment(Pos.CENTER_RIGHT);
        construct_area_label.setAlignment(Pos.CENTER_RIGHT);
        bedroom_label.setAlignment(Pos.CENTER_RIGHT);
        livingroom_label.setAlignment(Pos.CENTER_RIGHT);
        location_label.setAlignment(Pos.CENTER_RIGHT);
        release_date_label.setAlignment(Pos.CENTER_RIGHT);
    }

    public void setDistrict(String district_label, String district_content) {
        this.district_label.setText(district_label);
        this.district_content.setText(district_content);
    }

    public void setArea(String area_label, String area_content) {
        this.area_label.setText(area_label);
        this.area_content.setText(area_content);
    }

    public void setTypes(String types_label, String types_content) {
        this.types_label.setText(types_label);
        this.types_content.setText(types_content);
    }

    public void setPrice(String price_label, String price_content) {
        this.price_label.setText(price_label);
        this.price_content.setText(price_content);
    }

    public void setSalableArea(String salable_area_label, String salable_area_content) {
        this.salable_area_label.setText(salable_area_label);
        this.salable_area_content.setText(salable_area_content);
    }

    public void setConstructArea(String construct_area_label, String construct_area_content) {
        this.construct_area_label.setText(construct_area_label);
        this.construct_area_content.setText(construct_area_content);
    }

    public void setBedroom(String bedroom_label, String bedroom_content) {
        this.bedroom_label.setText(bedroom_label);
        this.bedroom_content.setText(bedroom_content);
    }

    public void setLivingroom(String livingroom_label, String livingroom_content) {
        this.livingroom_label.setText(livingroom_label);
        this.livingroom_content.setText(livingroom_content);
    }

    public void setLocation(String location_label, String location_content) {
        this.location_label.setText(location_label);
        this.location_content.setText(location_content);
    }

    public void setReleaseDate(String release_date_label, String release_date_content) {
        this.release_date_label.setText(release_date_label);
        this.release_date_content.setText(release_date_content);
    }

    public void setImage(@NotNull String url) {
        this.image.setImage(new Image(getClass().getResource(url).toString(), true));
    }

    private HBox RowHelper(boolean pos, Label label, Label content) {
        int width;
        int label_width;
        int content_width;

        if (pos) {
            width = 330;
            label_width = 100;
            content_width = 214;
        } else {
            width = 384;
            label_width = 160;
            content_width = 208;
        }

        HBox hBox = new HBox();
        hBox.setMinSize(width, 54);
        hBox.setMaxSize(width, 54);

        label.setMinSize(label_width, 80);
        label.setMaxSize(label_width, 80);
        hBox.getChildren().add(label);

        content.setMinSize(content_width, 80);
        content.setMaxSize(content_width, 80);
        hBox.getChildren().add(content);
        HBox.setMargin(content, new Insets(0, 0, 0, 16));

        return hBox;
    }

    public StackPane getItem() {
        StackPane root = new StackPane();
        root.setMinSize(1076, 344);
        root.setMaxSize(1076, 344);
        root.setStyle("-fx-background-color: #ffffff;");
        root.setEffect(new DropShadow(10, Color.color(0, 0, 0, 0.27)));

        image.setFitHeight(344);
        image.setFitWidth(344);
        root.getChildren().add(image);
        StackPane.setAlignment(image, Pos.CENTER_LEFT);

        VBox vBoxLeft = new VBox();
        vBoxLeft.setMinSize(384, 344);
        vBoxLeft.setMaxSize(384, 344);
        root.getChildren().add(vBoxLeft);
        StackPane.setAlignment(vBoxLeft, Pos.CENTER_LEFT);
        StackPane.setMargin(vBoxLeft, new Insets(0, 0, 0, 370));

        HBox dateHBox = new HBox();
        dateHBox.setMinSize(784, 54);
        dateHBox.setMaxSize(784, 54);

        release_date_label.setMinSize(100, 80);
        release_date_label.setMaxSize(100, 80);
        dateHBox.getChildren().add(release_date_label);

        release_date_content.setMinSize(668, 80);
        release_date_content.setMaxSize(668, 80);
        dateHBox.getChildren().add(release_date_content);
        HBox.setMargin(release_date_content, new Insets(0, 0, 0, 16));

        HBox addressHBox = new HBox();
        addressHBox.setMinSize(784, 54);
        addressHBox.setMaxSize(784, 54);

        location_label.setMinSize(100, 80);
        location_label.setMaxSize(100, 80);
        addressHBox.getChildren().add(location_label);

        location_content.setMinSize(668, 80);
        location_content.setMaxSize(668, 80);
        addressHBox.getChildren().add(location_content);
        HBox.setMargin(location_content, new Insets(0, 0, 0, 16));

        HBox[] leftRows = {RowHelper(LEFT, district_label, district_content),
                RowHelper(LEFT, area_label, area_content),
                RowHelper(LEFT, types_label, types_content),
                RowHelper(LEFT, bedroom_label, bedroom_content),
                dateHBox,
                addressHBox};
        for (HBox tmp : leftRows) {
            vBoxLeft.getChildren().add(tmp);
        }


        VBox vBoxRight = new VBox();
        vBoxRight.setMinSize(384, 344);
        vBoxRight.setMaxSize(384, 344);
        root.getChildren().add(vBoxRight);
        StackPane.setAlignment(vBoxRight, Pos.CENTER_LEFT);
        StackPane.setMargin(vBoxRight, new Insets(0, 0, 0, 716));

        HBox[] rightRows = {RowHelper(RIGHT, price_label, price_content),
                RowHelper(RIGHT, salable_area_label, salable_area_content),
                RowHelper(RIGHT, construct_area_label, construct_area_content),
                RowHelper(RIGHT, livingroom_label, livingroom_content)};
        for (HBox tmp : rightRows) {
            vBoxRight.getChildren().add(tmp);
        }

        return root;
    }
}
