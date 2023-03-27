package io.ara.remittance.security.model;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String password;
    private String role;
    private String email;
    private Long merchant;
}
