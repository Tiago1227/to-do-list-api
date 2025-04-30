package to_do.list.api.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import to_do.list.api.domain.tarefa.DadosAtualizarTarefa;
import to_do.list.api.domain.tarefa.DadosTarefa;
import to_do.list.api.domain.tarefa.Tarefa;
import to_do.list.api.domain.tarefa.TarefaRepository;
import to_do.list.api.domain.usuario.Usuario;
import to_do.list.api.domain.usuario.UsuarioRepository;

@RestController
@RequestMapping("tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosTarefa dados, UriComponentsBuilder uriBuilder){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Usuario usuario = (Usuario) usuarioRepository.findByLogin(userDetails.getUsername());


        var tarefa = new Tarefa(dados, usuario);
        repository.save(tarefa);
        var uri = uriBuilder.path("/tarefas/{id}").buildAndExpand(tarefa.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosTarefa(tarefa));
    }

    @GetMapping
    public ResponseEntity<Page<DadosTarefa>> listar(Pageable paginacao) {
        var usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var tarefas = repository.findByUsuario(usuarioLogado, paginacao)
                .map(DadosTarefa::new);
        return ResponseEntity.ok(tarefas);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarTarefa dados){
        var usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var tarefa = repository.getReferenceById(dados.id());

        if (!tarefa.getUsuario().getId().equals(usuario.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden
        }

        tarefa.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosTarefa(tarefa));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var tarefa = repository.getReferenceById(id);

        if (!tarefa.getUsuario().getId().equals(usuario.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden
        }

        repository.delete(tarefa);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhamento(@PathVariable Long id){
        var usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var tarefa = repository.getReferenceById(id);

        if (!tarefa.getUsuario().getId().equals(usuario.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden
        }
        return ResponseEntity.ok(new DadosTarefa(tarefa));
    }
}

