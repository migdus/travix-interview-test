package com.travix.medusa.busyflights.domain.crazyair;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CrazyAirConverter {

    private static final String SUPPLIER = "CrazyAir";

    private CrazyAirConverter(){}

    public static CrazyAirRequest toCrazyAirRequest(BusyFlightsRequest busyFlightsRequest) {
        CrazyAirRequest request = new CrazyAirRequest();
        request.setOrigin(busyFlightsRequest.getOrigin());
        request.setDestination(busyFlightsRequest.getDestination());
        request.setDepartureDate(
                busyFlightsRequest.getDepartureDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        request.setReturnDate(
                busyFlightsRequest.getReturnDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        request.setPassengerCount(busyFlightsRequest.getNumberOfPassengers());
        return request;
    }

    public static BusyFlightsResponse toBusyFlightsResponse(CrazyAirResponse crazyAirResponse) {
        BusyFlightsResponse response = new BusyFlightsResponse();
        response.setAirline(crazyAirResponse.getAirline());
        response.setSupplier(SUPPLIER);
        response.setFare(
                BigDecimal.valueOf(crazyAirResponse.getPrice())
                        .setScale(2,BigDecimal.ROUND_HALF_EVEN).toPlainString());
        response.setDepartureAirportCode(crazyAirResponse.getDepartureAirportCode());
        response.setDestinationAirportCode(crazyAirResponse.getDestinationAirportCode());
        response.setDepartureDate(parseDate(crazyAirResponse.getDepartureDate()));
        response.setArrivalDate(parseDate(crazyAirResponse.getArrivalDate()));
        return response;
    }

    private static String parseDate(String crazyAirDate) {
        return LocalDateTime.parse(crazyAirDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                .format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
