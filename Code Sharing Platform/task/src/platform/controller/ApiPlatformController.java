package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.Code;
import platform.CodeService;
import platform.CodeUserRepresentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static platform.Mapper.map;

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
        if (code.getViewRestriction()>0) {
            codeNew.setViewRestricted(true);
            codeNew.setViewRestriction(code.getViewRestriction());
        }
        codeService.saveCode(codeNew);
        System.out.println(codeNew);
        return new ResponseEntity<>(Map.of("id", String.valueOf(codeNew.getId())), HttpStatus.OK);
    }

    @GetMapping(value = "/code/{id}")
    public ResponseEntity<?> getCodeByIdApi(@PathVariable UUID id) {
        Code codeNew = codeService.getCodeById(id);
        if (codeNew == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        codeNew.updateRestriction();
        codeService.saveCode(codeNew);
        codeService.deleteCodeRestriction();


        return new ResponseEntity<>(map(codeNew), HttpStatus.OK);
    }

    @GetMapping(value = "/code/latest")
    public ResponseEntity<?> getLatestCodeApi() {
        List<CodeUserRepresentation> codeList = new ArrayList<>();
        for (Code code:
                codeService.latest10()) {
            codeList.add(map(code));
        }
        return new ResponseEntity<>(codeList, HttpStatus.OK);
    }
}
