package to_do.list.api.domain.tarefa;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import to_do.list.api.domain.usuario.Usuario;

@Table(name = "tarefas")
@Entity(name = "Tarefa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tarefa {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private Boolean concluido;

    @ManyToOne(fetch = FetchType.LAZY) // muitas tarefas para um usuário
    @JoinColumn(name = "usuario_id")   // nome da FK no banco de dados
    private Usuario usuario;

    public Tarefa(@Valid DadosTarefa dados, Usuario usuario) {
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.concluido = dados.concluido() != null ? dados.concluido() : false;
        this.usuario = usuario;
    }

    public void atualizarInformacoes(@Valid DadosAtualizarTarefa dados) {
        if(dados.titulo() != null)
            this.titulo = dados.titulo();
        if(dados.descricao() != null)
            this.descricao = dados.descricao();
        if(dados.concluido() != null)
            this.concluido = dados.concluido();
    }
}
