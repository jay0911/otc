package com.ota.notes.dto;

public class NoteResponseDto extends NoteRequestDto {
    private Long id;

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }
}
