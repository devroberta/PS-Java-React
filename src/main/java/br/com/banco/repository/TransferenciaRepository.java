package br.com.banco.repository;

import br.com.banco.entity.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    @Query(value = "SELECT t.id, t.data_transferencia, t.valor, t.tipo, t.nome_operador_transacao, t.conta_id "
            + "FROM transferencia AS t "
            + "INNER JOIN conta AS c ON c.id_conta = t.conta_id "
            + "WHERE t.conta_id = :conta_id", nativeQuery = true)
    List<Transferencia> findAllTransferenciaById(Long conta_id);
}
