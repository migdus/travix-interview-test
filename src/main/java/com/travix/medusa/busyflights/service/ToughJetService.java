package com.travix.medusa.busyflights.service;


import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetConverter;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToughJetService implements SupplierService{
    @Value("${toughjet.server}")
    private String toughjetServer;

    public final static String CONTROLLER_NAME = "toughjet";
    public final static String API_QUERY = "query";

    private RestTemplate restTemplate;

    @Autowired
    public ToughJetService(RestTemplate restTemplate){this.restTemplate = restTemplate;}

    @Override
    public List<BusyFlightsResponse> getFares(BusyFlightsRequest request) {
        return getFares(ToughJetConverter.toToughJetRequest(request)).stream()
                .map(ToughJetConverter::toBusyFlightsResponse).collect(Collectors.toList());
    }

    public List<ToughJetResponse> getFares(ToughJetRequest toughJetRequest) {
        return Arrays.asList(restTemplate.getForObject(buildUri(toughJetRequest), ToughJetResponse[].class));
    }

    private String buildUri(ToughJetRequest toughJetRequest){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(toughjetServer);
        builder.pathSegment(CONTROLLER_NAME);
        builder.pathSegment(API_QUERY);
        builder.queryParam("from",toughJetRequest.getFrom());
        builder.queryParam("to",toughJetRequest.getTo());
        builder.queryParam("outboundDate",toughJetRequest.getOutboundDate());
        builder.queryParam("inboundDate",toughJetRequest.getInboundDate());
        builder.queryParam("numberOfAdults",toughJetRequest.getNumberOfAdults());
        return builder.toUriString();
    }
}
