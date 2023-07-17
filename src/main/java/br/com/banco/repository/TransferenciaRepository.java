package br.com.banco.repository;

import br.com.banco.entity.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    @Query(value = "SELECT t.id, t.data_transferencia, t.valor, t.tipo, t.nome_operador_transacao, t.conta_id "
                    + "FROM transferencia as t "
                    + "JOIN conta AS c ON c.id_conta = t.conta_id "
                    + "WHERE t.conta_id = :id "
                    + "AND t.data_transferencia BETWEEN :dataInicial AND :dataFinal "
                    + "AND (case when :nomeOperador = '%%' then (0=0) "
                    + "else (lower(t.nome_operador_transacao) LIKE lower(:nomeOperador)) "
                    + "end)", nativeQuery = true)
    List<Transferencia> findByDataTransferenciaBetweenAndNomeOperadorTransacao(
            @Param("id") Long id,
            @Param("dataInicial") LocalDate dataInicial,
            @Param("dataFinal") LocalDate dataFinal,
            @Param("nomeOperador") String nomeOperador);
}





