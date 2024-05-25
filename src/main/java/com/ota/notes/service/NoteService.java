package com.ota.notes.service;

import com.ota.notes.model.Note;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    public Note createNote(Note note);

    public List<Note> getAllNotes();

    public Optional<Note> getNoteById(Long id);

    public Optional<Note> updateNote(Long id, Note noteDetails);

    public boolean deleteNoteById(Long id);
}
