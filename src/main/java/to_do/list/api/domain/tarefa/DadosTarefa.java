package to_do.list.api.domain.tarefa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosTarefa(
        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String descricao,
        @NotNull
        Boolean concluido){
        public DadosTarefa(Tarefa tarefa){
                this(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getConcluido());
        }
}
