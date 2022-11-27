package platform.controller;

import freemarker.template.TemplateModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import platform.model.Code;
import platform.service.CodeService;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class WebPlatformController implements TemplateModel {
    private final CodeService codeService;

    public WebPlatformController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(path = "/code/{id}")
    public String get(Model model, @PathVariable UUID id) {

        Code code = codeService.getCodeById(id);
        if (id == null || code == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (code.getViewRestriction() <= 0 && code.isViewRestricted()) {
            codeService.deleteByID(id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (code.getTimeRestriction() <= 0 && code.isTimeRestricted()) {
            codeService.deleteByID(id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        code.updateRestriction();
        codeService.saveCode(code);

        model.addAttribute("codeBody", code.getCode());
        model.addAttribute("date", code.getDate());
        model.addAttribute("time_restriction", code.isTimeRestricted());
        model.addAttribute("views_restriction", code.isViewRestricted());
        model.addAttribute("time", code.getTimeRestriction());
        model.addAttribute("views", code.getViewRestriction());

        return "codeSingle";
    }

    @GetMapping(value = "/code/new")
    public ModelAndView newCode(HttpServletResponse response) {

        response.addHeader("Content-Type", "text/html");

        codeService.deleteCodeRestriction();

        return new ModelAndView("submitForm");
    }

    @GetMapping(value = "/code/latest")
    public ModelAndView getLatestCode(@ModelAttribute("model") ModelMap model) {

        ModelAndView codeList = new ModelAndView("latest");

        codeList.addObject(codeService.latest10());
        codeService.deleteCodeRestriction();

        return codeList;
    }

}
