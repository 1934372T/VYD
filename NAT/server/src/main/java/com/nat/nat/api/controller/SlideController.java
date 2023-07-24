package com.nat.nat.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nat.nat.api.services.interfaces.SlideServiceInterface;
import com.nat.nat.rules.Consts;

@RestController
@RequestMapping(Consts.API_PREFIX+"/slide")
public class SlideController {
    private final SlideServiceInterface service;

    @Autowired
    public SlideController(SlideServiceInterface service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<?> getById(@RequestParam("id") int id) {
        return this.service.getById(id);
    }
}
