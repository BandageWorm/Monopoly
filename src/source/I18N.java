package source;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class I18N {
    static String country;
    static String language;
    static int languageIndex;
    static String unit;
    static int pbase, pgap, rbase, rgap, PurchasePriceDivide, RentPriceDivide;
    static String runit, punit, PurchasePriceUnit, RentPriceUnit;
    static SimpleDateFormat localDate;

    public static void setDefaultConfig() {
        String country = "";
        File countryFile = new File("./src/text/defaultCountry.txt");
        if (!countryFile.exists()) {
            System.out.println("defaultCountry file not exist");
            I18N.country = "HK";
            I18N.language = "zh";
        }
        try {
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(countryFile), "UTF-8");
//			String line;
            BufferedReader bufferedReader = new BufferedReader(read);
            country = bufferedReader.readLine();
            I18N.unit = bufferedReader.readLine();
            read.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setCountry(country);
    }

    public static void setDefault() {
        setDefaultConfig();
    }

    public static void writeDefaultConfig() {
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("./src/text/defaultCountry.txt");
            OutputStreamWriter osw = new OutputStreamWriter(fileOut);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(I18N.country + "\n");
            bw.write(I18N.unit + "\n");
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public I18N(String lan, String cnry) {
        this.country = cnry;
        this.language = lan;
    }

    public static void setCountry(String cnry) {
        System.out.println("New Country is: " + cnry);
        Map<String, String> lanCtry = new HashMap<>();
        lanCtry.put("HK", "zh");
        lanCtry.put("CN", "zh");
        lanCtry.put("US", "en");
        lanCtry.put("KR", "ko");
        lanCtry.put("JP", "ja");

        Map<String, Integer> lanIndex = new HashMap<>();
        lanIndex.put("CN", 0);
        lanIndex.put("HK", 1);
        lanIndex.put("US", 2);
        lanIndex.put("KR", 3);
        lanIndex.put("JP", 4);

        I18N.country = cnry;
        I18N.language = lanCtry.get(cnry);
        I18N.languageIndex = lanIndex.get(cnry);
    }

    public static void setPriceI18N() {
        if (I18N.country.equals("KR")) {
            rbase = 7000000;
            rgap = 3000000;
            pbase = 400000000;
            pgap = 150000000;
            runit = I18N.convert("ten K ");
            punit = I18N.convert("M ");
        } else if (I18N.country.equals("HK") || I18N.country .equals("CN")) {
            rbase = 50000;
            rgap = 20000;
            pbase = 3000000;
            pgap = 1000000;
            runit = I18N.convert("ten K ");
            punit = I18N.convert("ten K ");
        } else if (I18N.country.equals("JP")) {
            rbase = 700000;
            rgap = 300000;
            pbase = 40000000;
            pgap = 15000000;
            runit = I18N.convert("ten K ");
            punit = I18N.convert("M ");
        } else {
            rbase = 6000;
            rgap = 2000;
            pbase = 350000;
            pgap = 100000;
            runit = I18N.convert("K ");
            punit = I18N.convert("K ");
        }

        if (I18N.punit .equals(I18N.convert("K "))) PurchasePriceDivide = 1000;
        else if (I18N.punit.equals(I18N.convert("M "))) PurchasePriceDivide = 1000000;
        else PurchasePriceDivide = 10000;

        if (I18N.runit.equals(I18N.convert("K "))) RentPriceDivide = 1000;
        else if (I18N.runit.equals(I18N.convert("M "))) RentPriceDivide = 1000000;
        else RentPriceDivide = 10000;

        PurchasePriceUnit = I18N.punit + I18N.convert(CurrentRate.getDollorName());
        RentPriceUnit = I18N.runit + I18N.convert(CurrentRate.getDollorName());
    }

    public static String setDateI18N(String dateStr){
        String dateI18N="";
        if (I18N.country.equals("HK")) {
            localDate = new SimpleDateFormat("d/M/yyyy");
        } else if (I18N.country.equals("US")) {
            localDate = new SimpleDateFormat("M/d/yyyy");
        } else if (I18N.country.equals("CN")) {
            localDate = new SimpleDateFormat("yy/M/d");
        } else if (I18N.country.equals("JP")) {
            localDate = new SimpleDateFormat("yyyy/MM/dd");
        }else{
            localDate = new SimpleDateFormat("yyyy-MM-dd");
        }
        SimpleDateFormat getDateFormat= new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date = getDateFormat.parse(dateStr);
            dateI18N = localDate.format(date);
        }catch (Exception ex){ex.printStackTrace();}
        finally {
            return dateI18N;
        }
    }

    public static String convert(String str) {
        if (I18N.country.length() < 1 || I18N.language.length() < 1)
            return str;
        if (I18N.country.equals("US") && I18N.language.equals("en"))
            return str;
        Locale currentLocale = new Locale(language, country);
        ResourceBundle message = ResourceBundle.getBundle("convert.convert", currentLocale);
        if (message.containsKey(str))
            return message.getString(str);
        return str;
    }
}
