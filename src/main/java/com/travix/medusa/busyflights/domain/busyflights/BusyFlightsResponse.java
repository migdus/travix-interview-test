package com.travix.medusa.busyflights.domain.busyflights;

public class BusyFlightsResponse {
    private String airline;
    private String supplier;
    private String fare;
    private String departureAirportCode;
    private String destinationAirportCode;
    private String departureDate;
    private String arrivalDate;

    public String getAirline() {
        return airline;
    }

    public String getSupplier() {
        return supplier;
    }

    public String getFare() {
        return fare;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void setFare(String fare) {
        this.fare = fare;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public void setDestinationAirportCode(String destinationAirportCode) {
        this.destinationAirportCode = destinationAirportCode;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}
