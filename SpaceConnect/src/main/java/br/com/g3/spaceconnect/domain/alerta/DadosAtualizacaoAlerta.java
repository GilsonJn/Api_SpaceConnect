package br.com.g3.spaceconnect.domain.alerta;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoAlerta(
        @NotNull Long id,
        String descricao,
        Gravidade gravidade
) {
}