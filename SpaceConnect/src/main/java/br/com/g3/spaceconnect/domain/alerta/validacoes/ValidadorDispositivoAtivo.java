package br.com.g3.spaceconnect.domain.alerta.validacoes;

import br.com.g3.spaceconnect.domain.ValidacaoException;
import br.com.g3.spaceconnect.domain.alerta.DadosCadastroAlerta;
import br.com.g3.spaceconnect.domain.dispositivo.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDispositivoAtivo implements ValidadorDeAlerta {

    @Autowired
    private DispositivoRepository repository;

    @Override
    public void validar(DadosCadastroAlerta dados) {
        var dispositivo = repository.getReferenceById(dados.idDispositivo());

        if (!dispositivo.getAtivo()) {
            throw new ValidacaoException("Alerta rejeitado: O dispositivo espacial com este ID encontra-se inativo no sistema.");
        }
    }
}