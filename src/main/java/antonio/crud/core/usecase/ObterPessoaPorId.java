package antonio.crud.core.usecase;

import antonio.crud.core.domain.Pessoa;
import antonio.crud.infra.database.repository.PessoaRepository;
import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterPessoaPorId {
    private final PessoaRepository repository;
    public Pessoa execute(@NonNull final Long id){
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException(String.format("Pessoa Não Econtrada: %s",id)));
    }
}
