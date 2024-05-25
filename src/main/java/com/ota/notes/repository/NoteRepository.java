package com.ota.notes.repository;

import com.ota.notes.model.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface NoteRepository {

    public Note save(Note note);

    public List<Note> findAll();

    public Optional<Note> findById(Long id);

    public Note update(Note note);

    public void deleteById(Long id);
}
