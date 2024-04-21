package br.com.kaua.mostratempo.service;

import br.com.kaua.mostratempo.dto.ConsultarTempoCidadeDTO;
import br.com.kaua.mostratempo.dto.LerTempoDTO;
import br.com.kaua.mostratempo.model.Cidade;
import br.com.kaua.mostratempo.model.ConsultaTempo;
import br.com.kaua.mostratempo.model.Usuario;
import br.com.kaua.mostratempo.repository.ConsultaTempoRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaTempoService {

    private final ConsultaTempoRepository consultaTempoRepository;
    private final CidadeService cidadeService;
    private final OpenWeatherMapService openWeatherMapService;
    private final UsuarioService usuarioService;

    @Autowired
    private ConsultaTempoService(ConsultaTempoRepository consultaTempoRepository,
                                 CidadeService cidadeService,
                                 OpenWeatherMapService openWeatherMapService,
                                 UsuarioService usuarioService) {
        this.consultaTempoRepository = consultaTempoRepository;
        this.cidadeService = cidadeService;
        this.openWeatherMapService = openWeatherMapService;
        this.usuarioService = usuarioService;
    }

    public LerTempoDTO consultar(ConsultarTempoCidadeDTO dto, Long usuarioId) {
        Cidade cidade = cidadeService.verificarOuCriar(dto);

        ConsultaTempo consultaTempo = new ConsultaTempo(cidade);

        String resposta = openWeatherMapService.buscarTempoPelaCidade(cidade.getNome());
        LerTempoDTO lerTempoDTO = parseJson(resposta, consultaTempo.getId());
        consultaTempo.setTempo(lerTempoDTO);

        if (usuarioId != null)
            consultaTempo.setUsuario(usuarioService.buscarPorId(usuarioId));

        Long idGerado = consultaTempoRepository.save(consultaTempo).getId();
        lerTempoDTO = parseJson(resposta, idGerado);

        return lerTempoDTO;
    }

    public List<LerTempoDTO> consultarFavoritosPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        List<Cidade> cidadesFavoritas = usuario.getCidadesFavoritas();

        List<LerTempoDTO> temperaturasDTOs = new ArrayList<>();

        for (Cidade cidade : cidadesFavoritas) {
            ConsultaTempo consultaTempo = new ConsultaTempo(usuario, cidade);

            String resposta = openWeatherMapService.buscarTempoPelaCidade(cidade.getNome());
            LerTempoDTO lerTempoDTO = parseJson(resposta, consultaTempo.getId());
            consultaTempo.setTempo(lerTempoDTO);
            Long idGerado = consultaTempoRepository.save(consultaTempo).getId();

            lerTempoDTO = parseJson(resposta, idGerado);
            temperaturasDTOs.add(lerTempoDTO);
        }

        return temperaturasDTOs;
    }

    public List<LerTempoDTO> visualizarConsultasPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioService.buscarPorId(usuarioId);

        return consultaTempoRepository.findByUsuario(usuario).stream()
                .map(this::toLerTemperaturaDTO)
                .toList();
    }

    private LerTempoDTO parseJson(String repostaJson, Long consultaId) {
        JSONObject json = new JSONObject(repostaJson);
        JSONObject main = json.getJSONObject("main");

        String nomeCidade = json.getString("name");
        double temperatura = main.getDouble("temp");
        double sensacaoTermica = main.getDouble("feels_like");
        double temperaturaMinima = main.getDouble("temp_min");
        double temperaturaMaxima = main.getDouble("temp_max");
        double humidade = main.getDouble("humidity");
        int pressao = main.getInt("pressure");
        Instant dataConsulta = Instant.ofEpochSecond(json.getLong("dt"));

        return new LerTempoDTO(consultaId, nomeCidade, temperatura, sensacaoTermica, temperaturaMinima, temperaturaMaxima, humidade, pressao, dataConsulta);
    }

    private LerTempoDTO toLerTemperaturaDTO(ConsultaTempo consulta) {
        return new LerTempoDTO(
                consulta.getId(),
                consulta.getNomeCidade(),
                consulta.getTemperatura(),
                consulta.getSensacaoTermica(),
                consulta.getTemperaturaMinima(),
                consulta.getTemperaturaMaxima(),
                consulta.getHumidade(),
                consulta.getPressao(),
                consulta.getDataConsulta()
        );
    }
}
