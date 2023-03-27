package io.ara.remittance.data;

import lombok.Data;

@Data
public class SenderResponse {
    private String username;
    private String password;
    private String email;
    private String country;

    private Boolean isActive;
}
