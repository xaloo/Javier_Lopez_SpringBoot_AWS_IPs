package javier.lopez.demo.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public record IPRangeDTO (@JsonProperty("ip_prefix") String ipPrefix,
                          @JsonProperty("region") String region,
                          @JsonProperty("service") String service,
                          @JsonProperty("network_border_group") String networkBorderGroup) {
    private static final Logger logger = LoggerFactory.getLogger(IPRangeDTO.class);

    private static final String REGION_SEPARATOR = "-";
    public Region getEnumRegion() {
        if (region.equals(Region.GLOBAL.name())) {
            return Region.GLOBAL;
        }
        try {
            String[] regionSplit = region.split(REGION_SEPARATOR);
            return Region.fromString(regionSplit[0]);
        } catch (Exception e) {
            logger.warn(String.format("Could not find region for enum %s", region));
            return Region.NOT_FOUND;
        }
    }
}
