package com.ota.notes.service;

import com.ota.notes.model.Note;
import com.ota.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    @Qualifier("NoteFileRepository")  //can be NoteFileRepository or NoteInMemoryRepository
    private NoteRepository noteRepository;

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    public Optional<Note> updateNote(Long id, Note noteDetails) {
        Optional<Note> note = noteRepository.findById(id);
        if (note.isPresent()) {
            Note existingNote = note.get();
            existingNote.setTitle(noteDetails.getTitle());
            existingNote.setBody(noteDetails.getBody());
            return Optional.of(noteRepository.update(existingNote));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteNoteById(Long id) {
        Optional<Note> note = noteRepository.findById(id);
        if (note.isPresent()) {
            noteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
