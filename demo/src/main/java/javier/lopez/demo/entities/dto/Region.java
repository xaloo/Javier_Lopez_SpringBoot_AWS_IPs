package javier.lopez.demo.entities.dto;

import io.micrometer.common.util.StringUtils;

public enum Region {
    ALL,
    GLOBAL,
    NOT_FOUND,
    EU,
    US,
    AP,
    CN,
    SA,
    AF,
    CA,
    ME,
    IL,
    EUSC;

    public static Region fromString(String value) {
        if (StringUtils.isBlank(value))
            return null;
        return Region.valueOf(value.toUpperCase());
    }
}

