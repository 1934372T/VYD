package es3.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es3.server.rules.Consts;
import es3.server.service.SlideService;

@RestController
@RequestMapping(Consts.API_PREFIX+"/slide")
public class SlideController {
    private final SlideService service;

    @Autowired
    public SlideController(SlideService service) {
        this.service = service;
    }
}
