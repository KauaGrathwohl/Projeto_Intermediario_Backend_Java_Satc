package br.com.kaua.mostratempo.controller;

import br.com.kaua.mostratempo.dto.CriarUsuarioDTO;
import br.com.kaua.mostratempo.dto.LerCidadeDTO;
import br.com.kaua.mostratempo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    private UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Void> criar(@RequestBody CriarUsuarioDTO dto) {
        usuarioService.criar(dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{usuarioId}/favoritar/{cidadeId}")
    public ResponseEntity<Void> favoritarCidade(@PathVariable Long usuarioId,
                                                @PathVariable Long cidadeId) {
        usuarioService.favoritarCidade(usuarioId, cidadeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{usuarioId}/cidades-favoritas")
    public ResponseEntity<List<LerCidadeDTO>> listarCidadesFavoritas(@PathVariable Long usuarioId) {
        List<LerCidadeDTO> cidades = usuarioService.listarCidadesFavoritas(usuarioId);
        return new ResponseEntity<>(cidades, HttpStatus.OK);
    }
}
