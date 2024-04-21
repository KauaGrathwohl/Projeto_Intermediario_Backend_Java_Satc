package br.com.kaua.mostratempo.service;

import br.com.kaua.mostratempo.dto.CriarUsuarioDTO;
import br.com.kaua.mostratempo.dto.LerCidadeDTO;
import br.com.kaua.mostratempo.enterprise.BusinessRuleException;
import br.com.kaua.mostratempo.enterprise.EntityNotFoundException;
import br.com.kaua.mostratempo.model.Cidade;
import br.com.kaua.mostratempo.model.Usuario;
import br.com.kaua.mostratempo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CidadeService cidadeService;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, CidadeService cidadeService) {
        this.usuarioRepository = usuarioRepository;
        this.cidadeService = cidadeService;
    }

    public void criar(CriarUsuarioDTO dto) {
        verificarSeUsuarioExiste(dto.nome());
        Usuario usuario = new Usuario(dto);
        usuarioRepository.save(usuario);
    }

    public void favoritarCidade(Long usuarioId, Long cidadeId) {
        Usuario usuario = buscarPorId(usuarioId);
        Cidade cidade = cidadeService.buscarPorId(cidadeId);

        usuario.adicionarCidadeFavorita(cidade);
        usuarioRepository.save(usuario);
    }

    public List<LerCidadeDTO> listarCidadesFavoritas(Long usuarioId) {
        Usuario usuario = buscarPorId(usuarioId);
        return usuario.getCidadesFavoritas().stream()
                .map(cidade -> new LerCidadeDTO(cidade.getId(), cidade.getNome()))
                .toList();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    private void verificarSeUsuarioExiste(String nome) {
        Optional<Usuario> usuario = usuarioRepository.findByNome(nome);
        if (usuario.isPresent())
            throw new BusinessRuleException("Usuário já cadastrado");
    }
}
