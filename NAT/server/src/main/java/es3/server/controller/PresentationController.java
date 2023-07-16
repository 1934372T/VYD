package es3.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es3.server.rules.Consts;
import es3.server.service.PresentationService;

@RestController
@RequestMapping(Consts.API_PREFIX+"/presentation")
public class PresentationController {
    @Autowired
    private final PresentationService service;

    public PresentationController(PresentationService service) {
        this.service = service;
    }

}
