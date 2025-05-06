package it.epicode.build_5.auth.authorization;

import it.epicode.build_5.auth.app_user.AppUser;
import it.epicode.build_5.auth.app_user.AppUserService;
import it.epicode.build_5.auth.app_user.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AppUserService appUserService;

    @PatchMapping(path = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadLogoAziendale(@RequestParam Long id, @RequestParam MultipartFile file) {
        appUserService.uploadFotoProfilo(id, file);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/current-user")
    public AppUser getCurrentUser(@AuthenticationPrincipal AppUser appUser) {
        return appUser;
    }

    /*@PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody RegisterRequest registerRequest) {
        appUserService.registerAdmin(
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                Set.of( Role.ROLE_ADMIN)
        );
        return ResponseEntity.ok("Registrazione avvenuta con successo");
    }*/

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest ) {
        appUserService.registerUser(
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                registerRequest.getEmail(),
                registerRequest.getNome(),
                registerRequest.getCognome(),
                Set.of( Role.ROLE_USER)
        );
        return ResponseEntity.ok("Registrazione avvenuta con successo");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        log.info("Login request:");
        String token = appUserService.authenticateUser(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
