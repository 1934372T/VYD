package com.nat.nat.domain;

public class File {
    private int id;
    private String name;
    private String studentId;
    private String fileId;

    public File() {}

    public File(String name, String fileId) {
        this.name = name;
        this.fileId = fileId;
    }

    public File(int id, String name, String fileId) {
        this.id = id;
        this.name = name;
        this.fileId = fileId;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getFieldId() {
        return this.fileId;
    }

    public String getStudentId() {
        return this.studentId;
    }
}
