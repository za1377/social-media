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

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstPersonHeaderParameter() {
        return new  PersonV1("Zahra Anvari");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondPersonHeaderParameter() {
        return new  PersonV2(new Name("Zahra", "Anvari"));
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstPersonAcceptParameter() {
        return new  PersonV1("Zahra Anvari");
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondPersonAcceptParameter() {
        return new  PersonV2(new Name("Zahra", "Anvari"));
    }

}
