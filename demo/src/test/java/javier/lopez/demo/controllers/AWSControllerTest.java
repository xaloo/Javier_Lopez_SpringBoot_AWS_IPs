package javier.lopez.demo.controllers;

import javier.lopez.demo.entities.dto.Region;
import javier.lopez.demo.services.AWSService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AWSControllerTest {

    @Mock
    private AWSService AWSService;

    @InjectMocks
    private AWSController AWSController;

    private MockMvc mockMvc;

    private static final String AWS_IP_RANGE_ENDPOINT = "/aws/ipRanges";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(AWSController).build();
    }

    @Test
    void getIpRangesAllTest() throws Exception {
        String response = "192.168.1.1\n192.168.1.2";
        when(AWSService.getIpRangesText(Region.ALL)).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.get("/aws/ipRanges")
                        .param("region", Region.ALL.name()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(response));
    }


    @Test
    void invalidRegionParameterTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(AWS_IP_RANGE_ENDPOINT)
                        .param("region", "invalidParam"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
