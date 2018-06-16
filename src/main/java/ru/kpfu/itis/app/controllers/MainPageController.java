package ru.kpfu.itis.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.app.forms.CalcForm;
import ru.kpfu.itis.app.validators.CalcFormValidator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;


@Controller
@RequestMapping("/")
public class MainPageController {

    @Autowired
    CalcFormValidator calcFormValidator;
    @InitBinder("calcForm")
    public void initValidator(WebDataBinder binder) {
        binder.addValidators(calcFormValidator);
    }
    @RequestMapping(value = "", method = RequestMethod.GET, params = {"a", "b", "oper"})
    public String getResults(@Valid @ModelAttribute ("calcForm")CalcForm calcForm,
                             BindingResult errors,
                             RedirectAttributes attributes
                             ) {
       /* String  a = calcForm.getA();
        String b = calcForm.getB();
        String oper = calcForm.getOper();
        System.out.println(a);
        System.out.println(b);
        System.out.println(oper);
        */

        if (errors.hasErrors()) {
            attributes.addFlashAttribute("error", errors.getAllErrors().get(0).getDefaultMessage());
            attributes.addFlashAttribute("a", calcForm.getA());
            attributes.addFlashAttribute("b", calcForm.getB());
            attributes.addFlashAttribute("oper", calcForm.getOper());
            return "redirect:/";
        }
        Double a = Double.parseDouble(calcForm.getA());
        Double b = Double.parseDouble(calcForm.getB());
        Double result = 0.0;
        String oper = calcForm.getOper();
        switch(oper){
            case "plus":
                result = a + b;
                break;

            case "minus":
                result = a - b;
                break;
            case "mult":
                result = a * b;
                break;

            case "div":
                result = a / b ;
                break;

        }
        attributes.addFlashAttribute("a", calcForm.getA());
        attributes.addFlashAttribute("b", calcForm.getB());
        attributes.addFlashAttribute("oper", calcForm.getOper());
        attributes.addFlashAttribute("answer" , result);

        return "redirect:/";

    }

    @GetMapping("")
    public String getMainPage(){
        return "index";
    }
}
