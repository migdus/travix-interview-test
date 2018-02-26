package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusyFlightService {

    private List<SupplierService> supplierServices;

    @Autowired
    public BusyFlightService(List<SupplierService> suppliers){
        this.supplierServices = suppliers;
    }

    public List<BusyFlightsResponse> queryFlights(BusyFlightsRequest request) {
        return supplierServices.stream()
                .map(supplierService -> supplierService.getFares(request))
                .flatMap(List::stream)
                .sorted(Comparator.comparing((BusyFlightsResponse x)-> Double.valueOf(x.getFare()))).collect(Collectors.toList());
    }
}
