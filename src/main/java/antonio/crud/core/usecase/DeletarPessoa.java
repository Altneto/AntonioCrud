package antonio.crud.core.usecase;

import antonio.crud.infra.database.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletarPessoa {
    private final PessoaRepository repository;
    private final ObterPessoaPorId obterPessoaPorId;

    @Transactional
    public void execute(@NonNull final Long id){
        repository.delete(obterPessoaPorId.execute(id));
    }

}
