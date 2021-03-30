package grapp.grapp;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class appController implements ErrorController{

    @GetMapping(value= "/")
    String index(Model model){
        model.addAttribute("key", "prueba");
        List<String> listado = new ArrayList<String>();
        listado.add("Pagina principal: vuelve a la página principal");
        listado.add("Subir fotos: te permite subir una foto devolviendo un id");
        listado.add("Ver fotos: te permite ver las fotos subidas mediante id");
        model.addAttribute("features", listado);
        return "index.html";
    }

    @GetMapping(value="/upload")
    String upload(Model model,@Valid formulario formulario){
        return "upload.html";
    }

    @PostMapping(value="/upload")
    String uploadPost(Model model, @Valid formulario formulario, BindingResult bindingResult){
        //TODO upload photo
        String imgSrc = imgUrlScraper.uploadImg(formulario.getImg());
        model.addAttribute("imgUrl", imgSrc);

        //TODO get id 
        String generatedId="0";
        model.addAttribute("id", generatedId);
        return "upload.html";
    }

    @GetMapping(value="/see")
    String see(Model model,@Valid formulario formulario){        
        return "see.html";
    }

    @PostMapping(value="/see")
    String seePost(Model model, @Valid formulario formulario, BindingResult bindingResult){
        if(!bindingResult.hasErrors()) {
            String imgSrc = imgUrlScraper.searchById(formulario.getText());
            model.addAttribute("imgUrl", imgSrc);
        }
        return "see.html";
    }

    //---------------------------------------------------------------------------------------------------

    //HTTP Error handle DO NOT TOUCH

    @Override
    public String getErrorPath() {
        // TODO Auto-generated method stub
        return "/error";
    }

}
