package grapp.grapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class appController implements ErrorController{

    private String dbUrl = "postgres://fqdunfercvmtrb:4893ba593d036a518f11634deae9224233e95c7f1e9e37bb2f446805dceb3a29@ec2-52-50-171-4.eu-west-1.compute.amazonaws.com:5432/dduetcch1mnm33";

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

    /*@Bean
    public DataSource dataSource() throws SQLException {
      if (dbUrl == null || dbUrl.isEmpty()) {
        return new HikariDataSource();
      } else {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        return new HikariDataSource(config);
      }
    }*/

    @PostMapping(value="/upload")
    String uploadPost(Model model, @Valid formulario formulario, BindingResult bindingResult){
        //upload photo
        String generatedId = imgUrlScraper.uploadImg(formulario.getImg());
        model.addAttribute("imgUrl", imgUrlScraper.getImageUrl(generatedId));
        //get id 
        String userID = formulario.getText();
        model.addAttribute("id", generatedId);
        model.addAttribute("userID", userID);
        //bbddd
        try (Connection connection = DataSource.getConnection()) {
            Statement stmt = connection.createStatement();
            
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS imgs (idUser varchar2(10), idImg varchar2(10))");
            stmt.executeUpdate("INSERT INTO imgs VALUES (" + userID + ", " + generatedId  + ")");
        } catch(Exception e){
            model.addAttribute("excepcion", e.getMessage());
        }
        return "upload.html";
    }

    @GetMapping(value="/see")
    String see(Model model,@Valid formulario formulario){        
        return "see.html";
    }

    @PostMapping(value="/see")
    String seePost(Model model, @Valid formulario formulario, BindingResult bindingResult){
        if(!bindingResult.hasErrors()) {
            String imgSrc = imgUrlScraper.getImageUrl(imgUrlScraper.searchById(formulario.getText()));
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
