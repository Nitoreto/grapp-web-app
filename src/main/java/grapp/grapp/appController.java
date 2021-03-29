package grapp.grapp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class appController implements ErrorController{

    @GetMapping(value= "/")
    String index(Model model){
        model.addAttribute("key", "prueba");
        List<String> listado = new ArrayList<String>();
        listado.add("primero");
        listado.add("tercero");
        listado.add("quinto");
        model.addAttribute("users", listado);
        return "index.html";
    }

    @GetMapping(value="/form")
    String form(Model model,@Valid formulario formulario){
        //model.addAttribute("formulario", new Formulario());
        return "form.html";
    }

    @PostMapping(value="/form")
    String formPost(Model model, @Valid formulario formulario, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "form.html";
        }
        //model.addAttribute("img", formulario.getImg().toURI());
        model.addAttribute("confirmation", "He recibido el POST y los campos estan bien. Pusiste:" +formulario.getText());
        return "form.html";
    }

    @RequestMapping(value = "/error")
    public String error() {
        return "Error handling";
    }

    @Override
    public String getErrorPath() {
        // TODO Auto-generated method stub
        return "/error";
    }

}
