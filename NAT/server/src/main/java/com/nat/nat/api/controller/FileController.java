package com.nat.nat.api.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@SpringBootApplication
public class FileController {

    private static final String PREFIX = "/file";

    @PostMapping(PREFIX)
    public String createNewFile() {
        return "hello";
    }

    @GetMapping(PREFIX)
    public void getFileContentsById() {}

    @GetMapping(PREFIX+"/data")
    public void getFileDataById() {}

    @GetMapping(PREFIX+"/data")
    public void getFileDataList() {}
}
