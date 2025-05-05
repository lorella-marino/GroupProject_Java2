package it.epicode.build_5.auth.authorization;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
