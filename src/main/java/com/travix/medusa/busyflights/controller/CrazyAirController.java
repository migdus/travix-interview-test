package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.service.CrazyAirService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//Test controller with mock data
@RestController
@RequestMapping(CrazyAirService.CONTROLLER_NAME)
public class CrazyAirController {

    @GetMapping(CrazyAirService.API_QUERY)
    public List<CrazyAirResponse> queryFlights(@ModelAttribute CrazyAirRequest crazyAirRequest){
        List<CrazyAirResponse> responses = new ArrayList<>();
        CrazyAirResponse c = new CrazyAirResponse();
        c.setArrivalDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        c.setDepartureDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        c.setDestinationAirportCode("ABC");
        c.setDepartureAirportCode("DEF");
        c.setAirline("CheapAirlines");
        c.setCabinclass("E");
        c.setPrice(450);
        responses.add(c);
        c = new CrazyAirResponse();
        c.setArrivalDate(LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        c.setDepartureDate(LocalDateTime.now().plusDays(2).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        c.setDestinationAirportCode("GHI");
        c.setDepartureAirportCode("JKL");
        c.setAirline("ClassyAirlines");
        c.setCabinclass("B");
        c.setPrice(70);
        responses.add(c);
        return responses;
    }
}
