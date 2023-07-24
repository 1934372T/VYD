package com.nat.nat.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nat.nat.api.services.interfaces.PaperServiceInterface;
import com.nat.nat.rules.Consts;

@RestController
@RequestMapping(Consts.API_PREFIX+"/paper")
public class PaperController {
    private final PaperServiceInterface service;

    @Autowired
    public PaperController(PaperServiceInterface service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<?> getById(@RequestParam("id") int id) {
        return this.service.getById(id);
    }
}
