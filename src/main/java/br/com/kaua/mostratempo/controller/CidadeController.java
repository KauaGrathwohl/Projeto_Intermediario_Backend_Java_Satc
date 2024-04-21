package br.com.kaua.mostratempo.controller;

import br.com.kaua.mostratempo.dto.LerCidadeDTO;
import br.com.kaua.mostratempo.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {

    private final CidadeService cidadeService;

    @Autowired
    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public ResponseEntity<List<LerCidadeDTO>> buscarCidades() {
        List<LerCidadeDTO> cidades = cidadeService.buscarCidades();
        return new ResponseEntity<>(cidades, HttpStatus.OK);
    }
}
