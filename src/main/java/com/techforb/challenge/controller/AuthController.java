package com.techforb.challenge.controller;

import com.techforb.challenge.dtos.AuthResponsoDto;
import com.techforb.challenge.dtos.LoginDto;
import com.techforb.challenge.dtos.RegistroDto;
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

@Controller
@RequestMapping("/api/auth/")
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

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegistroDto registroDto) {
        if (credencialRepository.existsByEmail(registroDto.getEmail()))
            return new ResponseEntity<>("Email existente", HttpStatus.BAD_REQUEST);
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
        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.OK);
    }


    @PostMapping("login")
    public ResponseEntity<AuthResponsoDto> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);

        return new ResponseEntity<>(new AuthResponsoDto(token), HttpStatus.OK);
    }

}