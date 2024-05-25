package com.ota.notes.repository;

import com.ota.notes.model.Note;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository("NoteInMemoryRepository")
public class NoteInMemoryRepository implements NoteRepository {

    private final Map<Long, Note> notes = new ConcurrentHashMap<>();
    private final AtomicLong currentId = new AtomicLong(0);

    public synchronized Note save(Note note) {
        if (note.getId() == null) {
            note.setId(currentId.incrementAndGet());
        }
        notes.put(note.getId(), note);
        return note;
    }

    public List<Note> findAll() {
        return new ArrayList<>(notes.values());
    }

    public Optional<Note> findById(Long id) {
        return Optional.ofNullable(notes.get(id));
    }

    public synchronized Note update(Note note) {
        notes.put(note.getId(), note);
        return note;
    }

    public synchronized void deleteById(Long id) {
        notes.remove(id);
    }
}
