package javier.lopez.demo.entities.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class IPRangeDTOTest {

    private static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of(new IPRangeDTO("192.168.1.1", "GLOBAL", "AMAZON", "global"), Region.GLOBAL),
                Arguments.of(new IPRangeDTO("192.168.1.1", "invalid-region", "AMAZON", "invalid-region"), Region.NOT_FOUND),
                Arguments.of(new IPRangeDTO("192.168.1.1", "us-west-1", "AMAZON", "us-west-1"), Region.US),
                Arguments.of(new IPRangeDTO("192.168.1.1", "eu-west-1", "AMAZON", "eu-west-1"), Region.EU),
                Arguments.of(new IPRangeDTO("192.168.1.1", "ap-southeast-1", "AMAZON", "ap-southeast-1"), Region.AP),
                Arguments.of(new IPRangeDTO("192.168.1.1", "cn-north-1", "AMAZON", "cn-north-1"), Region.CN),
                Arguments.of(new IPRangeDTO("192.168.1.1", "sa-east-1", "AMAZON", "sa-east-1"), Region.SA),
                Arguments.of(new IPRangeDTO("192.168.1.1", "af-south-1", "AMAZON", "af-south-1"), Region.AF),
                Arguments.of(new IPRangeDTO("192.168.1.1", "ca-central-1", "AMAZON", "ca-central-1"), Region.CA),
                Arguments.of(new IPRangeDTO("192.168.1.1", "me-south-1", "AMAZON", "me-south-1"), Region.ME),
                Arguments.of(new IPRangeDTO("192.168.1.1", "il-central-1", "AMAZON", "il-central-1"), Region.IL),
                Arguments.of(new IPRangeDTO("192.168.1.1", "eusc-east-1", "AMAZON", "us-gov-east-1"), Region.EUSC)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    public void getEnumRegionGlobalTest(IPRangeDTO ipRangeDTO, Region expectedRegion) {
        Region result = ipRangeDTO.getEnumRegion();
        assertEquals(expectedRegion, result);
    }

    @Test
    public void createIPRangeDTOTest() {
        IPRangeDTO ipRangeDTO = new IPRangeDTO("192.168.1.1", "us-east-1", "AMAZON", "us-east-1");
        assertEquals("192.168.1.1", ipRangeDTO.ipPrefix());
        assertEquals("us-east-1", ipRangeDTO.region());
        assertEquals("AMAZON", ipRangeDTO.service());
        assertEquals("us-east-1", ipRangeDTO.networkBorderGroup());
    }

}
