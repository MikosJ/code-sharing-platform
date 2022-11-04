package platform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import platform.codeRepo.CodeService;

@Controller
public class WebPlatformController {

    @GetMapping("/code/{id}")
    public ResponseEntity<String> getCode(@PathVariable int id) {
        return new ResponseEntity<>(CodeService.getHomeWeb(id), HttpStatus.OK);
    }

    @GetMapping("/code/new")
    public ResponseEntity<String> getCodeSnippet() {
        return new ResponseEntity<>(CodeService.setCodeWeb(), HttpStatus.OK);
    }

    @GetMapping("/code/latest")
    public String getLatest(Model model) {
        model.addAttribute("codeList", CodeService.getLatest());
        return "latest";
    }

}
