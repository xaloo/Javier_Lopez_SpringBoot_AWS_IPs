package javier.lopez.demo.services;

import javier.lopez.demo.config.CustomWebClient;
import javier.lopez.demo.entities.dto.IPRangeDTO;
import javier.lopez.demo.entities.dto.IPRangeSummaryDTO;
import javier.lopez.demo.entities.dto.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.stream.Collectors;

@Service
public class AwsServiceImpl implements AwsService {

    @Value("${aws.ip.ranges.url}")
    private String awsIpRangesUrl;

    private final CustomWebClient customWebClient;

    public AwsServiceImpl (CustomWebClient customWebClient){
        this.customWebClient = customWebClient;
    }

    @Override
    public String getIpRangesText(Region region) {
        IPRangeSummaryDTO summary = getIpRangesSummary();
        return summary.prefixes().stream()
                .filter(ipRange -> region == Region.ALL || region == ipRange.getEnumRegion())
                .map(IPRangeDTO::ipPrefix)
                .collect(Collectors.joining("\n"));
    }

    private IPRangeSummaryDTO getIpRangesSummary() {
        WebClient webClient = customWebClient.getWebClient();
        return webClient.get()
                .uri(awsIpRangesUrl)
                .retrieve()
                .bodyToMono(IPRangeSummaryDTO.class)
                .block();
    }
}
