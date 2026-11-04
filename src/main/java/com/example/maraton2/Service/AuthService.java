package com.example.maraton2.Service;

import com.example.maraton2.Dto.AUTH.AuthResponseDTO;
import com.example.maraton2.Dto.AUTH.LoginRequestDTO;
import com.example.maraton2.Dto.AUTH.RegistroRequestDTO;

public interface AuthService {

    AuthResponseDTO registrar(RegistroRequestDTO dto);

    AuthResponseDTO login(LoginRequestDTO dto);
}
