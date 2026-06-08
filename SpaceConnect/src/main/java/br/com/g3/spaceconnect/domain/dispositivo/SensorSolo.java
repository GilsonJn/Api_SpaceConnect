package br.com.g3.spaceconnect.domain.dispositivo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "SensorSolo")
@DiscriminatorValue("SENSOR_SOLO")
@Getter
@NoArgsConstructor
public class SensorSolo extends Dispositivo {

    private String culturaMonitorada;

    @Override
    public String analisarStatusDeRisco() {
        return "Analisando microclima e presença de fungos via OpenCV...";
    }
}