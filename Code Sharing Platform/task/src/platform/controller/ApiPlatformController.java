package platform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.model.Code;
import platform.service.CodeService;
import java.util.Map;
import java.util.UUID;

@RestController
public class ApiPlatformController {

    private final CodeService codeService;

    public ApiPlatformController(CodeService codeService) {
        this.codeService = codeService;
    }


    @PostMapping("/api/code/new")
    public ResponseEntity<?> newCodeApi(@RequestBody Code code) {

        Code codeNew = new Code(code.getCode(), code.getTimeRestriction(), code.getViewRestriction());
        codeService.saveCode(codeNew);

        return new ResponseEntity<>(Map.of("id", String.valueOf(codeNew.getId())), HttpStatus.OK);
    }

    @GetMapping(value = "/api/code/{id}")
    public ResponseEntity<?> getCodeByIdApi(@PathVariable UUID id) {

        Code codeNew = codeService.getCodeById(id);

        codeNew.updateRestriction();
        codeService.saveCode(codeNew);

        if (codeNew.isToBeDeleted()) {
            codeService.deleteByID(id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(codeNew, HttpStatus.OK);
    }

    @GetMapping(value = "/api/code/latest")
    public ResponseEntity<?> getLatestCodeApi() {

        codeService.deleteCodeRestriction();
        return new ResponseEntity<>(codeService.latest10(), HttpStatus.OK);
    }
}
