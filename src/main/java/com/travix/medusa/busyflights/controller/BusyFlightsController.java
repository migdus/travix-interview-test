package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.BusyFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(BusyFlightsController.BUSYFLIGHTS)
public class BusyFlightsController {
    public static final String BUSYFLIGHTS = "busyflights";
    public static final String QUERY = "query";

    private BusyFlightService service;

    @Autowired
    public BusyFlightsController(BusyFlightService service){this.service = service;}

    @GetMapping(QUERY)
    public List<BusyFlightsResponse> queryFlights(@Valid @ModelAttribute BusyFlightsRequest request){
            return service.queryFlights(request);
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected List<ErrorDetail> validationError(BindingResult results){
        return results.getAllErrors().stream()
                .map(FieldError.class::cast)
                .map(ErrorDetail::new).collect(Collectors.toList());
    }

    private class ErrorDetail {
        private String field;
        private String rejectedValue;
        private String defaultMessage;

        public ErrorDetail(FieldError field){
            this.field = field.getField();
            this.rejectedValue = (String)field.getRejectedValue();
            this.defaultMessage = field.getDefaultMessage();
        }

        public String getField() {
            return field;
        }

        public String getRejectedValue() {
            return rejectedValue;
        }

        public String getDefaultMessage() {
            return defaultMessage;
        }
    }
}
