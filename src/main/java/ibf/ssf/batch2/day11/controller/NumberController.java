package ibf.ssf.batch2.day11.controller;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping(path={"/", "/index.html"}, produces = MediaType.TEXT_HTML_VALUE)

public class NumberController {
    @GetMapping
    public String getNumber(Model model){

        final Logger logger = Logger.getLogger(NumberController.class.getName()); 
        
        //generate random number
        Random random = new Random(); 
        int randomNumber = random.nextInt(30);

        //join random number with img directory
        String imgUrl = "/numbers/number%d.jpg".formatted(randomNumber);
        
        //log info
        logger.log(Level.INFO, ">>>Generated imgUrl: %s".formatted(imgUrl));

        //add model attributes: random number generated and img url
        model.addAttribute("randomNumber", randomNumber); 
        model.addAttribute("imgUrl", imgUrl);
        
        //return page
        return "index";
    }
}
