package br.com.g3.spaceconnect.domain.dispositivo;

public record DadosDetalhamentoDispositivo(
        Long id,
        String tipo,
        String nome,
        String localizacao,
        Boolean ativo,
        String orbita,
        String culturaMonitorada
) {
    public DadosDetalhamentoDispositivo(Dispositivo dispositivo) {
        this(
                dispositivo.getId(),
                dispositivo instanceof Satelite ? "SATELITE" : "SENSOR_SOLO",
                dispositivo.getNome(),
                dispositivo.getLocalizacao(),
                dispositivo.getAtivo(),
                dispositivo instanceof Satelite s ? s.getOrbita() : null,
                dispositivo instanceof SensorSolo s ? s.getCulturaMonitorada() : null
        );
    }
}