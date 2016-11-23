package source;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Chen xl on 11/4/2016.
 */
public class SettingController implements Initializable,LanguageChangeListener {

    @FXML
    Label language_label = new Label();
    @FXML
    Pane china_pane = new Pane();
    @FXML
    JFXButton china_button = new JFXButton();
    @FXML
    Pane hk_pane = new Pane();
    @FXML
    JFXButton hk_button = new JFXButton();
    @FXML
    Pane english_pane = new Pane();
    @FXML
    JFXButton english_button = new JFXButton();
    @FXML
    Pane korea_pane = new Pane();
    @FXML
    JFXButton korea_button = new JFXButton();
    @FXML
    Pane jp_pane = new Pane();
    @FXML
    JFXButton jp_button = new JFXButton();
    @FXML
    Label unit_label = new Label();
    @FXML
    JFXRadioButton meter = new JFXRadioButton();
    @FXML
    JFXRadioButton foot = new JFXRadioButton();

    ListToggle listToggle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LanguageChangeManager.getManager().registry(this);

        String str_language = I18N.convert("Language");
        language_label.setText(str_language);

        ListItem item_china = new ListItem("CN", china_pane, china_button);
        ListItem item_hk = new ListItem("HK", hk_pane, hk_button);
        ListItem item_english = new ListItem("US", english_pane, english_button);
        ListItem item_korea = new ListItem("KR", korea_pane, korea_button);
        ListItem item_jp = new ListItem("JP", jp_pane, jp_button);

        int defaultValue = I18N.languageIndex;
        listToggle = new ListToggle(defaultValue, item_china, item_hk, item_english, item_korea, item_jp);

        listToggle.addSelectChangeListener((oldValue, newValue) -> {
            I18N.setCountry(newValue.getLabel());
            I18N.setPriceI18N();
            LanguageChangeManager.getManager().change();
            I18N.writeDefaultConfig();
        });

        String str_unit = I18N.convert("Unit");
        unit_label.setText(str_unit);
        String str_meter = I18N.convert("Metric");
        meter.setText(str_meter);
        String str_foot = I18N.convert("English");
        foot.setText(str_foot);

        ToggleGroup group = new ToggleGroup();
        meter.setToggleGroup(group);
        foot.setToggleGroup(group);

        if(I18N.unit.equals("Sq.m")) {
            meter.setSelected(true);
        }else {
            foot.setSelected(true);
        }

        group.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Old Value: " + ((JFXRadioButton) oldValue).getText());
            System.out.println("New Value: " + ((JFXRadioButton) newValue).getText());
            if(((JFXRadioButton) newValue).getText().equals(I18N.convert("Metric")))
                I18N.unit = "Sq.m";
            else
                I18N.unit = "Sq.ft";
            LanguageChangeManager.getManager().change();
            I18N.writeDefaultConfig();
        });
    }

    @Override
    public void takeChange() {
        String str_language = I18N.convert("Language");
        language_label.setText(str_language);
        String str_unit = I18N.convert("Unit");
        unit_label.setText(str_unit);
        String str_meter = I18N.convert("Metric");
        meter.setText(str_meter);
        String str_foot = I18N.convert("English");
        foot.setText(str_foot);
    }
}
