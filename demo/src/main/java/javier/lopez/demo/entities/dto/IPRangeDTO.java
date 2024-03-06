package javier.lopez.demo.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record IPRangeDTO (@JsonProperty("ip_prefix") String ipPrefix,
                          @JsonProperty("region") String region,
                          @JsonProperty("service") String service,
                          @JsonProperty("network_border_group") String networkBorderGroup){
}
