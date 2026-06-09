package br.com.g3.spaceconnect.domain.dispositivo;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoDispositivo(
        @NotNull
        Long id,
        String nome,
        String localizacao,
        Boolean ativo
) {
}