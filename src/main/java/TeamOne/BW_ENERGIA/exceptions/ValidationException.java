package TeamOne.BW_ENERGIA.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private List<String> errorMessage;

    public ValidationException(List<String> errorMessage) {
        super("Alcuni errori di validazione!");
        this.errorMessage = errorMessage;
    }

}
