package es3.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es3.server.rules.Consts;
import es3.server.service.PaperService;

@RestController
@RequestMapping(Consts.API_PREFIX+"/paper")
public class PaperController {
    private final PaperService service;

    @Autowired
    public PaperController(PaperService service) {
        this.service = service;
    }
}
