package platform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.Code;
import platform.CodeService;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ApiPlatformController {

    private final CodeService codeService;

    public ApiPlatformController(CodeService codeService) {
        this.codeService = codeService;
    }


    @PostMapping("/code/new")
    public ResponseEntity<?> newCodeApi(@RequestBody Code code) {
        Code codeNew = new Code(code.getCode(), code.getTimeRestriction(), code.getViewRestriction());
        codeService.saveCode(codeNew);
        return new ResponseEntity<>(Map.of("id", String.valueOf(codeNew.getId())), HttpStatus.OK);
    }

    @GetMapping(value = "/code/{id}")
    public ResponseEntity<?> getCodeByIdApi(@PathVariable UUID id) {
        Code codeNew = codeService.getCodeById(id);
        codeNew.updateRestriction();
        if (codeNew.isToBeDeleted()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        codeService.saveCode(codeNew);
        codeService.deleteCodeRestriction();

        return new ResponseEntity<>(codeNew, HttpStatus.OK);
    }

    @GetMapping(value = "/code/latest")
    public ResponseEntity<?> getLatestCodeApi() {

        codeService.deleteCodeRestriction();
        return new ResponseEntity<>(codeService.latest10(), HttpStatus.OK);
    }
}
