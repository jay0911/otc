package com.ota.notes.dto;

import com.ota.notes.model.Note;

public class NoteRequestDto {
    private String title;
    private String body;

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

    public Note toNote() {
        Note note = new Note();
        note.setBody(body);
        note.setTitle(title);
        return note;
    }
}
