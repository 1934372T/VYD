package com.nat.nat.api.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nat.nat.api.usecase.interfaces.PresentationUsecaseInterfaces;

@RestController
@RequestMapping("/api/v1/presentation")
public class PresentationController {

    private final PresentationUsecaseInterfaces usecase;

    @Autowired
    public PresentationController(PresentationUsecaseInterfaces usecase) {
        this.usecase = usecase;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> create(@RequestHeader HttpHeaders headers, @RequestParam("paper") MultipartFile paperFile, @RequestParam("slide") MultipartFile slideFile, @RequestParam("title") String title, @RequestParam("date") String date, @RequestParam("note") String note) {
        List<String> authHeaders = headers.get("Authorization");
        Instant instant = Instant.parse(date);
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return this.usecase.create(authHeaders, paperFile, slideFile, title, ldt, note);
    }
}