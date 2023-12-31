package com.example.TodoList.noteservice;

import com.example.TodoList.data.FakeNoteRepo;
import com.example.TodoList.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private FakeNoteRepo noteRepo;

    @Override
    public List<Note> listAll() {
        return noteRepo.getAllNotes();
    }

    @Override
    public Note add(Note note) {
        note.setId(UUID.randomUUID());
        noteRepo.addNote(note);
        return note;
    }

    @Override
    public void deleteById(UUID id) throws Exception {
        noteRepo.deleteNote(id);
    }

    @Override
    public void update(Note note) throws Exception {
        noteRepo.updateNote(note);
    }

    @Override
    public Note getById(UUID id) throws Exception {
        return noteRepo.getNote(id);
    }
}
