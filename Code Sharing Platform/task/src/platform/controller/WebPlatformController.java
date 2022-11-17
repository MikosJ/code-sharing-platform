package platform.controller;

import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import platform.Code;
import platform.CodeService;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class WebPlatformController implements TemplateModel {
    @Autowired
    CodeService codeService;

    @GetMapping(value = "/code/{id}")
    public ModelAndView getCodeById(HttpServletResponse response, @PathVariable UUID id) {
        response.addHeader("Content-Type", "text/html");
        ModelAndView model = new ModelAndView("codeSingle");
        Code code = codeService.getCodeById(id);
        code.updateRestriction();
        codeService.saveCode(code);
        codeService.deleteCodeRestriction();
        model.addObject("codeBody", code.getCode());
        model.addObject("date", code.getDate());
        return model;
    }

    @GetMapping(value = "/code/new")
    public ModelAndView newCode(HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html");
        return new ModelAndView("submitForm");
    }

    @GetMapping(value = "/code/latest")
    public ModelAndView getLatestCode(@ModelAttribute("model") ModelMap model) {
        ModelAndView codeList = new ModelAndView("latest");
        codeList.addObject(codeService.latest10());
        return codeList;
    }

}
