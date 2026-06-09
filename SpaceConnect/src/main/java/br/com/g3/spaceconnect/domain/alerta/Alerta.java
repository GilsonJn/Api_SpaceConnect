package br.com.g3.spaceconnect.domain.alerta;

import br.com.g3.spaceconnect.domain.dispositivo.Dispositivo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "alertas")
@Entity(name = "Alerta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Alerta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private Gravidade gravidade;

    @Column(name = "data_hora_emissao")
    private LocalDateTime dataHoraEmissao;

    @Column(name = "data_hora_resolucao")
    private LocalDateTime dataHoraResolucao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dispositivo_id")
    private Dispositivo dispositivoOrigem;

    public void marcarComoResolvido() {
        this.dataHoraResolucao = LocalDateTime.now();
    }

    public void atualizarInformacoes(DadosAtualizacaoAlerta dados) {
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.gravidade() != null) {
            this.gravidade = dados.gravidade();
        }
    }
}