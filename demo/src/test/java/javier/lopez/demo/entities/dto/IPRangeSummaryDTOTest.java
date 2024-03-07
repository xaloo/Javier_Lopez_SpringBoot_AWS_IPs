package javier.lopez.demo.entities.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class IPRangeSummaryDTOTest {

    @Test
    public void createIPRangeSummaryDTOTest() {
        IPRangeSummaryDTO summary = new IPRangeSummaryDTO(
                "token",
                "2022-01-01",
                List.of(new IPRangeDTO("192.168.1.1", "us-east-1", "AMAZON", "us-east-1"),
                new IPRangeDTO("192.168.1.2", "us-west-1", "AMAZON", "us-west-1")));
        assertEquals("token", summary.syncToken());
        assertEquals("2022-01-01", summary.createDate());
        assertEquals(2, summary.prefixes().size());
        assertEquals("us-east-1", summary.prefixes().get(0).region());
        assertEquals("us-west-1", summary.prefixes().get(1).region());
    }
}
