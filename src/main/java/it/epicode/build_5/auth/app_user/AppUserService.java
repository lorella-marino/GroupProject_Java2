package it.epicode.build_5.auth.app_user;
import it.epicode.build_5.auth.jwt.JwtTokenUtil;
import it.epicode.build_5.cloudinary.CloudinaryService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.Set;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CloudinaryService cloudinaryService;

    public void uploadFotoProfilo(Long id, MultipartFile fotoProfilo) {
        AppUser dipendente = appUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
        String url = cloudinaryService.uploadImage(fotoProfilo);
        dipendente.setAvatar(url);
        appUserRepository.save(dipendente);
    }

    public AppUser registerDefault(String username, String password, Set<Role> roles) {
        if (appUserRepository.existsByUsername(username)) {
            throw new EntityExistsException("Username già in uso");
        }

        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(passwordEncoder.encode(password));
        appUser.setRoles(roles);
        return appUserRepository.save(appUser);
    }

    public AppUser registerUser(String username, String password,  String email, String nome, String cognome , Set<Role> roles) {
        if (appUserRepository.existsByUsername(username)) {
            throw new EntityExistsException("Username già in uso");
        }

        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setPassword(passwordEncoder.encode(password));
        appUser.setRoles(roles);
        appUser.setEmail(email);
        appUser.setNome(nome);
        appUser.setCognome(cognome);
        return appUserRepository.save(appUser);
    }

    public Optional<AppUser> findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public String authenticateUser(String username, String password)  {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            throw new SecurityException("Credenziali non valide", e);
        }
    }


    public AppUser loadUserByUsername(String username)  {
        AppUser appUser = appUserRepository.findByUsername(username)
            .orElseThrow(() -> new EntityNotFoundException("Utente non trovato con username: " + username));


        return appUser;
    }
}
