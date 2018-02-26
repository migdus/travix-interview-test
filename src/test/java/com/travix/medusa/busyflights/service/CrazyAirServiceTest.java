package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirConverter;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CrazyAirConverter.class)
public class CrazyAirServiceTest {
    private CrazyAirService crazyAirService;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private CrazyAirResponse crazyAirResponse1;
    @Mock
    private CrazyAirResponse crazyAirResponse2;
    @Mock
    private BusyFlightsResponse busyFlightResponse1;
    @Mock
    private BusyFlightsResponse busyFlightResponse2;
    @Mock
    private BusyFlightsRequest busyFlightsRequest;

    private CrazyAirRequest crazyAirRequest;

    @Before
    public void before(){
        initCrazyAirRequest();
        initRestTemplate();
        initService();
        initCrazyAirConverter();
    }

    private void initCrazyAirRequest() {
        crazyAirRequest = new CrazyAirRequest();
        crazyAirRequest.setOrigin("ABS");
        crazyAirRequest.setDestination("RTS");
        crazyAirRequest.setDepartureDate("2018-01-01");
        crazyAirRequest.setReturnDate("2018-01-02");
        crazyAirRequest.setPassengerCount(2);
    }

    private void initRestTemplate() {
        String url = "http://localhost:9000/crazyair/query?origin=ABS&destination=RTS&departureDate=2018-01-01&returnDate=2018-01-02&passengerCount=2";
        when(restTemplate.getForObject(url,CrazyAirResponse[].class)).thenReturn(new CrazyAirResponse[]{crazyAirResponse1,crazyAirResponse2});
    }

    private void initService() {
        crazyAirService = new CrazyAirService(restTemplate);
        Whitebox.setInternalState(crazyAirService,"crazyAirServer","http://localhost:9000");
    }

    private void initCrazyAirConverter() {
        PowerMockito.mockStatic(CrazyAirConverter.class);
        when(CrazyAirConverter.toCrazyAirRequest(busyFlightsRequest)).thenReturn(crazyAirRequest);
        when(CrazyAirConverter.toBusyFlightsResponse(crazyAirResponse1)).thenReturn(busyFlightResponse1);
        when(CrazyAirConverter.toBusyFlightsResponse(crazyAirResponse2)).thenReturn(busyFlightResponse2);
    }

    @Test
    public void test(){
        List<BusyFlightsResponse> result = crazyAirService.getFares(busyFlightsRequest);
        Assertions.assertThat(result).contains(busyFlightResponse1);
        Assertions.assertThat(result).contains(busyFlightResponse2);
    }
}