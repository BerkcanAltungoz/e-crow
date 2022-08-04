package ecrow.backend.core.utilities;

import ecrow.backend.core.utilities.results.Result;
import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class Utils {
    public static ResponseEntity<?> getResponseEntity(Result result) {
        if (result.isSuccess()) return ResponseEntity.ok(result);
        else return ResponseEntity.badRequest().body(result);
    }
}
