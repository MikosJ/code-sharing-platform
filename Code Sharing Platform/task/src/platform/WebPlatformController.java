package platform;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebPlatformController {

    @GetMapping("/code")
    public ResponseEntity<String> getCode() {
        return new ResponseEntity<>(CodeService.getHomeWeb(), HttpStatus.OK);
    }
}
