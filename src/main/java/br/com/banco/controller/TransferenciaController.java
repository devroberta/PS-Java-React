package br.com.banco.controller;

import br.com.banco.dto.response.TransferenciaResponse;
import br.com.banco.service.TransferenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

    private TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @GetMapping
    public ResponseEntity<List<TransferenciaResponse>> buscarTodas() {
        return ResponseEntity.ok().body(transferenciaService.buscarTodas());
    }
}
