package javier.lopez.demo.services;

import javier.lopez.demo.config.CustomWebClient;
import javier.lopez.demo.entities.dto.IPRangeSummary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AwsServiceImpl implements AwsService {

    @Value("${aws.ip.ranges.url}")
    String awsIpRangesUrl;

    private final CustomWebClient customWebClient;

    public AwsServiceImpl (CustomWebClient customWebClient){
        this.customWebClient = customWebClient;
    }

    @Override
    public IPRangeSummary getIpRanges() {
        WebClient webClient = customWebClient.getWebClient();
        return webClient.get()
                .uri(awsIpRangesUrl)
                .retrieve()
                .bodyToMono(IPRangeSummary.class)
                .block();
    }
}
