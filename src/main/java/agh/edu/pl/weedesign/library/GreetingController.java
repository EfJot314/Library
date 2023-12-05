package agh.edu.pl.weedesign.library;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping
    public String greeting() {
        return "WeeDesign";
    }
}
