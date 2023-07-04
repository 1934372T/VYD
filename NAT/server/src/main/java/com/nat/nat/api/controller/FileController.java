package com.nat.nat.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    @PostMapping("")
    public String createNewFile() {
        return "hello";
    }

    @GetMapping("")
    public void getFileContentsById() {}

    // @GetMapping("/data")
    // public void getFileDataById() {}

    // @GetMapping("/data")
    // public void getFileDataList() {}
}
