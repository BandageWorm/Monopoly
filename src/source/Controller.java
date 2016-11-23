package source;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class Controller implements Initializable, LanguageChangeListener {

    @FXML
    StackPane root = new StackPane();

    @FXML
    Label title = new Label();

    @FXML
    StackPane container = new StackPane();

    @FXML
    Label currencyRate = new Label();

    @FXML
    Label buy_label = new Label();
    @FXML
    Pane buy_pane = new Pane();
    @FXML
    JFXButton buy_button = new JFXButton();
    @FXML
    Label rent_label = new Label();
    @FXML
    Pane rent_pane = new Pane();
    @FXML
    JFXButton rent_button = new JFXButton();
    @FXML
    Label setting_label = new Label();
    @FXML
    Pane setting_pane = new Pane();
    @FXML
    JFXButton setting_button = new JFXButton();
    @FXML
    Label exit_label = new Label();
    @FXML
    JFXButton exit_button = new JFXButton();

    Label exit_heading = new Label();
    JFXButton exit_yes = new JFXButton();
    JFXButton exit_cancel = new JFXButton();

    StackPane buyScene;
    StackPane rentScene;
    VBox settingScene;

    JFXDialog exitDialog;

    ListToggle listToggle;

    public void setLable() {
        String str_rent = I18N.convert("Rent");
        rent_label.setText(str_rent);
        String str_buy = I18N.convert("Purchase");
        buy_label.setText(str_buy);
        String str_setting = I18N.convert("Setting");
        setting_label.setText(str_setting);
        String str_exit = I18N.convert("Exit");
        exit_label.setText(str_exit);
        setRate();
    }

    public void setRate() {
        boolean access_Internet = true;
        if (access_Internet) {
            String currency_label = I18N.convert("Currency Rate");
            String str_srcCurrency = "HKD";
            System.out.println(CurrentRate.rateMap);
            String str_dstCurrency = CurrentRate.getDollorName();
            System.out.println("current :" + str_dstCurrency);
            String rate = CurrentRate.getHKRate(I18N.country);
            currencyRate.setText(MessageFormat.format("{0}: 100 {1} = {2} {3}", currency_label, str_srcCurrency, rate, str_dstCurrency));
        } else {
            String str_no_access_Internet = "No Internet Access.";
            currencyRate.setText(str_no_access_Internet);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LanguageChangeManager.getManager().registry(this);

        try {
            buyScene = FXMLLoader.load(getClass().getResource("/layout/buy.fxml"));
            rentScene = FXMLLoader.load(getClass().getResource("/layout/rent.fxml"));
            settingScene = FXMLLoader.load(getClass().getResource("/layout/setting.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        VBox sliderVBox = new VBox();
        sliderVBox.getChildren().add(buyScene);
        sliderVBox.getChildren().add(rentScene);
        sliderVBox.getChildren().add(settingScene);

        TranslateTransition slideTransition = new TranslateTransition(Duration.millis(500), sliderVBox);
        setLable();
        container.getChildren().add(sliderVBox);

        buy_button.addEventHandler(MouseEvent.MOUSE_PRESSED, (event) -> {
            title.setText(I18N.convert("Purchase"));
            if (sliderVBox.getTranslateY() != 0) {
                slideTransition.setByX(0);
                slideTransition.setByY(-sliderVBox.getTranslateY());
                slideTransition.playFromStart();
            }
        });

        rent_button.addEventHandler(MouseEvent.MOUSE_PRESSED, (event) -> {
            title.setText(I18N.convert("Rent"));
            if (sliderVBox.getTranslateY() != -600.0) {
                slideTransition.setByX(0);
                slideTransition.setByY(-600 - sliderVBox.getTranslateY());
                slideTransition.playFromStart();
            }
        });

        setting_button.addEventHandler(MouseEvent.MOUSE_PRESSED, (event) -> {
            title.setText(I18N.convert("Setting"));
            if (sliderVBox.getTranslateY() != -1200.0) {
                slideTransition.setByX(0);
                slideTransition.setByY(-1200 - sliderVBox.getTranslateY());
                slideTransition.playFromStart();
            }
        });

        exit_button.addEventHandler(MouseEvent.MOUSE_PRESSED, (event) -> {
            String str_heading = I18N.convert("Exit") + "?";
            exit_heading.setText(str_heading);
            String str_cancel = I18N.convert("Cancel");
            exit_cancel.setText(str_cancel);
            String str_yes = I18N.convert("Yes");
            exit_yes.setText(str_yes);
            if (exitDialog == null) {
                exitDialog = generateExitDialog(exit_heading, exit_cancel, exit_yes);
            }
            exitDialog.show();
        });

        ListItem buy = new ListItem("buy", buy_pane, buy_button);
        ListItem rent = new ListItem("rent", rent_pane, rent_button);
        ListItem setting = new ListItem("setting", setting_pane, setting_button);
        listToggle = new ListToggle(0, buy, rent, setting);

        title.setText(I18N.convert("Purchase"));

    }

    private JFXDialog generateExitDialog(Label heading, JFXButton cancel, JFXButton yes) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setPrefSize(320, 120);

        Label heading_label = heading;
        heading_label.setTextFill(Paint.valueOf("#000000FE"));
        heading_label.setFont(new Font("System Bold", 20));
        heading_label.setPadding(new Insets(0, 16, 0, 16));

        dialogLayout.setHeading(heading_label);

        JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.CENTER);

        JFXButton cancel_button = cancel;
        cancel_button.setPadding(new Insets(4, 16, 4, 16));
        cancel_button.setFont(new Font(16));
        cancel_button.setTextFill(Paint.valueOf("#2196F3"));
        cancel_button.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            dialog.close();
        });

        JFXButton yes_button = yes;
        yes_button.setPadding(new Insets(4, 16, 4, 16));
        yes_button.setFont(new Font(16));
        yes_button.setTextFill(Paint.valueOf("#2196F3"));
        yes_button.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            System.exit(0);
        });

        dialogLayout.setActions(cancel_button, yes_button);

        return dialog;
    }

    @Override
    public void takeChange() {
        setLable();
        switch (listToggle.ItemSelectNow()) {
            case 0:
                title.setText(buy_label.getText());
                break;
            case 1:
                title.setText(rent_label.getText());
                break;
            case 2:
                title.setText(setting_label.getText());
                break;
        }
    }
}
