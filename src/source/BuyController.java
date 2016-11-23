package source;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Chen xl on 11/3/2016.
 */
public class BuyController implements Initializable, LanguageChangeListener {

    @FXML
    StackPane buy_root = new StackPane();

    @FXML
    Label district_label = new Label();
    @FXML
    Label district_hint = new Label();
    @FXML
    VBox district_vbox = new VBox();

    JFXListView<Label> district_list;
    JFXPopup district_popup;

    @FXML
    Label area_label = new Label();
    @FXML
    Label area_hint = new Label();
    @FXML
    VBox area_vbox = new VBox();

    JFXListView<Label> area_list;
    JFXPopup area_popup;

    @FXML
    Label type_label = new Label();
    @FXML
    Label type_hint = new Label();
    @FXML
    VBox type_vbox = new VBox();

    JFXListView<Label> type_list;
    JFXPopup type_popup;

    @FXML
    Label price_label = new Label();
    @FXML
    Label price_low_hint = new Label();
    @FXML
    Label price_high_hint = new Label();
    @FXML
    VBox price_low_vbox = new VBox();
    @FXML
    VBox price_high_vbox = new VBox();

    JFXListView<Label> price_low_list;
    JFXPopup price_low_popup;
    JFXListView<Label> price_high_list;
    JFXPopup price_high_popup;

    @FXML
    Label salable_area_label = new Label();
    @FXML
    Label salable_area_low_hint = new Label();
    @FXML
    Label salable_area_high_hint = new Label();
    @FXML
    VBox salable_area_low_vbox = new VBox();
    @FXML
    VBox salable_area_high_vbox = new VBox();

    JFXListView<Label> salable_area_low_list;
    JFXPopup salable_area_low_popup;
    JFXListView<Label> salable_area_high_list;
    JFXPopup salable_area_high_popup;

    @FXML
    Label construction_area_label = new Label();
    @FXML
    Label construction_area_low_hint = new Label();
    @FXML
    Label construction_area_high_hint = new Label();
    @FXML
    VBox construction_area_low_vbox = new VBox();
    @FXML
    VBox construction_area_high_vbox = new VBox();

    JFXListView<Label> construction_area_low_list;
    JFXPopup construction_area_low_popup;
    JFXListView<Label> construction_area_high_list;
    JFXPopup construction_area_high_popup;

    @FXML
    Label livingRoom_label = new Label();
    @FXML
    JFXSlider livingRoom_slider = new JFXSlider();

    @FXML
    Label bedroom_label = new Label();
    @FXML
    JFXSlider bedroom_slider = new JFXSlider();

    @FXML
    JFXTextField keyword = new JFXTextField();

    @FXML
    JFXButton search = new JFXButton();

    List<HouseInfo> houseList = new ArrayList<HouseInfo>();

    List<String> Kowloon = new ArrayList<String>();
    List<String> NTerr = new ArrayList<String>();
    List<String> HKIsland = new ArrayList<String>();
    List<String> outIsland = new ArrayList<String>();
    List<String> initAreaList = new ArrayList<>();
    List<String> district_content = new ArrayList<>();
    List<String> type_content;
    List<String> salable_area_high_content = new ArrayList<>();
    List<String> salable_area_low_content = new ArrayList<>();
    List<String> construction_area_low_content = new ArrayList<>();
    List<String> construction_area_high_content = new ArrayList<>();

    JFXListView<Label> hong_kong_all_list;
    JFXListView<Label> hong_kong_island_list;
    JFXListView<Label> kowloon_list;
    JFXListView<Label> new_territories_list;
    JFXListView<Label> outlying_island_list;

    public void setLable() {
        String str_livingroom_number = I18N.convert("Living Room");
        livingRoom_label.setText(str_livingroom_number);

        String str_bedroom_number = I18N.convert("Bedroom");
        bedroom_label.setText(str_bedroom_number);

        String str_search = I18N.convert("Search");
        search.setText(str_search);

        String str_district_label = I18N.convert("District");
        district_label.setText(str_district_label);

        String str_district_hint = I18N.convert("Any");
        district_hint.setText(str_district_hint);

        String str_area_label = I18N.convert("Area");
        area_label.setText(str_area_label);

        String str_area_hint = I18N.convert("Any");
        area_hint.setText(str_area_hint);

        String str_type_label = I18N.convert("Types");
        type_label.setText(str_type_label);

        String str_type_hint = I18N.convert("Any");
        type_hint.setText(str_type_hint);

        String str_price_label = I18N.convert("Price") + "/" + I18N.PurchasePriceUnit;
        price_label.setText(str_price_label);

        String str_price_low_hint = I18N.convert("Low");
        price_low_hint.setText(str_price_low_hint);

        String str_price_high_hint = I18N.convert("High");
        price_high_hint.setText(str_price_high_hint);

        String str_salable_area_label = I18N.convert("Salable Area") + "/" + I18N.convert(I18N.unit);
        salable_area_label.setText(str_salable_area_label);

        String str_salable_area_low_hint = I18N.convert("Low");
        salable_area_low_hint.setText(str_salable_area_low_hint);

        String str_salable_area_high_hint = I18N.convert("High");
        salable_area_high_hint.setText(str_salable_area_high_hint);

        String str_construction_area_label = I18N.convert("Construct Area") + "/" + I18N.convert(I18N.unit);
        construction_area_label.setText(str_construction_area_label);

        String str_construction_area_low_hint = I18N.convert("Low");
        construction_area_low_hint.setText(str_construction_area_low_hint);

        String str_construction_area_high_hint = I18N.convert("High");
        construction_area_high_hint.setText(str_construction_area_high_hint);

        String promptText = I18N.convert("Reference");
        keyword.setPromptText(promptText);
        keyword.setFocusTraversable(false);
    }

