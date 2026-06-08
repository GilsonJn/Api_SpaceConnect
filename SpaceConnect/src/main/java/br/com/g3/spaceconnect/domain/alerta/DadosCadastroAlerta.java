package br.com.g3.spaceconnect.domain.alerta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroAlerta(
        @NotBlank
        String descricao,

        @NotNull
        Gravidade gravidade,

        @NotNull
        Long idDispositivo
) {
}