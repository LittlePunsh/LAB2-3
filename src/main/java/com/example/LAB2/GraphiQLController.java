package com.example.LAB2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GraphiQLController {

    @GetMapping("/graphiql")
    public String graphiql() {
        return "graphiql.html";
    }
}
