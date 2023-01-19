package antonio.crud.core.usecase;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import antonio.crud.core.domain.Pessoa;
import antonio.crud.infra.database.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListarPessoas {
    private final PessoaRepository repository;

    public Page<Pessoa> execute(@Nullable final String nome,
                                @Nullable final String telefone,
                                @Nullable final String cpf,
                                @NonNull final Pageable pageable){
        final var example = Pessoa.builder();
        Optional.ofNullable(nome).ifPresent(example::nome);
        Optional.ofNullable(telefone).ifPresent(example::telefone);
        Optional.ofNullable(cpf).ifPresent(example::cpf);
        return repository.findAll(Example.of(example.build(),
                ExampleMatcher.matchingAll()
                        .withMatcher("nome",
                                      ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING,true))
                ), pageable);
    }

    public List<Pessoa> list(){
        return repository.findAll();
    }

}
