package br.com.kaua.mostratempo.dto;

import java.time.Instant;

public record LerTempoDTO(
        Long id,
        String nomeCidade,
        double temperatura,
        double sensacaoTermica,
        double temperaturaMinima,
        double temperaturaMaxima,
        double humidade,
        int pressao,
        Instant dataConsulta
) {
}
