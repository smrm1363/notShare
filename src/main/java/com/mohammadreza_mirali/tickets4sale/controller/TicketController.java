package com.mohammadreza_mirali.tickets4sale.controller;

import com.mohammadreza_mirali.tickets4sale.controller.dto.OutputDtoMaker;
import com.mohammadreza_mirali.tickets4sale.controller.dto.ResultOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * This is the REST controller for calling the backend from the frontend
 */
@RestController
public class TicketController {

    private final OutputDtoMaker outputDtoMaker;
    @Autowired
    public TicketController(OutputDtoMaker outputDtoMaker) {
        this.outputDtoMaker = outputDtoMaker;
    }

    @RequestMapping(value = "/findSellStatistic", method = RequestMethod.GET)
    public List<ResultOutputDto> findSellStatistic(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate showDate) throws IOException
    {
        /**
         *  Because we use default CSV file path, and query date in web application, I put null, and the OutputDtoMaker handles the situation
         */
         return outputDtoMaker.makeOutputDto(null,null,showDate);

    }
}
