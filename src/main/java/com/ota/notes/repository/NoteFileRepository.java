package com.ota.notes.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ota.notes.model.Note;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository("NoteFileRepository")
public class NoteFileRepository implements NoteRepository {

    private final File file = new File("notes.json");
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AtomicLong currentId = new AtomicLong(0);

    public NoteFileRepository() {
        if (file.exists()) {
            try {
                List<Note> notes = objectMapper.readValue(file, new TypeReference<List<Note>>() {});
                for (Note note : notes) {
                    currentId.updateAndGet(x -> Math.max(x, note.getId()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized Note save(Note note) {
        List<Note> notes = readNotesFromFile();
        if (note.getId() == null) {
            note.setId(currentId.incrementAndGet());
        }
        notes.add(note);
        writeNotesToFile(notes);
        return note;
    }

    public List<Note> findAll() {
        return readNotesFromFile();
    }

    public Optional<Note> findById(Long id) {
        return readNotesFromFile().stream().filter(note -> note.getId().equals(id)).findFirst();
    }

    public synchronized Note update(Note note) {
        List<Note> notes = readNotesFromFile();
        notes.removeIf(n -> n.getId().equals(note.getId()));
        notes.add(note);
        writeNotesToFile(notes);
        return note;
    }

    public synchronized void deleteById(Long id) {
        List<Note> notes = readNotesFromFile();
        notes.removeIf(note -> note.getId().equals(id));
        writeNotesToFile(notes);
    }

    private synchronized List<Note> readNotesFromFile() {
        try {
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<Note>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private synchronized void writeNotesToFile(List<Note> notes) {
        try {
            objectMapper.writeValue(file, notes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
