package source;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen xl on 11/2/2016.
 */
public class ListToggle {
    private List<ListItem> list;
    private int idNow;
    private int selectNow;
    private List<SelectChangeListener> listeners;

    public ListToggle(int defaultSelect, @NotNull ListItem... items) {
        listeners = new ArrayList<SelectChangeListener>();

        list = new ArrayList<ListItem>();
        idNow = 0;
        for (ListItem tmp : items) {
            list.add(tmp);
            tmp.setToggle(this);
            tmp.setId(idNow);
            idNow++;
        }
        list.get(defaultSelect).select();
        selectNow = defaultSelect;
    }

    public void addSelectChangeListener(@NotNull SelectChangeListener listener) {
        this.listeners.add(listener);
    }

    protected void selected(int id) {
        ListItem oldValue = list.get(selectNow);
        ListItem newValue = list.get(id);

        if (listeners.size() != 0) {
            for (SelectChangeListener listener : listeners) {
                listener.selectChange(oldValue, newValue);
            }
        }
        oldValue.unSelect();
        newValue.select();
        selectNow = id;
    }

    public int ItemSelectNow(){
        return selectNow;
    }
}