    public void setArea() {
        district_content.add("Any");
        district_content.add("Kowloon");
        district_content.add("Hong Kong Island");
        district_content.add("New Territories");
        district_content.add("Outlying Island");


        Kowloon.add("Kowloon City District");
        Kowloon.add("Kwun Tong District");
        Kowloon.add("Sham Shui Po District");
        Kowloon.add("Wong Tai Sin District");
        Kowloon.add("Yau Tsim Mong District");


        NTerr.add("Kwai Tsing District");
        NTerr.add("North District");
        NTerr.add("Sai Kung District");
        NTerr.add("Sha Tin District");
        NTerr.add("Tai Po District");
        NTerr.add("Tsuen Wan District");
        NTerr.add("Tuen Mun District");
        NTerr.add("Yuen Long District");


        HKIsland.add("Central and Western District");
        HKIsland.add("Eastern District");
        HKIsland.add("Southern District");
        HKIsland.add("Wan Chai District");

        outIsland.add("Any");
        outIsland.add("Outlying Island");

        initAreaList.addAll(outIsland);
        initAreaList.addAll(HKIsland);
        initAreaList.addAll(Kowloon);
        initAreaList.addAll(NTerr);

        HKIsland.add("Any");
        NTerr.add("Any");
        Kowloon.add("Any");
    }

    public String csv(String str) {
        Locale currentLocale = new Locale("en","US");
        ResourceBundle message = ResourceBundle.getBundle("convert.csv", currentLocale);
        if(message.containsKey(str)) return message.getString(str);
        else return str;
    }

    public void loadCSV() {
        System.out.println("Loading house CSV...");
        FileInputStream fileIn;
        try {
            fileIn = new FileInputStream("./src/text/CN.csv");
            InputStreamReader isr = new InputStreamReader(fileIn,"BIG5");
            BufferedReader br = new BufferedReader(isr);
            String line;
            boolean start = false;
            while ((line = br.readLine()) != null) {
                if (!start) {
                    start = true;
                    continue;
                }
                String[] attrs = line.split(",");
                if (attrs[1].equals("-1"))
                    continue;
                for(int i=0;i<attrs.length;i++)
                    attrs[i] = csv(attrs[i]);
                HouseInfo house = new HouseInfo(attrs);
                houseList.add(house);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void areaList(){
        salable_area_low_content.clear();
        salable_area_high_content.clear();
        construction_area_high_content.clear();
        construction_area_low_content.clear();
        int unitTime = I18N.convert("Sq.m").equals(I18N.convert(I18N.unit)) ? 1 : 10;

        salable_area_low_content.add(I18N.convert("Low"));
        for (int i = 0; i < 10; i++) {
            salable_area_low_content.add("" + (20 * i + 20) * unitTime);
        }
        salable_area_low_list = ListViewHelper(salable_area_low_content, salable_area_low_hint, 161);

        salable_area_high_content.add(I18N.convert("High"));
        for (int i = 0; i < 10; i++) {
            salable_area_high_content.add("" + (20 * i + 20) * unitTime);
        }
        salable_area_high_list = ListViewHelper(salable_area_high_content, salable_area_high_hint, 161);

        salable_area_low_popup = PopupHelper(salable_area_low_list, buy_root, salable_area_low_vbox);
        salable_area_high_popup = PopupHelper(salable_area_high_list, buy_root, salable_area_high_vbox);

        for (Label tmp : salable_area_low_list.getItems()) {
            tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                salable_area_low_popup.close();
            });
        }

        for (Label tmp : salable_area_high_list.getItems()) {
            tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                salable_area_high_popup.close();
            });
        }

