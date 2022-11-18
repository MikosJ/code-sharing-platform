package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    CodeService codeService;


    @PostMapping("/code/new")
    public ResponseEntity<?> newCodeApi(@RequestBody Code code) {
        Code codeNew = new Code(code.getCode());
        if (code.getTimeRestriction() > 0) {
            codeNew.setTimeRestricted(true);
            codeNew.setTimeRestriction(code.getTimeRestriction());
        }
        if (code.getViewRestriction() > 0) {
            codeNew.setViewRestricted(true);
            codeNew.setViewRestriction(code.getViewRestriction());
        }
        codeService.saveCode(codeNew);
        //codeService.deleteCodeRestriction();
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
