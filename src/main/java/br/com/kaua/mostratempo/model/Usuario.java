package br.com.kaua.mostratempo.model;

import br.com.kaua.mostratempo.dto.CriarUsuarioDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuarios")
public class Usuario extends BaseEntity {

    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "usuario")
    private Set<ConsultaTempo> consultasTempo;

    @ManyToMany
    @JoinTable(
            name = "favoritos",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "cidade_id")
    )
    private Set<Cidade> cidadesFavoritas;

    public Usuario() {
    }

    public Usuario(String nome) {
        this.nome = nome;
    }

    public Usuario(CriarUsuarioDTO dto) {
        this.nome = dto.nome();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarCidadeFavorita(Cidade cidade) {
        this.cidadesFavoritas.add(cidade);
    }

    public List<Cidade> getCidadesFavoritas() {
        return List.copyOf(cidadesFavoritas);
    }
}
