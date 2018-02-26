package com.travix.medusa.busyflights.domain.toughjet;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class ToughJetConverter {

    private static final String SUPPLIER = "ToughJet";

    private ToughJetConverter(){}

    public static ToughJetRequest toToughJetRequest(BusyFlightsRequest busyFlightsRequest) {
        ToughJetRequest toughJetRequest = new ToughJetRequest();
        toughJetRequest.setFrom(busyFlightsRequest.getOrigin());
        toughJetRequest.setTo(busyFlightsRequest.getDestination());
        toughJetRequest.setOutboundDate(
                busyFlightsRequest.getDepartureDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        toughJetRequest.setInboundDate(
                busyFlightsRequest.getReturnDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        toughJetRequest.setNumberOfAdults(busyFlightsRequest.getNumberOfPassengers());
        return toughJetRequest;
    }

    public static BusyFlightsResponse toBusyFlightsResponse(ToughJetResponse toughJetResponse) {
        BusyFlightsResponse response = new BusyFlightsResponse();
        response.setAirline(toughJetResponse.getCarrier());
        response.setSupplier(SUPPLIER);
        response.setFare(calculateFare(toughJetResponse));
        response.setDepartureAirportCode(toughJetResponse.getDepartureAirportName());
        response.setDestinationAirportCode(toughJetResponse.getArrivalAirportName());
        response.setDepartureDate(toughJetResponse.getOutboundDateTime());
        response.setArrivalDate(toughJetResponse.getInboundDateTime());
        return response;
    }

    private static String calculateFare(ToughJetResponse toughJetResponse) {
        BigDecimal basePrice = BigDecimal.valueOf(toughJetResponse.getBasePrice());
        BigDecimal discount = BigDecimal.valueOf(toughJetResponse.getDiscount()).divide(BigDecimal.valueOf(100));
        BigDecimal tax = BigDecimal.valueOf(toughJetResponse.getTax()).divide(BigDecimal.valueOf(100));
        BigDecimal basePriceWithDiscount=basePrice.subtract(basePrice.multiply(discount));
        return basePriceWithDiscount.add(basePriceWithDiscount.multiply(tax)).setScale(2,BigDecimal.ROUND_HALF_EVEN).toPlainString();
    }
}
