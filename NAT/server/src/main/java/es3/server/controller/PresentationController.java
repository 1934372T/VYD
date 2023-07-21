package es3.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import es3.server.rules.Consts;
import es3.server.rules.Grade;
import es3.server.service.PresentationService;
import lombok.Data;

@RestController
@RequestMapping(Consts.API_PREFIX+"/presentation")
public class PresentationController {
    private final PresentationService service;

    @Autowired
    public PresentationController(PresentationService service) {
        this.service = service;
    }
}
