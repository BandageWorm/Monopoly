package source;

import sun.rmi.server.InactiveGroupException;

import java.util.List;

/**
 * Created by chenj on 2016/11/9.
 */
public class SearchInfo {
    String district;
    String area;
    String type;
    String keyWords;
    int bedRoomNum;
    int livNum;
    int priceLowBound;
    int priceHighBound;
    int salableAreaLowBound;
    int salableAreaHighBound;
    int constructAreaLowBound;
    int constructAreaHighBound;
    static final int lowbound = 0;
    static final int highbound = Integer.MAX_VALUE;

    public SearchInfo(List<String> attrs){
        I18N.setPriceI18N();
        System.out.println(attrs);
        for(int i = 0;i < attrs.size();i++){
            if(attrs.get(i).equals(I18N.convert("Low")))
                attrs.set(i, String.valueOf(lowbound));
            if(attrs.get(i).equals(I18N.convert("High")))
                attrs.set(i, String.valueOf(highbound));
        }
        this.district = attrs.get(0);
        this.area = attrs.get(1);
        this.type = attrs.get(2);
        this.bedRoomNum = Double.valueOf(attrs.get(3)).intValue();
        this.livNum = Double.valueOf(attrs.get(4)).intValue();
        this.priceLowBound = Double.valueOf(attrs.get(5)).intValue();
        this.priceHighBound = Double.valueOf(attrs.get(6)).intValue();
        this.salableAreaLowBound = Double.valueOf(attrs.get(7)).intValue();
        this.salableAreaHighBound = Double.valueOf(attrs.get(8)).intValue();
        this.constructAreaLowBound = Double.valueOf(attrs.get(9)).intValue();
        this.constructAreaHighBound = Double.valueOf(attrs.get(10)).intValue();
        this.keyWords = attrs.get(11);
    }

    public boolean isLegal(HouseInfo house){

        double rate = Double.valueOf(CurrentRate.getHKRate(I18N.country)) /100;

        if(!keyWords.equals(house.refID) && keyWords.length() > 0)
            return false;

        if(!this.type.equals(I18N.convert(house.type)) && !this.type.equals(I18N.convert("Any")))
            return false;

        if(!this.area.equals(I18N.convert(house.area)) && !this.area.equals(I18N.convert("Any")))
            return false;

        if(!this.district.equals(I18N.convert(house.district)) && !this.district.equals(I18N.convert("Any")))
            return false;

        if(house.bedroomNum != this.bedRoomNum)
            return false;

        if(house.livNum != this.livNum)
            return false;

        if(house.isRentable){
            if(this.priceHighBound==Integer.MAX_VALUE)
                this.priceHighBound/=I18N.RentPriceDivide;
            if(house.rentPrice < this.priceLowBound*I18N.RentPriceDivide/rate || house.rentPrice > this.priceHighBound*I18N.RentPriceDivide/rate)
                return false;
        }

        if(house.isPurchaseable){
            if(this.priceHighBound==Integer.MAX_VALUE)
                this.priceHighBound/=I18N.PurchasePriceDivide;
            if(house.purchasePrice < this.priceLowBound*I18N.PurchasePriceDivide/rate || house.purchasePrice > this.priceHighBound*I18N.PurchasePriceDivide/rate)
                return false;
        }

        if(house.salableArea < this.salableAreaLowBound || house.salableArea > this.salableAreaHighBound)
            return false;

        if(house.constructArea < this.constructAreaLowBound || house.constructArea > this.constructAreaHighBound)
            return false;
        return true;
    }
}
