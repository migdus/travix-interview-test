package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class BusyFlightServiceTest {

    private List<SupplierService> supplierServices;
    private BusyFlightService busyFlightService;

    @Before
    public void before(){
        initSupplierServices();
        busyFlightService = new BusyFlightService(supplierServices);
    }

    private void initSupplierServices(){
        supplierServices = new ArrayList<>();
        supplierServices.add(new CrazyAirMockSupplierService());
        supplierServices.add(new ToughJetMockSupplierService());
    }

    @Test
    public void queryFlightsAreOrdered(){
        List<BusyFlightsResponse> result = busyFlightService.queryFlights(new BusyFlightsRequest());
        Assertions.assertThat(Integer.valueOf(result.get(0).getFare())).isEqualTo(10);
        Assertions.assertThat(Integer.valueOf(result.get(1).getFare())).isEqualTo(40);
        Assertions.assertThat(Integer.valueOf(result.get(2).getFare())).isEqualTo(70);
        Assertions.assertThat(Integer.valueOf(result.get(3).getFare())).isEqualTo(200);
    }

    private class CrazyAirMockSupplierService implements SupplierService{

        @Override
        public List<BusyFlightsResponse> getFares(BusyFlightsRequest request) {
            List<BusyFlightsResponse> fares= new ArrayList<>();
            BusyFlightsResponse r = new BusyFlightsResponse();
            r.setFare("70");
            fares.add(r);
            r = new BusyFlightsResponse();
            r.setFare("40");
            fares.add(r);
            return fares;
        }
    }

    private class ToughJetMockSupplierService implements SupplierService{

        @Override
        public List<BusyFlightsResponse> getFares(BusyFlightsRequest request) {
            List<BusyFlightsResponse> fares= new ArrayList<>();
            BusyFlightsResponse r = new BusyFlightsResponse();
            r.setFare("200");
            fares.add(r);
            r = new BusyFlightsResponse();
            r.setFare("10");
            fares.add(r);
            return fares;
        }
    }
}