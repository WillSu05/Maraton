package com.example.maraton2.Service.IMPL;

import com.example.maraton2.Dto.AUTH.AuthResponseDTO;
import com.example.maraton2.Dto.AUTH.LoginRequestDTO;
import com.example.maraton2.Dto.AUTH.RegistroRequestDTO;
import com.example.maraton2.Entity.Usuario;
import com.example.maraton2.Repository.UsuarioRepository;
import com.example.maraton2.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponseDTO registrar(RegistroRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("El email ya se encuentra registrado");
        }

        Usuario usuario = Usuario.builder()
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .rol(Usuario.Rol.ROLE_USER)
                .build();

        usuarioRepository.save(usuario);


        String token = "TOKEN_JWT_GENERADO";

        return AuthResponseDTO.builder()
                .token(token)
                .type("Bearer")
                .email(usuario.getEmail())
                .nombre(usuario.getNombre())
                .build();
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Credenciales inválidas"));

        if (!passwordEncoder.matches(dto.getPassword(), usuario.getPassword())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }

        String token = "TOKEN_JWT_GENERADO"; // jwtTokenProvider.generateToken(usuario.getEmail());

        return AuthResponseDTO.builder()
                .token(token)
                .type("Bearer")
                .email(usuario.getEmail())
                .nombre(usuario.getNombre())
                .build();
    }
}
