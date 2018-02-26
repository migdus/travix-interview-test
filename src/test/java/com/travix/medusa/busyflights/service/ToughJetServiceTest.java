package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetConverter;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
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
@PrepareForTest(ToughJetConverter.class)
public class ToughJetServiceTest {
    @Mock
    private RestTemplate restTemplate;
    private ToughJetRequest toughJetRequest;
    @Mock
    private ToughJetResponse toughJetResponse1;
    @Mock
    private ToughJetResponse toughJetResponse2;
    @Mock
    private BusyFlightsResponse busyFlightResponse1;
    @Mock
    private BusyFlightsResponse busyFlightResponse2;
    @Mock
    private BusyFlightsRequest busyFlightsRequest;
    private ToughJetService toughJetService;

    @Before
    public void before(){
        initToughJetRequest();
        initRestTemplate();
        initService();
        initToughJetConverter();
    }

    private void initToughJetRequest() {
        toughJetRequest = new ToughJetRequest();
        toughJetRequest.setFrom("ABS");
        toughJetRequest.setTo("RTS");
        toughJetRequest.setOutboundDate("2018-01-01");
        toughJetRequest.setInboundDate("2018-01-02");
        toughJetRequest.setNumberOfAdults(2);
    }

    private void initRestTemplate() {
        String url = "http://localhost:9090/toughjet/query?from=ABS&to=RTS&outboundDate=2018-01-01&inboundDate=2018-01-02&numberOfAdults=2";
        when(restTemplate.getForObject(url,ToughJetResponse[].class)).thenReturn(new ToughJetResponse[]{toughJetResponse1,toughJetResponse2});
    }

    private void initService() {
        toughJetService = new ToughJetService(restTemplate);
        Whitebox.setInternalState(toughJetService,"toughjetServer","http://localhost:9090");
    }

    private void initToughJetConverter() {
        PowerMockito.mockStatic(ToughJetConverter.class);
        when(ToughJetConverter.toToughJetRequest(busyFlightsRequest)).thenReturn(toughJetRequest);
        when(ToughJetConverter.toBusyFlightsResponse(toughJetResponse1)).thenReturn(busyFlightResponse1);
        when(ToughJetConverter.toBusyFlightsResponse(toughJetResponse2)).thenReturn(busyFlightResponse2);
    }

    @Test
    public void test(){
        List<BusyFlightsResponse> result = toughJetService.getFares(busyFlightsRequest);
        Assertions.assertThat(result).contains(busyFlightResponse1);
        Assertions.assertThat(result).contains(busyFlightResponse2);
    }

}