package SmartSafetyMap.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeTestController {

    @GetMapping("/")
    public String home() {
        return "Hello World";
        //23
    }

}
