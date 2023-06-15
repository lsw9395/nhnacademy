package com.example.javajdbc;

public class FlightData {
    private String reservedData;
    private String deparetures;
    private String arrival;
    private int price;
    private String flightDate;
    private int aircraftNo;

    public FlightData(int aircraftNo,String reservedData, String deparetures, String arrival, int price, String flightDate) {
        this.aircraftNo = aircraftNo;
        this.reservedData = reservedData;
        this.deparetures = deparetures;
        this.arrival = arrival;
        this.price = price;
        this.flightDate = flightDate;
    }

    public int getAircraftNo() {
        return aircraftNo;
    }

    public void setAircraftNo(int aircraftNo) {
        this.aircraftNo = aircraftNo;
    }

    public String getReservedData() {
        return reservedData;
    }

    public void setReservedData(String reservedData) {
        this.reservedData = reservedData;
    }

    public String getDeparetures() {
        return deparetures;
    }

    public void setDeparetures(String deparetures) {
        this.deparetures = deparetures;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }
}
