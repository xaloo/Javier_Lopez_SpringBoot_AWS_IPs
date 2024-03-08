package javier.lopez.demo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import javier.lopez.demo.entities.dto.Region;
import javier.lopez.demo.services.AWSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/aws")
public class AWSController {

    private final AWSService awsService;

    @Autowired
    public AWSController(AWSService awsService){
        this.awsService = awsService;
    }

    @GetMapping(value = "/ipRanges", produces = "text/plain")
    @Operation(
        summary = "List All filtered ipRanges by region",
        description = "List All filtered ipRanges by region. Default region ALL",
        responses = {
            @ApiResponse(
                description = "ipRanges list",
                responseCode = "200",
                content = @Content(
                        mediaType = "text/plain",
                        schema = @Schema(implementation = String.class)
                )
            )
        })
    public String getIpRanges(@RequestParam(defaultValue = "ALL") Region region){
        return awsService.getIpRangesText(region);
    }
}
