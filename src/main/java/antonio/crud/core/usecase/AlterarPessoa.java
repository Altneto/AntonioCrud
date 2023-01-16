package antonio.crud.core.usecase;

import antonio.crud.infra.database.repository.PessoaRepository;
import antonio.crud.infra.exception.BusinessException;
import antonio.crud.infra.helper.I18NHelper;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlterarPessoa {

    I18NHelper i18NHelper;
    private final PessoaRepository repository;
    private final ObterPessoaPorId obterPessoaPorId;

    @Transactional
    public void execute(@NonNull final Long id, @NonNull final In pessoa){
        final var persistent = obterPessoaPorId.execute(id);
        BeanUtils.copyProperties(pessoa, persistent, "id");
        repository.save(persistent);
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
