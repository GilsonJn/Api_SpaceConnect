package br.com.g3.spaceconnect.domain.dispositivo;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroDispositivo(
        @NotBlank String tipo,
        @NotBlank String nome,
        @NotBlank String localizacao,
        String orbita,
        String culturaMonitorada
) {
}