package com.nat.nat.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nat.nat.api.services.interfaces.PresentationServiceInterface;
import com.nat.nat.rules.Consts;

@RestController
@RequestMapping(Consts.API_PREFIX+"/presentation")
public class PresentationController {

    private final PresentationServiceInterface service;
    
    private static final String UPLOAD      = "/upload";
    private static final String GET_BY_ID   = "/";
    private static final String LIST        = "/list";
    private static final String TERMS       = "/terms";

    @Autowired
    public PresentationController(PresentationServiceInterface service) {
        this.service = service;
    }

    @PostMapping(UPLOAD)
    public ResponseEntity<?> create(@RequestHeader HttpHeaders headers, @RequestParam("paper") MultipartFile paperFile, @RequestParam("slide") MultipartFile slideFile, @RequestParam("title") String title, @RequestParam("date") String date, @RequestParam("note") String note) {
        List<String> authHeaders = headers.get("Authorization");
        return this.service.create(authHeaders, paperFile, slideFile, title, date, note);
    }

    @GetMapping(GET_BY_ID)
    public ResponseEntity<?> getById(@RequestParam("id") int id) {
        return this.service.getById(id);
    }

    @GetMapping(LIST)
    public ResponseEntity<?> getListWithQuery(@RequestParam("term") String term, @RequestParam("degree") String degree) {
        List<String> queries = new ArrayList<String>();
        if(!term.contains("none")) {
            queries.add("term="+term);
        }
        if(!degree.contains("none")) {
            queries.add("degree="+degree);
        }
        return this.service.getListWithQuery(queries);
    }

    @GetMapping(TERMS)
    public ResponseEntity<?> getAllTerms() {
        return this.service.getAllTerm();
    }
}