        salable_area_low_hint.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            salable_area_low_popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT);
        });

        salable_area_high_hint.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            salable_area_high_popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT);
        });

        construction_area_low_content.add(I18N.convert("Low"));
        for (int i = 0; i < 10; i++) {
            construction_area_low_content.add("" + (20 * i + 20) * unitTime);
        }
        construction_area_low_list = ListViewHelper(construction_area_low_content, construction_area_low_hint, 161);

        construction_area_high_content.add(I18N.convert("High"));
        for (int i = 0; i < 10; i++) {
            construction_area_high_content.add("" + (20 * i + 20) * unitTime);
        }
        construction_area_high_list = ListViewHelper(construction_area_high_content, construction_area_high_hint, 161);

        construction_area_low_popup = PopupHelper(construction_area_low_list, buy_root, construction_area_low_vbox);
        construction_area_high_popup = PopupHelper(construction_area_high_list, buy_root, construction_area_high_vbox);

        for (Label tmp : construction_area_low_list.getItems()) {
            tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                construction_area_low_popup.close();
            });
        }

        for (Label tmp : construction_area_high_list.getItems()) {
            tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                construction_area_high_popup.close();
            });
        }

        construction_area_low_hint.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            construction_area_low_popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT);
        });

        construction_area_high_hint.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            construction_area_high_popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT);
        });
    }

    private ResultItem getResultInfo(HouseInfo house) {
        ResultItem item = new ResultItem();
        String pageURL = "/image/house" + house.refID + ".jpg";
        item.setImage(pageURL);
        item.setArea(area_label.getText(), I18N.convert(house.area));
        item.setDistrict(district_label.getText(), I18N.convert(house.getDistrict()));
        item.setTypes(type_label.getText(), I18N.convert(house.getType()));
        item.setBedroom(bedroom_label.getText(), "" + house.getBedroomNum());
        DecimalFormat df = new DecimalFormat("######0.00");
        Double realPrice = (double) house.getPurchasePrice() / 100 * Double.valueOf(CurrentRate.getHKRate(I18N.country)) / I18N.PurchasePriceDivide;
        System.out.println(I18N.unit);
        System.out.println(I18N.convert("Sq.m"));
        int unitTime = I18N.convert("Sq.m").equals(I18N.convert(I18N.unit)) ? 1 : 9;
        item.setPrice(price_label.getText(), String.valueOf(df.format(realPrice)) + " " + I18N.PurchasePriceUnit);
        item.setSalableArea(salable_area_label.getText(), String.valueOf(df.format(house.getSalableArea() * unitTime)) + " " + I18N.convert(I18N.unit));
        item.setConstructArea(construction_area_label.getText(), String.valueOf(df.format(house.getConstructArea() * unitTime)) + " " + I18N.convert(I18N.unit));
        item.setLivingroom(livingRoom_label.getText(), "" + String.valueOf(house.getLivNum()));
        item.setReleaseDate(I18N.convert("Release Date"), "(UTC+8) "+I18N.setDateI18N(house.releaseDate));
        item.setLocation(I18N.convert("Address"), I18N.convert(house.address));
        return item;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LanguageChangeManager.getManager().registry(this);

        I18N.setPriceI18N();
        loadCSV();
        setArea();
        setLable();
        areaList();

        type_content = new ArrayList<>();
        type_content.add("Any");
        type_content.add("Fine Decoration");
        type_content.add("Simple Decoration");
        type_content.add("No Decoration");
        type_list = ListViewHelper(type_content, type_hint, 264);

        district_list = ListViewHelper(district_content, district_hint, 264);
        district_popup = PopupHelper(district_list, buy_root, district_vbox);

        search.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            List<ResultItem> itemList = new ArrayList<ResultItem>();
            List<String> searchRules = new ArrayList<String>();
            searchRules.add("area_label.getText()");
            SearchInfo serInfo = new SearchInfo(getSearchRuleList());
            System.out.println("Searching legal houses,house size are : " + houseList.size());
            for (HouseInfo house : houseList) {
                System.out.println(house);
                if (serInfo.isLegal(house)) {
                    itemList.add(getResultInfo(house));
                }
            }
            try {
                SearchResultLoader result = new SearchResultLoader();
                for (int i = 0; i < itemList.size(); i++)
                    result.addItem(itemList.get(i));
                result.showStage("Result");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        hong_kong_all_list = ListViewHelper(initAreaList, area_hint, 264);
        hong_kong_island_list = ListViewHelper(HKIsland, area_hint, 264);
        kowloon_list = ListViewHelper(Kowloon, area_hint, 264);
        new_territories_list = ListViewHelper(NTerr, area_hint, 264);
        outlying_island_list = ListViewHelper(outIsland, area_hint, 264);

        area_list = hong_kong_all_list;
        area_popup = PopupHelper(area_list, buy_root, area_vbox);

        for (Label tmp : district_list.getItems()) {
            tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                String curDistrict = tmp.getText();
                if (curDistrict.equals(I18N.convert("Hong Kong Island")))
                    area_list = hong_kong_island_list;
                else if (curDistrict.equals(I18N.convert("Kowloon")))
                    area_list = kowloon_list;
                else if (curDistrict.equals(I18N.convert("New Territories")))
                    area_list = new_territories_list;
                else if (curDistrict.equals(I18N.convert("Outlying Island")))
                    area_list = outlying_island_list;

                area_popup = PopupHelper(area_list, buy_root, area_vbox);
                for (Label tmpArea : area_list.getItems()) {
                    tmpArea.addEventHandler(MouseEvent.MOUSE_CLICKED, (eventArea) -> {
                        area_popup.close();
                    });
                }
                district_popup.close();
            });
        }

        district_hint.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            district_popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT);
        });

        for (Label tmp : area_list.getItems()) {
            tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                area_popup.close();
            });
        }

        area_hint.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            area_popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT);
        });


        type_popup = PopupHelper(type_list, buy_root, type_vbox);

        for (Label tmp : type_list.getItems()) {
            tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                type_popup.close();
            });
        }

        type_hint.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            type_popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT);
        });

        List<String> price_low_content = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            price_low_content.add("" + (i * I18N.pgap + I18N.pbase) / I18N.PurchasePriceDivide);
        }
        price_low_list = ListViewHelper(price_low_content, price_low_hint, 161);

        List<String> price_high_content = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            price_high_content.add("" + (i * I18N.pgap + I18N.pbase) / I18N.PurchasePriceDivide);
        }
        price_high_list = ListViewHelper(price_high_content, price_high_hint, 161);

        price_low_popup = PopupHelper(price_low_list, buy_root, price_low_vbox);
        price_high_popup = PopupHelper(price_high_list, buy_root, price_high_vbox);

        for (Label tmp : price_low_list.getItems()) {
            tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                price_low_popup.close();
            });
        }

        for (Label tmp : price_high_list.getItems()) {
            tmp.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                price_high_popup.close();
            });
        }

        price_low_hint.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            price_low_popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT);
        });

        price_high_hint.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            price_high_popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT);
        });
    }

    private JFXListView<Label> ListViewHelper(List<String> content, Label hint, int length) {
        JFXListView<Label> listview = new JFXListView<Label>();
        listview.setMinWidth(length);
        listview.setMaxWidth(length);
        for (String str : content) {
            Label tmpLabel = new Label(I18N.convert(str));
            tmpLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                hint.setText(I18N.convert(tmpLabel.getText()));
            });
            listview.getItems().add(tmpLabel);
        }
        return listview;
    }

    private JFXPopup PopupHelper(Region content, Pane popupContainer, Node source) {
        JFXPopup popup = new JFXPopup();
        popup.setContent(content);
        popup.setPopupContainer(popupContainer);
        popup.setSource(source);
        popup.setTranslateY(30);
        return popup;
    }

    private List<String> getSearchRuleList() {
        List<String> searchRules = new ArrayList<String>();
        searchRules.add(district_hint.getText());
        searchRules.add(area_hint.getText());
        searchRules.add(type_hint.getText());
        searchRules.add(String.valueOf(bedroom_slider.getValue()));
        searchRules.add(String.valueOf(livingRoom_slider.getValue()));
        searchRules.add(price_low_hint.getText());
        searchRules.add(price_high_hint.getText());
        searchRules.add(salable_area_low_hint.getText());
        searchRules.add(salable_area_high_hint.getText());
        searchRules.add(construction_area_low_hint.getText());
        searchRules.add(construction_area_high_hint.getText());
        searchRules.add(keyword.getText());
        return searchRules;
    }

    public void changelistitem(JFXListView<Label> list, List<String> content) {
        for (int i = 0; i < list.getItems().size(); i++) {
            list.getItems().get(i).setText(I18N.convert(content.get(i)));
        }
    }

    @Override
    public void takeChange() {
        setLable();
        areaList();
        changelistitem(district_list, district_content);
        changelistitem(type_list, type_content);
        changelistitem(hong_kong_all_list, initAreaList);
        changelistitem(hong_kong_island_list, HKIsland);
        changelistitem(kowloon_list, Kowloon);
        changelistitem(new_territories_list, NTerr);
        changelistitem(outlying_island_list, outIsland);
        changelistitem(construction_area_high_list,construction_area_high_content);
        changelistitem(construction_area_low_list,construction_area_low_content);
        changelistitem(salable_area_high_list,salable_area_high_content);
        changelistitem(salable_area_low_list,salable_area_low_content);
    }
}
