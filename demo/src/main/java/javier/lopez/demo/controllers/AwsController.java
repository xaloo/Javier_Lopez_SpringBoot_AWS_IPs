package javier.lopez.demo.controllers;

import javier.lopez.demo.entities.dto.Region;
import javier.lopez.demo.services.AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/aws")
public class AwsController {

    private final AwsService awsService;

    @Autowired
    public AwsController(AwsService awsService){
        this.awsService = awsService;
    }

    @GetMapping(value = "/ipRanges", produces = "text/plain")
    public String getIpRanges(@RequestParam(defaultValue = "ALL") Region region){
        return awsService.getIpRangesText(region);
    }
}
