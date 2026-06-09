package br.com.g3.spaceconnect.domain.dispositivo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Satelite")
@DiscriminatorValue("SATELITE")
@Getter
@NoArgsConstructor
public class Satelite extends Dispositivo {

    private String orbita;

    @Override
    public String analisarStatusDeRisco() {
        return "Analisando macroclima e tempestades via telemetria da NASA...";
    }

    public Satelite(String nome, String localizacao, String orbita) {
        super(null, nome, localizacao, true);
        this.orbita = orbita;
    }
}