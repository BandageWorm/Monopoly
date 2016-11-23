package source;

import java.io.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by chenj on 2016/11/8.
 */
public class CurrentRate {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 3000;
    public static final int DEF_READ_TIMEOUT = 3000;
    public static final String APPKEY = "6650584f7acfd5ffeb31319a7a3b38d1";
    public static String defaultRate;
    public static HashMap<String, Double> rateMap;

    public static String getTotRate(String strUrl) {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = "";
        try {
            System.out.println(strUrl);
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.connect();
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            rs = sb.toString();
        } catch (IOException e) {
            rs = "";
            System.out.println("time out");
//            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;

    }

    public static String getRateJson() {
        String totRate = "";
        String url = "http://web.juhe.cn:8080/finance/exchange/rmbquot";
        url += "?type=0&bank=3&key=";
        url += APPKEY;
        totRate = getTotRate(url);
        if (totRate.length() < 1)
            return totRate;
        if (totRate.charAt(0) != '{')
            totRate = totRate.substring(1, totRate.length());
        System.out.println(totRate);
        return totRate;
    }

    public static void writeDefaultRate() {
        String line = getRateJson();
        if (line.length() < 1)
            return;
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream("src/text/defaultRate.txt");
            OutputStreamWriter osw = new OutputStreamWriter(fileOut, DEF_CHATSET);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(line + "\n");
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getDefaultRate() {
        StringBuilder sb = new StringBuilder();
        FileInputStream fileIn;
        try {
            fileIn = new FileInputStream("src/text/defaultRate.txt");
            InputStreamReader isr = new InputStreamReader(fileIn, DEF_CHATSET);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void getRate() {
        String strJson = getDefaultRate();
        HashMap<String, Double> rateMap = new HashMap<>();

        JSONObject jsonObject = JSONObject.fromObject(strJson);
        String ratesJons = jsonObject.get("result").toString();
        ratesJons = ratesJons.substring(1, ratesJons.length() - 1);

        JSONObject jsonRate = JSONObject.fromObject(ratesJons);
        System.out.println(jsonRate);
        for (int i = 1; i <= 20; i++) {
            String data = "data" + String.valueOf(i);
            String srate = jsonRate.getString(data);
            JSONObject rate = JSONObject.fromObject(srate);
            String pri = rate.getString("fSellPri");
            if (pri.equals("null"))
                continue;
//            System.out.println(i + ":" +rate.getString("name"));
            System.out.println("name?!" + rate.getString("name"));
            rateMap.put(rate.getString("name"), Double.valueOf(pri));
        }
//        rateMap.put("韩元", 0.6002);
        CurrentRate.rateMap = rateMap;
    }

    public static String getHKRate(String aimCry) {
        String ctry;
        if (aimCry.equals("CN")) {
            return CurrentRate.rateMap.get("港币").toString();
        } else if (aimCry.equals("US")) {
            ctry = "美元";
        } else if (aimCry.equals("KR")) {
            ctry = "韩国元";
        } else if (aimCry.equals("JP")) {
            ctry = "日元";
        } else {
            return "100";
        }
        System.out.println(CurrentRate.rateMap.get("港币"));
        double HKrate = CurrentRate.rateMap.get("港币");
        System.out.println("HKrate  " + HKrate);
        double rate = CurrentRate.rateMap.get(ctry);
        double res = HKrate * (100.0 / rate);
        DecimalFormat df = new DecimalFormat("######0.00");
        return df.format(res);
    }

    public static String getDollorName() {
        if (I18N.country.equals("CN"))
            return "CNY";
        if (I18N.country.equals("JP"))
            return "JPY";
        if (I18N.country.equals("KR"))
            return "KRW";
        if (I18N.country.equals("US"))
            return "USD";
        return "HKD";
    }
}
