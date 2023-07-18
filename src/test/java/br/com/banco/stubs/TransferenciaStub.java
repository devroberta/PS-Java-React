package br.com.banco.stubs;

import br.com.banco.entity.Transferencia;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransferenciaStub {
    public static Transferencia gerarTransferenciaCustomizada() {
        return Transferencia.builder()
                .id(1L)
                .dataTransferencia(LocalDate.of(2019,01,01).atTime(12,00,00))
                .valor(BigDecimal.valueOf(30895.46))
                .tipo("'DEPOSITO'")
                .nomeOperadorTransacao("Beltrano")
                .build();
    }
}
