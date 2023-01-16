package antonio.crud.infra.exception;

import org.springframework.lang.NonNull;

public class BusinessException extends  RuntimeException {

    public BusinessException(@NonNull String message) {
        super(message);
    }

}
