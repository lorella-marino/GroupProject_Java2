package it.epicode.build_5.auth.authorization;

import it.epicode.build_5.auth.app_user.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String email;
}
