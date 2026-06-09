package br.com.g3.spaceconnect.domain.alerta.validacoes;

import br.com.g3.spaceconnect.domain.alerta.DadosCadastroAlerta;

public interface ValidadorDeAlerta {
    void validar(DadosCadastroAlerta dados);
}