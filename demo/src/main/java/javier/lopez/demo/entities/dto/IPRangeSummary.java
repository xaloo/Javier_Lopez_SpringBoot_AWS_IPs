package javier.lopez.demo.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record IPRangeSummary(
        @JsonProperty("syncToken") String syncToken,
        @JsonProperty("createDate") String createDate,
        @JsonProperty("prefixes") List<IPRangeDTO> prefixes) {
}
