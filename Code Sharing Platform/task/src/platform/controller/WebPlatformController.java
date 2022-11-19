package platform.controller;

import freemarker.template.TemplateModel;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import platform.Code;
import platform.CodeService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
public class WebPlatformController implements TemplateModel {
    private final CodeService codeService;

    public WebPlatformController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(value = "/code/{id}")
    public ModelAndView getCodeById(HttpServletResponse response, @PathVariable UUID id) throws NotFoundException, IOException {
        response.addHeader("Content-Type", "text/html");
        ModelAndView model = new ModelAndView("codeSingle");
        codeService.deleteCodeRestriction();

        Code code = codeService.getCodeById(id);
        System.out.println("PRZED IFEM\n" + code.toString());
        if (code.isToBeDeleted() || code.equals(null)) {
            System.out.println("PIERWSZY IF");
            System.out.println(code.toString());
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        System.out.println("PO PIERWSZYM IFIE");
        System.out.println(code.toString());
        code.updateRestriction();
        codeService.saveCode(code);
        if (code.isToBeDeleted() || code.equals(null)) {
            System.out.println("OSTATNI IF");
            System.out.println(code.toString());
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        model.addObject("codeBody", code.getCode());
        model.addObject("date", code.getDate());
        model.addObject("time_restriction", code.isTimeRestricted());
        model.addObject("views_restriction", code.isViewRestricted());
        model.addObject("time", code.getTimeRestriction());
        model.addObject("views", code.getViewRestriction());
        response.setStatus(HttpServletResponse.SC_OK);



        System.out.println("PO OSTATNIM IFIE");
        System.out.println(code.toString());


        return model;
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
