package br.com.kaua.mostratempo.model;

import br.com.kaua.mostratempo.dto.LerTempoDTO;
import jakarta.persistence.*;

import java.time.Instant;


@Entity
@Table(name = "consultas_tempo")
public class ConsultaTempo extends BaseEntity {

    @Column(name = "nome_cidade", nullable = false)
    private String nomeCidade;

    @Column(name = "temperatura", nullable = false)
    private Double temperatura;

    @Column(name = "sensacao_termica", nullable = false)
    private Double sensacaoTermica;

    @Column(name = "temperatura_minima", nullable = false)
    private Double temperaturaMinima;

    @Column(name = "temperatura_maxima", nullable = false)
    private Double temperaturaMaxima;

    @Column(name = "humidade", nullable = false)
    private Double humidade;

    @Column(name = "pressao", nullable = false)
    private Integer pressao;

    @Column(name = "data_consulta", nullable = false)
    private Instant dataConsulta;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade cidade;

    public ConsultaTempo() {
    }

    public ConsultaTempo(Cidade cidade) {
        this.cidade = cidade;
    }

    public ConsultaTempo(Cidade cidade, LerTempoDTO dto) {
        this.cidade = cidade;
        this.nomeCidade = dto.nomeCidade();
        this.temperatura = dto.temperatura();
        this.sensacaoTermica = dto.sensacaoTermica();
        this.temperaturaMinima = dto.temperaturaMinima();
        this.temperaturaMaxima = dto.temperaturaMaxima();
        this.humidade = dto.humidade();
        this.pressao = dto.pressao();
        this.dataConsulta = dto.dataConsulta();
    }

    public ConsultaTempo(Usuario usuario, Cidade cidade) {
        this.usuario = usuario;
        this.cidade = cidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getSensacaoTermica() {
        return sensacaoTermica;
    }

    public void setSensacaoTermica(Double sensacaoTermica) {
        this.sensacaoTermica = sensacaoTermica;
    }

    public Double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(Double temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public Double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(Double temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public Double getHumidade() {
        return humidade;
    }

    public void setHumidade(Double humidade) {
        this.humidade = humidade;
    }

    public Integer getPressao() {
        return pressao;
    }

    public void setPressao(Integer pressao) {
        this.pressao = pressao;
    }

    public Instant getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Instant dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public void setTempo(LerTempoDTO dto) {
        this.nomeCidade = dto.nomeCidade();
        this.temperatura = dto.temperatura();
        this.sensacaoTermica = dto.sensacaoTermica();
        this.temperaturaMinima = dto.temperaturaMinima();
        this.temperaturaMaxima = dto.temperaturaMaxima();
        this.humidade = dto.humidade();
        this.pressao = dto.pressao();
        this.dataConsulta = dto.dataConsulta();
    }
}
