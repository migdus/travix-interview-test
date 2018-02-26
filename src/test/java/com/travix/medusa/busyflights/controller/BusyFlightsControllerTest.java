package com.travix.medusa.busyflights.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.BusyFlightService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BusyFlightsController.class, secure = false)
public class BusyFlightsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BusyFlightService busyFlightService;
    private List<BusyFlightsResponse> busyFlightsResponse;
    private final String API_REQUEST="/busyflights/query?origin=ABC&destination=DEF&departureDate=2011-12-03&returnDate=2011-12-03&numberOfPassengers=4";

    @Before
    public void before(){
        initBusyFlightsResponses();
        Mockito.when(busyFlightService.queryFlights(any())).thenReturn(busyFlightsResponse);
    }

    private void initBusyFlightsResponses() {
        busyFlightsResponse=new ArrayList<>();
        BusyFlightsResponse response = new BusyFlightsResponse();
        response.setAirline("Airline 1");
        response.setSupplier("Supplier Company");
        response.setFare("45.23");
        response.setDestinationAirportCode("ERT");
        response.setDestinationAirportCode("YUB");
        response.setDepartureDate("2018-01-01");
        response.setArrivalDate("2018-01-20");
        busyFlightsResponse.add(response);
        response = new BusyFlightsResponse();
        response.setAirline("Airline 2");
        response.setSupplier("Supplier Company2");
        response.setFare("25.98");
        response.setDestinationAirportCode("TRE");
        response.setDestinationAirportCode("BYU");
        response.setDepartureDate("2018-02-01");
        response.setArrivalDate("2018-02-20");
        busyFlightsResponse.add(response);
    }

    @Test
    public void query() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(API_REQUEST);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(
                        content().string(new ObjectMapper().writeValueAsString(busyFlightsResponse)));
    }
}