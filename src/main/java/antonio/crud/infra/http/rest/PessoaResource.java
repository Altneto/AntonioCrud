package antonio.crud.infra.http.rest;

import antonio.crud.core.domain.Pessoa;
import antonio.crud.core.usecase.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/pessoas")
public class PessoaResource {
    private final CriarPessoa criar;
    private final AlterarPessoa alterar;
    private final DeletarPessoa deletar;
    private final ObterPessoaPorId obterPessoaPorId;
    private final ListarPessoas listarPessoas;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody final CriarPessoa.In pessoa,
                       @NotNull final HttpServletResponse response){
        final var created = criar.execute(pessoa);
        response.setHeader(HttpHeaders.LOCATION, ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri().toString());
    }

    @PutMapping("/{id}")
    public void update(@PathVariable final Long id,
                    @Valid @RequestBody final AlterarPessoa.In pessoa){
        alterar.execute(id, pessoa);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final Long id){
        deletar.execute(id);
    }

    @GetMapping("/{id}")
    public Pessoa getById(@PathVariable final Long id){
        return obterPessoaPorId.execute(id);
    }

    @GetMapping
    public Page<Pessoa> list(@RequestParam(required = false) final String nome,
                             @RequestParam(required = false) final String telefone,
                             @RequestParam(required = false) final String cpf,
                             @PageableDefault final Pageable pageable){
        return listarPessoas.execute(nome,telefone,cpf,pageable);
    }

}
