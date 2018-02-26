package com.travix.medusa.busyflights.domain.crazyair;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class CrazyAirConverterTest {

    private CrazyAirResponse crazyAirResponse;
    private BusyFlightsRequest busyFlightsRequest;

    @Before
    public void before(){
        initCrazyAirResponse();
        initBusyFlightsRequest();
    }

    private void initBusyFlightsRequest() {
        busyFlightsRequest = new BusyFlightsRequest();
        busyFlightsRequest.setNumberOfPassengers(3);
        busyFlightsRequest.setDestination("AER");
        busyFlightsRequest.setOrigin("ORG");
        busyFlightsRequest.setDepartureDate(LocalDate.of(2018,01,01));
        busyFlightsRequest.setReturnDate(LocalDate.of(2018,01,20));
    }

    private void initCrazyAirResponse() {
        crazyAirResponse=new CrazyAirResponse();
        crazyAirResponse.setPrice(25.35);
        crazyAirResponse.setArrivalDate("2011-12-03T10:15:30");
        crazyAirResponse.setDepartureDate("2011-12-03T10:15:30");
    }

    @Test
    public void toBusyFlightsResponse_getFare(){
        BusyFlightsResponse result = CrazyAirConverter.toBusyFlightsResponse(crazyAirResponse);
        Assertions.assertThat(result.getFare()).isEqualTo("25.35");
    }

    @Test
    public void toCrazyAirRequest_getDepartureDate(){
        CrazyAirRequest result = CrazyAirConverter.toCrazyAirRequest(busyFlightsRequest);
        Assertions.assertThat(result.getDepartureDate()).isEqualTo("2018-01-01");
    }

    @Test
    public void toCrazyAirRequest_getReturnDate(){
        CrazyAirRequest result = CrazyAirConverter.toCrazyAirRequest(busyFlightsRequest);
        Assertions.assertThat(result.getReturnDate()).isEqualTo("2018-01-20");
    }
}