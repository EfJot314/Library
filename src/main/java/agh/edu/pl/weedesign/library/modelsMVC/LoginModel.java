package agh.edu.pl.weedesign.library.modelsMVC;

import java.util.HashMap;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import agh.edu.pl.weedesign.library.entities.reader.Reader;
import agh.edu.pl.weedesign.library.services.ReaderService;

@Component
public class LoginModel {
    private ConfigurableApplicationContext springContext;
    private final ReaderService readerService;

    @Autowired
    public LoginModel(ConfigurableApplicationContext springContext, ReaderService readerService){
        this.readerService = readerService;
        this.springContext = springContext;
    }

    public HashMap<String, String> login(String name, String password){
        try {
            Reader reader = readerService.findByEmail(name);

            if(reader == null)
                throw new IllegalArgumentException("Wrong password or username");

            if(!Objects.equals(reader.getPassword(), password))
                throw new IllegalArgumentException("Wrong password or username");


        } catch (IllegalArgumentException e ){
            return new HashMap<String, String>(){{
                put("status", "failure");
                put("message", e.getMessage());
            }};
        };

        return new HashMap<String, String>(){{
            put("status", "success");
            put("message", "");
        }};
    };
}
