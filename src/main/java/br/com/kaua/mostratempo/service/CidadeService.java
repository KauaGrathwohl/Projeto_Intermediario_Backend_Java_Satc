package br.com.kaua.mostratempo.service;

import br.com.kaua.mostratempo.dto.ConsultarTempoCidadeDTO;
import br.com.kaua.mostratempo.dto.LerCidadeDTO;
import br.com.kaua.mostratempo.enterprise.EntityNotFoundException;
import br.com.kaua.mostratempo.model.Cidade;
import br.com.kaua.mostratempo.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    @Autowired
    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    public Cidade verificarOuCriar(ConsultarTempoCidadeDTO dto) {
        return cidadeRepository.findByNome(dto.nomeCidade())
                .orElseGet(() -> criar(dto));
    }

    public List<LerCidadeDTO> buscarCidades() {
        List<Cidade> cidades = cidadeRepository.findAll();

        return cidades.stream()
                .map(this::toLerCidadeDTO)
                .toList();
    }

    private Cidade criar(ConsultarTempoCidadeDTO dto) {
        Cidade cidade = new Cidade(dto.nomeCidade());
        return cidadeRepository.save(cidade);
    }

    public Cidade buscarPorId(Long id) {
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    private LerCidadeDTO toLerCidadeDTO(Cidade cidade) {
        return new LerCidadeDTO(cidade.getId(), cidade.getNome());
    }
}
