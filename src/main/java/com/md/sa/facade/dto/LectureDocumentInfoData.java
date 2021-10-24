package com.md.sa.facade.dto;

public class LectureDocumentInfoData {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public LectureDocumentInfoData setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public LectureDocumentInfoData setName(String name) {
        this.name = name;
        return this;
    }
}
