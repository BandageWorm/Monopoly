package source;

import java.util.Date;

/**
 * Created by chenj on 2016/11/9.
 */
public class HouseInfo {
    String refID;
    String type;
    String district;
    String address;

    String area;
    boolean isRentable;
    boolean isPurchaseable;
    int rentPrice;
    int purchasePrice;
    int bedroomNum;
    int livNum;
    String releaseDate;
    double salableArea;
    double constructArea;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRefID() {
        return refID;
    }

    public void setRefID(String refID) {
        this.refID = refID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isRentable() {
        return isRentable;
    }

    public void setRentable(boolean rentable) {
        isRentable = rentable;
    }

    public boolean isPurchaseable() {
        return isPurchaseable;
    }

    public void setPurchaseable(boolean purchaseable) {
        isPurchaseable = purchaseable;
    }

    public int getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getBedroomNum() {
        return bedroomNum;
    }

    public void setBedroomNum(int bedroomNum) {
        this.bedroomNum = bedroomNum;
    }

    public int getLivNum() {
        return livNum;
    }

    public void setLivNum(int livNum) {
        this.livNum = livNum;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getSalableArea() {
        return salableArea;
    }

    public void setSalableArea(double salableArea) {
        this.salableArea = salableArea;
    }

    public double getConstructArea() {
        return constructArea;
    }

    public void setConstructArea(double constructArea) {
        this.constructArea = constructArea;
    }

    public HouseInfo(String[] attrs) {
        this.refID = attrs[0];
        if (!attrs[1].equals("-1")) {
            this.purchasePrice = Integer.valueOf(attrs[1]);
            this.isPurchaseable = true;
        } else
            this.isPurchaseable = false;
        if (!attrs[2].equals("-1")) {
            this.rentPrice = Integer.valueOf(attrs[2]);
            this.isRentable = true;
        } else
            this.isRentable = false;

        setDistrict(attrs[3]);
        setAddress(attrs[4]);
        setBedroomNum(Double.valueOf(attrs[5]).intValue());
        setLivNum(Double.valueOf(attrs[6]).intValue());
        setReleaseDate(attrs[7]);
        setSalableArea(Double.valueOf(attrs[8]));
        setConstructArea(Double.valueOf(attrs[9]));
        setType(attrs[10]);
        setArea(attrs[11]);
    }

}
