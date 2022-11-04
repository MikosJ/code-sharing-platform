package platform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.Code;
import platform.codeRepo.CodeService;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiPlatformController {
    @GetMapping("/code")
    public ResponseEntity<?> getCode() {
        return new ResponseEntity<>(CodeService.getHomeApi(), HttpStatus.OK);
    }

    @PostMapping("/code/new")
    public ResponseEntity<?> newCode(@RequestBody Code code) {
        return new ResponseEntity<>(Map.of("id",CodeService.addCodeToRepo(code)), HttpStatus.OK);
    }

    @GetMapping("/code/{id}")
    public ResponseEntity<?> getCodeById(@PathVariable int id) {
        return new ResponseEntity<>(CodeService.getCodeFromRepo(id),HttpStatus.OK);
    }
    @GetMapping("/code/latest")
    public ResponseEntity<?> getLatest() {
        return new ResponseEntity<>(CodeService.getLatest(),HttpStatus.OK);
    }

}
