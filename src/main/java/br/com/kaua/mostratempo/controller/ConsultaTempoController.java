package br.com.kaua.mostratempo.controller;

import br.com.kaua.mostratempo.dto.ConsultarTempoCidadeDTO;
import br.com.kaua.mostratempo.dto.LerTempoDTO;
import br.com.kaua.mostratempo.service.ConsultaTempoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultas-tempo")
public class ConsultaTempoController {

    private final ConsultaTempoService consultaTempoService;

    @Autowired
    public ConsultaTempoController(ConsultaTempoService consultaTempoService) {
        this.consultaTempoService = consultaTempoService;
    }

    @PostMapping("/consultar")
    public ResponseEntity<LerTempoDTO> consultar(@RequestBody ConsultarTempoCidadeDTO dto,
                                                 @RequestParam(required = false) Long usuarioId) {
        LerTempoDTO tempoDTO = consultaTempoService.consultar(dto, usuarioId);
        return new ResponseEntity<>(tempoDTO, HttpStatus.OK);
    }

    @PostMapping("/consultar-favoritos-por-usuario/{usuarioId}")
    public ResponseEntity<List<LerTempoDTO>> consultarFavoritosPorUsuario(@PathVariable Long usuarioId) {
        List<LerTempoDTO> tempoDTOs = consultaTempoService.consultarFavoritosPorUsuario(usuarioId);
        return new ResponseEntity<>(tempoDTOs, HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<LerTempoDTO>> visualizarConsultasPorUsuario(@PathVariable Long usuarioId) {
        List<LerTempoDTO> tempoDTOs = consultaTempoService.visualizarConsultasPorUsuario(usuarioId);
        return new ResponseEntity<>(tempoDTOs, HttpStatus.OK);
    }
}
