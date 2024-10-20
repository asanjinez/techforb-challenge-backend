package com.techforb.challenge.security;

import com.techforb.challenge.models.Credencial;
import com.techforb.challenge.respostories.ICredencialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    ICredencialRepository credencialRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Credencial credencial = credencialRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuario no registrado"));
//  TODO No le paso authorities ya que en este caso solo habra un "rol" sin emabargo estaria bueno hacerlo
        Collection<GrantedAuthority> authorities = new ArrayList<>(); // No hay roles por ahora
        return new User(credencial.getEmail(),credencial.getPasswordHash(),authorities);
    }
}
