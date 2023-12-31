package com.example.TodoList.service;

import com.example.TodoList.service.dto.NoteDto;

import java.util.List;
import java.util.UUID;

public interface NoteService {
    /**
     * Gets all notes;
     */
    List<NoteDto> listAll();

    /**
     * Adds new note, generate new unique id and returns the note with id.
     */
    NoteDto add(NoteDto noteDto);

    /**
     * Deletes note with defined id.
     * Throws exception when there is no note with defined id.
     */
    void deleteById(UUID id) throws Exception;

    /**
     * Updates note with defined id.
     * Throws exception when there is no note with defined id.
     */
    void update(NoteDto noteDto) throws Exception;

    /**
     * Gets note with defined id.
     * Throws exception when there is no note with defined id.
     */
    NoteDto getById(UUID id) throws Exception;
}
