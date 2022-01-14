package com.example.Tp1JEE;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestWebService {

    @GetMapping("/coucou")
    public String bonjour()
    {
        return "bonjour";
    }

}
