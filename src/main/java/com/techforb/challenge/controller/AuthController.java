package com.techforb.challenge.controller;

import com.techforb.challenge.dtos.AuthResponseDto;
import com.techforb.challenge.dtos.LoginDto;
import com.techforb.challenge.dtos.RegistroDto;
import com.techforb.challenge.dtos.response.ApiResponse;
import com.techforb.challenge.exceptions.ResourceAlreadyExistsException;
import com.techforb.challenge.models.Credencial;
import com.techforb.challenge.models.Usuario;
import com.techforb.challenge.respostories.ICredencialRepository;
import com.techforb.challenge.respostories.IUsuariosRepository;
import com.techforb.challenge.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    IUsuariosRepository usuariosRepository;

    @Autowired
    private ICredencialRepository credencialRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody RegistroDto registroDto) {
        if (credencialRepository.existsByEmail(registroDto.getEmail()))
            throw new ResourceAlreadyExistsException("El email ya está registrado");

        Usuario usuario = new Usuario();
        usuario.setNombre(registroDto.getNombre());
        usuario.setApellido(registroDto.getApellido());
        usuario.setAvatar(registroDto.getAvatar());

        Usuario usuarioSaved = usuariosRepository.save(usuario);

        Credencial credencial = new Credencial();
        credencial.setEmail(registroDto.getEmail());
        credencial.setPasswordHash(passwordEncoder.encode(registroDto.getPassword()));
        credencial.setUsuario(usuarioSaved);

        credencialRepository.save(credencial);

        return ResponseEntity.ok(new ApiResponse<>(true,"Usuario registrado exitosamente"));
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDto>> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);

        AuthResponseDto authResponse = new AuthResponseDto(token);
        return ResponseEntity.ok(new ApiResponse<>(true,authResponse,"Login exitoso"));

    }

}