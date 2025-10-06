package br.com.nexum.nexum_api.security.controller;

import br.com.nexum.nexum_api.domain.Usuario;
import br.com.nexum.nexum_api.security.controller.request.LoginRequest;
import br.com.nexum.nexum_api.security.controller.response.LoginResponse;
import br.com.nexum.nexum_api.service.BuscarUsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final BuscarUsuarioService buscarUsuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    public LoginController(BuscarUsuarioService buscarUsuarioService, PasswordEncoder passwordEncoder, JwtEncoder jwtEncoder) {
        this.buscarUsuarioService = buscarUsuarioService;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = buscarUsuarioService.buscarPorEmail(loginRequest.getEmail());

        if (!isLoginCorreto(loginRequest.getSenha(), usuario.getSenha())) {
            throw new BadCredentialsException("Usuário ou senha incorretos!");
        }

        long expiresIn = 60000L;

        JwtClaimsSet jwt = JwtClaimsSet.builder()
                .issuer("seguranca-api")
                .subject(usuario.getNome())
                .expiresAt(Instant.now().plusSeconds(expiresIn))
                .issuedAt(Instant.now())
                .claim("email", usuario.getEmail())
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(jwt)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(token, expiresIn));
    }

    private boolean isLoginCorreto(String password, String savedPassowrd) {
        return passwordEncoder.matches(password, savedPassowrd);
    }
}
