package com.nhnacademy.edu.springframework.project.repository;

public class WaterBill {

    private String city;

    private String sector;

    private int unitPrice;
    private long billTotal;

    public WaterBill(String city, String sector, int unitPrice) {
        this.city = city;
        this.sector = sector;
        this.unitPrice = unitPrice;
    }
    public String getCity() {
        return city;
    }

    public String getSector() {
        return sector;
    }

    public int getUnitPrice() {
        return unitPrice;
    }
    public void setBillTotal(int usage){
        this.billTotal = unitPrice*usage;
    }
    public long getBillTotal(){
        return billTotal;
    }
    @Override
    public String toString(){
        return "WaterBill{" +
                "city='"+city+"', "+
                "sector='"+sector+"', "+
                "unitPrice='"+unitPrice+"', "+
                "billTotal='"+billTotal+"'}";
    }
}
