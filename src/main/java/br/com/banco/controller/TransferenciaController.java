package br.com.banco.controller;

import br.com.banco.dto.response.TransferenciaResponse;
import br.com.banco.service.TransferenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

    private TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @GetMapping("/{idConta}/filtro")
    public ResponseEntity<List<TransferenciaResponse>> buscarPorDatasEPorNomeOperadorTransacao(
            @PathVariable Long idConta,
            @RequestParam(value = "dataInicial", defaultValue = "0") String dataInicial,
            @RequestParam(value = "dataFinal", defaultValue = "0") String dataFinal,
            @RequestParam(value = "nomeOperadorTransacao", defaultValue = "0") String nomeOperadorTransacao) {

        return ResponseEntity.ok().body(transferenciaService.buscarPorDatasEPorNomeOperadorTransacao(
                idConta, dataInicial, dataFinal, nomeOperadorTransacao
        ));
    }
}
