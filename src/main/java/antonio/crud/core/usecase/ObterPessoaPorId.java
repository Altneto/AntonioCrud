package antonio.crud.core.usecase;

import antonio.crud.core.domain.Pessoa;
import antonio.crud.infra.database.repository.PessoaRepository;
import antonio.crud.infra.helper.I18NHelper;
import jakarta.persistence.EntityNotFoundException;
import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterPessoaPorId {
    private  final I18NHelper i18NHelper;
    private final PessoaRepository repository;
    public Pessoa execute(@NonNull final Long id){
        return repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(i18NHelper.getMessage("app.message.error.entity.notfound",
                    i18NHelper.getMessage("app.message.error.entity.pessoa"),
                    id)));
    }
}
