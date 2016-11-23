package source;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen xl on 11/13/2016.
 */

public class LanguageChangeManager {

    private List<LanguageChangeListener> changeList;

    private static LanguageChangeManager singleManager = new LanguageChangeManager();

    private LanguageChangeManager() {
        changeList = new ArrayList<>();
    }

    public static LanguageChangeManager getManager() {
        return singleManager;
    }

    public void registry(LanguageChangeListener listener) {
        changeList.add(listener);
    }

    public void change() {
        for (LanguageChangeListener tmp : changeList) {
            tmp.takeChange();
        }
    }
}
