package com.melek.gestionstock.service.auth;

import com.melek.gestionstock.exception.EntityNotFoundException;
import com.melek.gestionstock.exception.ErrorCodes;
import com.melek.gestionstock.model.Utilisateur;
import com.melek.gestionstock.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Aucun utilisateur avec l'email fourni !", ErrorCodes.UTILISATEUR_NOT_FOUND));

        return new User(utilisateur.getEmail(), utilisateur.getPassword(), Collections.emptyList()); // String username, String password, Collection<? extends GrantedAuthority> authorities
    }
}
