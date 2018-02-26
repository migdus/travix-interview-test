package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirConverter;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrazyAirService implements SupplierService {

    @Value("${crazyair.server}")
    private String crazyAirServer;

    public final static String CONTROLLER_NAME = "crazyair";
    public final static String API_QUERY = "query";

    private RestTemplate restTemplate;

    @Autowired
    public CrazyAirService(RestTemplate restTemplate){this.restTemplate = restTemplate;}

    @Override
    public List<BusyFlightsResponse> getFares(BusyFlightsRequest request) {
        return getFares(CrazyAirConverter.toCrazyAirRequest(request)).stream()
                .map(CrazyAirConverter::toBusyFlightsResponse).collect(Collectors.toList());
    }

    public List<CrazyAirResponse> getFares(CrazyAirRequest crazyAirRequest) {
        return Arrays.asList(restTemplate.getForObject(buildUri(crazyAirRequest), CrazyAirResponse[].class));
    }

    private String buildUri(CrazyAirRequest crazyAirRequest){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(crazyAirServer);
        builder.pathSegment(CONTROLLER_NAME);
        builder.pathSegment(API_QUERY);
        builder.queryParam("origin",crazyAirRequest.getOrigin());
        builder.queryParam("destination",crazyAirRequest.getDestination());
        builder.queryParam("departureDate",crazyAirRequest.getDepartureDate());
        builder.queryParam("returnDate",crazyAirRequest.getReturnDate());
        builder.queryParam("passengerCount",crazyAirRequest.getPassengerCount());
        return builder.toUriString();
    }
}
