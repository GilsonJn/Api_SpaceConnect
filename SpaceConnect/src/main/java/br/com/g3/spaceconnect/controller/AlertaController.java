package br.com.g3.spaceconnect.controller;

import br.com.g3.spaceconnect.domain.alerta.*;
import br.com.g3.spaceconnect.domain.alerta.validacoes.ValidadorDeAlerta;
import br.com.g3.spaceconnect.domain.dispositivo.DispositivoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key")
@RequestMapping("/alertas")
@CrossOrigin(origins = "*")
public class AlertaController {

    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private List<ValidadorDeAlerta> validadores;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoAlerta> registrarAlerta(@RequestBody @Valid DadosCadastroAlerta dados, UriComponentsBuilder uriBuilder) {

        validadores.forEach(v -> v.validar(dados));

        var dispositivo = dispositivoRepository.findById(dados.idDispositivo())
                .orElseThrow(EntityNotFoundException::new);

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

    @PutMapping("/{id}/resolver")
    @Transactional
    public ResponseEntity<DadosDetalhamentoAlerta> resolverAlerta(@PathVariable Long id) {

        var alerta = alertaRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        alerta.marcarComoResolvido();
        return ResponseEntity.ok(new DadosDetalhamentoAlerta(alerta));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        alertaRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}