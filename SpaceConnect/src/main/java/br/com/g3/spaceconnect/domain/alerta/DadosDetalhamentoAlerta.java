package br.com.g3.spaceconnect.domain.alerta;

import java.time.LocalDateTime;

public record DadosDetalhamentoAlerta(
        Long id,
        String descricao,
        Gravidade gravidade,
        LocalDateTime dataHoraEmissao,
        LocalDateTime dataHoraResolucao,
        Long idDispositivo
) {

    public DadosDetalhamentoAlerta(Alerta alerta) {
        this(alerta.getId(),
                alerta.getDescricao(),
                alerta.getGravidade(),
                alerta.getDataHoraEmissao(),
                alerta.getDataHoraResolucao(),
                alerta.getDispositivoOrigem().getId());
    }
}