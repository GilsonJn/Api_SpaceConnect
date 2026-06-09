package br.com.g3.spaceconnect.controller;

import br.com.g3.spaceconnect.domain.dispositivo.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key")
@RequestMapping("/dispositivos")
@CrossOrigin(origins = "*")
public class DispositivoController {

    @Autowired
    private DispositivoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoDispositivo> cadastrar(@RequestBody @Valid DadosCadastroDispositivo dados, UriComponentsBuilder uriBuilder) {
        Dispositivo dispositivo;

        if ("SATELITE".equalsIgnoreCase(dados.tipo())) {
            dispositivo = new Satelite(dados.nome(), dados.localizacao(), dados.orbita());
        } else {
            dispositivo = new SensorSolo(dados.nome(), dados.localizacao(), dados.culturaMonitorada());
        }

        repository.save(dispositivo);

        var uri = uriBuilder.path("/dispositivos/{id}").buildAndExpand(dispositivo.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoDispositivo(dispositivo));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoDispositivo> atualizar(@RequestBody @Valid DadosAtualizacaoDispositivo dados) {

        var dispositivo = repository.getReferenceById(dados.id());
        dispositivo.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoDispositivo(dispositivo));
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoDispositivo>> listar() {
        var lista = repository.findAll().stream().map(DadosDetalhamentoDispositivo::new).toList();
        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var dispositivo = repository.getReferenceById(id);
        dispositivo.inativar(); // exclusão lógica

        return ResponseEntity.noContent().build();
    }
}