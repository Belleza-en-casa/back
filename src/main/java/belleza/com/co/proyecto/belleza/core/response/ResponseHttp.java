package belleza.com.co.proyecto.belleza.core.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class ResponseHttp<T> {

    private boolean success;
    private String message;
    private T body;

    public ResponseHttp(boolean success, String message, T body) {
        this.success = success;
        this.message = message;
        this.body = body;
    }


    public static <T> ResponseEntity<ResponseHttp<T>> successResponse(String message, T body) {
        return ResponseEntity.ok(new ResponseHttp<>(true, message, body));
    }

    public static <T> ResponseEntity<ResponseHttp<T>> errorResponse(String message, Map<String, String> errors) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", message);
        responseBody.put("errors", errors);
        return ResponseEntity.badRequest().body(new ResponseHttp<>(false,
                "Error en la operación",
                (T) responseBody));
    }

}
