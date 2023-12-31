package com.example.TodoList.data.repository;

import com.example.TodoList.data.entity.NoteEntity;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;


import java.util.*;

@Component
public class FakeNoteRepo {
    private List<NoteEntity> noteList;

    @PostConstruct
    private void postConstruct() {
        noteList = new ArrayList<>();
        System.out.println("--->>> NoteRepo created.");
    }

    public NoteEntity save(NoteEntity note) {
        //Generate Id if missing
        if (Objects.isNull(note.getId())) {
            note.setId(UUID.randomUUID());
        } else {
            //Delete note if id already exist in the list
            Optional<NoteEntity> noteFromList = this.noteList.stream()
                    .filter(n -> n.getId().equals(note.getId()))
                    .findFirst();
            noteFromList.ifPresent(noteEntity -> this.noteList.remove(noteEntity));
            //Add new note to the list
            this.noteList.add(note);
        }
        return note;
    }

    public Optional<NoteEntity> findById(UUID id) {
        return this.noteList.stream()
                .filter(note -> note.getId().equals(id))
                .findFirst();
    }

    public List<NoteEntity> findAll() {
        return this.noteList;
    }

    public void delete(UUID id) {
        this.noteList.stream()
                .filter(note -> note.getId().equals(id))
                .findFirst()
                .ifPresent(this.noteList::remove);
    }
}
