package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.Code;
import platform.CodeService;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiPlatformController {

    @Autowired
    CodeService codeService;


    @PostMapping("/code/new")
    public ResponseEntity<?> newCodeApi(@RequestBody Code code) {
        Code codeNew = new Code(code.getCode());
        System.out.println(codeNew);
        System.out.println("ASDDASADADSAADSSADASDADS");
        codeService.saveCode(codeNew);
        System.out.println(codeNew);
        return new ResponseEntity<>(Map.of("id", String.valueOf(codeNew.getId())), HttpStatus.OK);
    }
    @GetMapping(value = "/code/{id}")
    public ResponseEntity<?> getCodeByIdApi(@PathVariable Long id) {
        return new ResponseEntity<>(codeService.getCodeById(id), HttpStatus.OK);
    }
    @GetMapping(value = "/code/latest")
    public ResponseEntity<?> getLatestCodeApi() {
        return new ResponseEntity<>(codeService.latest10(), HttpStatus.OK);
    }
}
