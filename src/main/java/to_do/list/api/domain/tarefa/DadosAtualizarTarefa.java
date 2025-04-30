package to_do.list.api.domain.tarefa;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarTarefa(
        @NotNull
        Long id,
        String titulo,
        String descricao,
        Boolean concluido) {
    public DadosAtualizarTarefa(Tarefa tarefa){
        this(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getConcluido());
    }
}
