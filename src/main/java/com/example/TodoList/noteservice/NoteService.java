package com.example.TodoList.noteservice;

import com.example.TodoList.entity.Note;

import java.util.List;
import java.util.UUID;

public interface NoteService {
    /**
     * Gets all notes;
     */
    List<Note> listAll();

    /**
     * Adds new note, generate new unique id and returns the note with id.
     */
    Note add(Note note);

    /**
     * Deletes note with defined id.
     * Throws exception when there is no note with defined id.
     */
    void deleteById(UUID id) throws Exception;

    /**
     * Updates note with defined id.
     * Throws exception when there is no note with defined id.
     */
    void update(Note note) throws Exception;

    /**
     * Gets note with defined id.
     * Throws exception when there is no note with defined id.
     */
    Note getById(UUID id) throws Exception;
}
