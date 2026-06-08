package br.com.g3.spaceconnect.controller;


import br.com.g3.spaceconnect.domain.alerta.*;
import br.com.g3.spaceconnect.domain.dispositivo.DispositivoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoAlerta> registrarAlerta(@RequestBody @Valid DadosCadastroAlerta dados, UriComponentsBuilder uriBuilder) {

        var dispositivo = dispositivoRepository.getReferenceById(dados.idDispositivo());
        var alerta = new Alerta(null, dados.descricao(), dados.gravidade(), LocalDateTime.now(), null, dispositivo);

        alertaRepository.save(alerta);

        var uri = uriBuilder.path("/alertas/{id}").buildAndExpand(alerta.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoAlerta(alerta));
    }

    @GetMapping
    public ResponseEntity<Iterable<DadosDetalhamentoAlerta>> listarTodos() {
        var alertas = alertaRepository.findAll().stream().map(DadosDetalhamentoAlerta::new).toList();
        return ResponseEntity.ok(alertas);
    }
}