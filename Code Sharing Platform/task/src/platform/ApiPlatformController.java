package platform;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ApiPlatformController {
    @GetMapping("/api/code")
    public ResponseEntity<?> getCode() {
        return new ResponseEntity<>(Map.of("code", CodeService.getHomeApi()), HttpStatus.OK);
    }
}
