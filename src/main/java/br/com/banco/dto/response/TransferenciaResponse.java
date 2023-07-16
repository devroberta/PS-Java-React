package br.com.banco.dto.response;

import br.com.banco.entity.Transferencia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TransferenciaResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDate dados;
    private BigDecimal valencia;
    private String tipo;
    private String nomeOperadorTransacionado;

    public TransferenciaResponse(Transferencia transferencia) {
        this.id = transferencia.getId();
        this.dados = transferencia.getDataTransferencia().toLocalDate();
        this.valencia = transferencia.getValor();
        this.tipo = transferencia.getTipo();
        this.nomeOperadorTransacionado = transferencia.getNomeOperadorTransacao();
    }
}
