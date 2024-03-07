package javier.lopez.demo.services;

import javier.lopez.demo.config.CustomWebClient;
import javier.lopez.demo.entities.dto.IPRangeDTO;
import javier.lopez.demo.entities.dto.IPRangeSummaryDTO;
import javier.lopez.demo.entities.dto.Region;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AWSServiceImplTest {

    @Mock
    private CustomWebClient customWebClient;

    @InjectMocks
    private AWSServiceImpl AWSService;

    private IPRangeSummaryDTO mockSummary;


    @BeforeEach
    void config() {
        IPRangeDTO ipRange1 = new IPRangeDTO("192.168.1.1", "us-east-1", "AMAZON", "us-east-1");
        IPRangeDTO ipRange2 = new IPRangeDTO("192.168.1.2", "us-west-2", "AMAZON", "us-west-2");
        mockSummary = new IPRangeSummaryDTO("token123", "2022-01-01", List.of(ipRange1, ipRange2));
    }

    @Test
    public void getIpRangesTextTest() {
        ReflectionTestUtils.setField(customWebClient, "webClientBufferSizeBytes", 2048);
        ReflectionTestUtils.setField(AWSService, "awsIpRangesUrl", "https://example.com/aws-ip-ranges");

        WebClient webClientMock = mock(WebClient.class);
        WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock = mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec requestHeadersSpecMock = mock(WebClient.RequestHeadersSpec.class);
        WebClient.ResponseSpec responseSpecMock = mock(WebClient.ResponseSpec.class);

        when(customWebClient.getWebClient()).thenReturn(webClientMock);
        when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(IPRangeSummaryDTO.class)).thenReturn(Mono.just(mockSummary));
        String result = AWSService.getIpRangesText(Region.US);
        assertEquals("192.168.1.1\n192.168.1.2", result);
    }

}