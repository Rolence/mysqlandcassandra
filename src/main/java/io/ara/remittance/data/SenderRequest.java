package io.ara.remittance.data;

import lombok.Data;

@Data
public class SenderRequest {
    private String country;

    private Boolean isActive;
}
