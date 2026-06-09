package br.com.g3.spaceconnect.domain.dispositivo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "dispositivos")
@Entity(name = "Dispositivo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_dispositivo")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public abstract class Dispositivo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String localizacao;
    private Boolean ativo;

    public abstract String analisarStatusDeRisco();

    public void atualizarInformacoes(DadosAtualizacaoDispositivo dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.localizacao() != null) {
            this.localizacao = dados.localizacao();
        }
        if (dados.ativo() != null) {
            this.ativo = dados.ativo();
        }
    }

    public void inativar() {
        this.ativo = false;
    }
}