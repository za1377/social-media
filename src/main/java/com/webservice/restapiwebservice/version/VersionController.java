package com.webservice.restapiwebservice.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @GetMapping("/v1/person")
    public PersonV1 getFirstPerson() {
        return new  PersonV1("Zahra Anvari");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondPerson() {
        return new  PersonV2(new Name("Zahra", "Anvari"));
    }

    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstPersonRequestParameter() {
        return new  PersonV1("Zahra Anvari");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondPersonRequestParameter() {
        return new  PersonV2(new Name("Zahra", "Anvari"));
    }
}
