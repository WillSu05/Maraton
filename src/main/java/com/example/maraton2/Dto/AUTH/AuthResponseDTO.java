package com.example.maraton2.Dto.AUTH;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDTO {
    private String token;
    private String type = "Bearer";
    private String email;
    private String nombre;
}