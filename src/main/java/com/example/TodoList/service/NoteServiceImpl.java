package com.example.TodoList.service;

import com.example.TodoList.data.repository.FakeNoteRepo;
import com.example.TodoList.data.entity.NoteEntity;
import com.example.TodoList.service.dto.NoteDto;
import com.example.TodoList.service.exception.NoteNotFoundException;
import com.example.TodoList.service.mapper.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private FakeNoteRepo noteRepo;
    @Autowired
    private NoteMapper noteMapper;

    @Override
    public List<NoteDto> listAll() {
        return noteMapper.toNoteDtos(noteRepo.findAll());
    }

    @Override
    public NoteDto add(NoteDto noteDto) {
        if (Objects.isNull(noteDto.getId())) {
            noteDto.setId(UUID.randomUUID());
        }
        NoteEntity noteEntity = noteMapper.toNoneEntity(noteDto);
        return noteMapper.toNoteDto(noteRepo.save(noteEntity));
    }

    @Override
    public void deleteById(UUID id) throws NoteNotFoundException {
       if (noteRepo.findById(id).isPresent()){
           noteRepo.delete(id);
       }else{
           throw new NoteNotFoundException(id);
       }
    }

    @Override
    public void update(NoteDto noteDto) throws Exception {
        if (noteRepo.findById(noteDto.getId()).isPresent()){
            noteRepo.save(noteMapper.toNoneEntity(noteDto));
        }else{
            throw new NoteNotFoundException(noteDto.getId());
        }
    }

    @Override
    public NoteDto getById(UUID id) throws Exception {
        Optional<NoteEntity> optional = noteRepo.findById(id);
        if (optional.isPresent()){
            return noteMapper.toNoteDto(optional.get());
        }else{
            throw new NoteNotFoundException(id);
        }
    }
}
