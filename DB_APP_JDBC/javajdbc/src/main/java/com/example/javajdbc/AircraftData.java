package com.example.javajdbc;

public class AircraftData {
    private int aircraftNo;
    private String kindOfAircraft;
    private String airline;
    private String deparetures;
    private String arrival;
    private String flightDate;

    public AircraftData(int aircraftNo, String kindOfAircraft, String airline, String deparetures, String arrival, String flightDate) {
        this.aircraftNo = aircraftNo;
        this.kindOfAircraft = kindOfAircraft;
        this.airline = airline;
        this.deparetures = deparetures;
        this.arrival = arrival;
        this.flightDate = flightDate;
    }

    public int getAircraftNo() {
        return aircraftNo;
    }

    public void setAircraftNo(int aircraftNo) {
        this.aircraftNo = aircraftNo;
    }

    public String getKindOfAircraft() {
        return kindOfAircraft;
    }

    public void setKindOfAircraft(String kindOfAircraft) {
        this.kindOfAircraft = kindOfAircraft;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
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

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }
}
