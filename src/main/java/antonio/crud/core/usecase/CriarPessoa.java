package antonio.crud.core.usecase;

import antonio.crud.core.domain.Pessoa;
import antonio.crud.infra.database.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class CriarPessoa {
    private final PessoaRepository repository;

    @Transactional
    public Pessoa execute (@NonNull final In pessoa){
        return repository.save(Pessoa.builder()
            .nome(pessoa.nome)
            .telefone(pessoa.telefone)
            .cpf(pessoa.cpf)
            .build());
    }

    public record In(
        @NotBlank
        @Size(min = 3, max = 80)
        String nome,
        @Size(min = 8 , max = 12)
        String telefone,
        @CPF
        String cpf
    ){};




}
