package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.service.ToughJetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

//Test controller with mock data
@RestController
@RequestMapping(ToughJetService.CONTROLLER_NAME)
public class ToughJetController {

    @GetMapping(ToughJetService.API_QUERY)
    public List<ToughJetResponse> queryFlights(@ModelAttribute ToughJetRequest toughJetRequest){
        List<ToughJetResponse> responses = new ArrayList<>();
        ToughJetResponse c = new ToughJetResponse();
        c.setCarrier("QuickNDirty Airlines");
        c.setBasePrice(420);
        c.setTax(12);
        c.setDiscount(5);
        c.setDepartureAirportName("MNO");
        c.setArrivalAirportName("PQR");
        c.setOutboundDateTime(DateTimeFormatter.ISO_INSTANT.format(Instant.now()));
        c.setInboundDateTime(DateTimeFormatter.ISO_INSTANT.format(Instant.now().plus(10, ChronoUnit.DAYS)));
        responses.add(c);
        c = new ToughJetResponse();
        c.setCarrier("A1 Airlines");
        c.setBasePrice(50);
        c.setTax(45);
        c.setDiscount(10);
        c.setDepartureAirportName("SRT");
        c.setArrivalAirportName("XFG");
        c.setOutboundDateTime(DateTimeFormatter.ISO_INSTANT.format(Instant.now()));
        c.setInboundDateTime(DateTimeFormatter.ISO_INSTANT.format(Instant.now().plus(2, ChronoUnit.DAYS)));
        responses.add(c);
        return responses;
    }
}
