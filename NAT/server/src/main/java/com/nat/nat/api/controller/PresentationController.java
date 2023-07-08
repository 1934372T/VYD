package com.nat.nat.api.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/presentation")
public class PresentationController {
    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("paper") MultipartFile paperFile, @RequestParam("slide") MultipartFile slideFile) {
        String message = "";
        try {
            // save files to a path
            String paperFileName = saveUploadedFile(paperFile);
            String slideFileName = saveUploadedFile(slideFile);
            message = "Uploaded the files successfully: " + paperFileName + ", " + slideFileName;
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Fail to upload files!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    private String saveUploadedFile(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            String newFileName = UUID.randomUUID() + "_" + fileName;  // unique name
            Path filePath = Paths.get("./tmp/" + newFileName);
            file.transferTo(filePath);
            return newFileName;
        } else {
            throw new RuntimeException("Empty file!");
        }
    }
}
