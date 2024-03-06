package javier.lopez.demo.controllers;

import javier.lopez.demo.entities.dto.IPRangeSummary;
import javier.lopez.demo.services.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/aws")
public class AwsController{

    private final AwsService awsService;

    @Autowired
    public AwsController(AwsService awsService){
        this.awsService = awsService;
    }

    @GetMapping
    public IPRangeSummary getIpRanges(){
        IPRangeSummary summary = awsService.getIpRanges();
        return summary;
    }
}
