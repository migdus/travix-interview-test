package com.travix.medusa.busyflights.domain.toughjet;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ToughJetConverterTest {
    private BusyFlightsRequest busyFlightRequest;
    private ToughJetResponse toughJetResponse;
    @Before
    public void before(){
        initBusyFlightRequest();
        initToughJetResponse();
    }

    private void initBusyFlightRequest(){
        busyFlightRequest=new BusyFlightsRequest();
        busyFlightRequest.setNumberOfPassengers(3);
        busyFlightRequest.setDestination("AER");
        busyFlightRequest.setOrigin("ORG");
        busyFlightRequest.setDepartureDate(LocalDate.of(2018,01,01));
        busyFlightRequest.setReturnDate(LocalDate.of(2018,01,20));
    }

    private void initToughJetResponse(){
        toughJetResponse = new ToughJetResponse();
        toughJetResponse.setCarrier("Airline");
        toughJetResponse.setBasePrice(50);
        toughJetResponse.setTax(10);
        toughJetResponse.setDiscount(15);
        toughJetResponse.setDepartureAirportName("LAX");
        toughJetResponse.setArrivalAirportName("XAE");
        toughJetResponse.setOutboundDateTime(DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochMilli(1514764800)));
        toughJetResponse.setInboundDateTime(DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochMilli(1517443200)));
    }

    @Test
    public void toToughJetRequest_getOutboundDate(){
        ToughJetRequest result = ToughJetConverter.toToughJetRequest(busyFlightRequest);
        Assertions.assertThat(result.getOutboundDate()).isEqualTo("2018-01-01");
    }

    @Test
    public void toToughJetRequest_getInboundDate(){
        ToughJetRequest result = ToughJetConverter.toToughJetRequest(busyFlightRequest);
        Assertions.assertThat(result.getInboundDate()).isEqualTo("2018-01-20");
    }

    @Test
    public void toBusyFlightsResponse_getFare() throws Exception {
        BusyFlightsResponse result = ToughJetConverter.toBusyFlightsResponse(toughJetResponse);
        Assertions.assertThat(result.getFare()).isEqualTo("46.75");
    }
}