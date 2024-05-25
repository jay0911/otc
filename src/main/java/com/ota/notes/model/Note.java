package com.ota.notes.model;

import com.ota.notes.dto.NoteResponseDto;

public class Note {
    private Long id;
    private String title;
    private String body;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public NoteResponseDto toResponseDto(){
        NoteResponseDto dto = new NoteResponseDto();
        dto.setBody(body);
        dto.setTitle(title);
        dto.setId(id);
        return dto;
    }
}