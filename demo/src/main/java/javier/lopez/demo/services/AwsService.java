package javier.lopez.demo.services;

import javier.lopez.demo.entities.dto.Region;

public interface AwsService {
    String getIpRangesText(Region region);
}
