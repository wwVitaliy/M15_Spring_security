package com.example.TodoList.data;

import com.example.TodoList.entity.Note;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FakeNoteRepo {
    private List<Note> notes;

    @PostConstruct
    private void postConstruct() {
        notes = new ArrayList<>();
        System.out.println("--->>> NoteRepo created.");
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public Note getNote(UUID id) throws Exception {
        for (Note note : notes) {
            if (note.getId().equals(id)) return note;
        }
        throw new Exception("Cannot find note with id = " + id);
    }

    public List<Note> getAllNotes() {
        return notes;
    }

    public void deleteNote(UUID id) throws Exception {
        for (Note note : notes) {
            if (note.getId().equals(id)) {
                notes.remove(note);
                break;
            }
        }
        throw new Exception("Cannot find note with id = " + id);
    }

    public void updateNote(Note note) throws Exception {
        for (Note noteFromList : notes) {
            if (noteFromList.getId().equals(note.getId())) {
                noteFromList.setContent(note.getContent());
                noteFromList.setTitle(note.getTitle());
                break;
            }
        }
        throw new Exception("Cannot find note with id = " + note.getId());
    }
}
