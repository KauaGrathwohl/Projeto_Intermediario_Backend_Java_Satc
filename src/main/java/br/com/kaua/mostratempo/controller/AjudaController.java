package br.com.kaua.mostratempo.controller;

import br.com.kaua.mostratempo.dto.LerProjetoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ajuda")
public class AjudaController {

    @GetMapping
    public ResponseEntity<Object> ajuda() {
        LerProjetoDTO lerProjetoDTO = new LerProjetoDTO("Kauã", "Mostra Tempo");
        return ResponseEntity.ok(lerProjetoDTO);
    }
}
