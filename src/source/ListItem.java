package source;

import com.jfoenix.controls.JFXButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Created by Chen xl on 11/2/2016.
 */
public class ListItem {
    private Pane pane;
    private JFXButton button;
    private int id;
    private ListToggle toggle;
    private String label;

    public ListItem(String label, Pane pane, JFXButton button) {
        this.label = label;
        this.pane = pane;
        pane.setVisible(false);
        this.button = button;
    }

    public void setToggle(ListToggle toggle) {
        this.toggle = toggle;
        this.button.addEventHandler(MouseEvent.MOUSE_PRESSED, (event) -> {
            this.toggle.selected(this.id);
        });
    }

    public String getLabel() {
        return this.label;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void select() {
        this.pane.setVisible(true);
    }

    public void unSelect() {
        this.pane.setVisible(false);
    }
}
