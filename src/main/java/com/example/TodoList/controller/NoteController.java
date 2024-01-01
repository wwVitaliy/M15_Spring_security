package com.example.TodoList.controller;

import com.example.TodoList.service.NoteService;
import com.example.TodoList.service.dto.NoteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

@Controller
@RequestMapping(value = "/note")
public class NoteController {
    @Autowired
    NoteService noteService;

    @GetMapping(value = "/list")
    public ModelAndView getAllNotes() {
        ModelAndView modelAndView = new ModelAndView("notes/allNotes");
        modelAndView.addObject("notes", noteService.listAll());
        return modelAndView;
    }

    @PostMapping(value = "/delete")
    public RedirectView deleteNote(@RequestParam(name = "id") UUID uuid) {
        try {
            noteService.deleteById(uuid);
            return new RedirectView("/note/list");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editNote(@PathVariable("id") UUID uuid) {
        ModelAndView modelAndView = new ModelAndView("notes/editNote");
        modelAndView.addObject("id", uuid);
        return modelAndView;

    }

    @PostMapping(value = "/edit")
    public RedirectView editNote(
            @RequestParam(value = "id") UUID uuid,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content") String content) {
        try {
            NoteDto note = noteService.getById(uuid);
            note.setTitle(title);
            note.setContent(content);
            noteService.update(note);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new RedirectView("/note/list");
    }

    @PostMapping(value = "/create")
    public RedirectView createNote(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "content")  String content) {
        try {
            NoteDto note =  new NoteDto();
            note.setTitle(title);
            note.setContent(content);
            noteService.add(note);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new RedirectView("/note/list");
    }


}
