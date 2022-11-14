package platform.controller;

import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import platform.Code;
import platform.CodeService;

import javax.servlet.http.HttpServletResponse;

@Controller
public class WebPlatformController implements TemplateModel {
    @Autowired
    CodeService codeService;

    @GetMapping(value = "/code/{id}")
    public ModelAndView getCodeById(HttpServletResponse response, @PathVariable Long id) {
        response.addHeader("Content-Type", "text/html");
        ModelAndView model = new ModelAndView("codeSingle");
        Code code = codeService.getCodeById(id);
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
    public ModelAndView getLatestCode(HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html");
        ModelAndView latest = new ModelAndView("latest");
        latest.addObject(codeService.latest10());
        return latest;
    }

}
