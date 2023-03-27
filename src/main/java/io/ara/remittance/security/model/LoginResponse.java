package io.ara.remittance.security.model;

import lombok.Data;

import java.util.List;

@Data
public class LoginResponse {
    private List<String> role;
    private List<String> privilege;
    private String token;
    private String status;
    private Long merchant;
}
