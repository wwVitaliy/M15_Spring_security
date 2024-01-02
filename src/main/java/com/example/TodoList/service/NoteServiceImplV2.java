package com.example.TodoList.service;

import com.example.TodoList.data.entity.NoteEntity;
import com.example.TodoList.data.repository.NoteRepository;
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
public class NoteServiceImplV2 implements NoteService {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private NoteMapper noteMapper;

    @Override
    public List<NoteDto> listAll() {
        return noteMapper.toNoteDtos(noteRepository.findAll());
    }

    @Override
    public NoteDto add(NoteDto noteDto) {
        NoteEntity noneEntity = noteMapper.toNoneEntity(noteDto);
        if (Objects.isNull(noneEntity.getId())) {
            noneEntity.setId(UUID.randomUUID());
        }
        return noteMapper.toNoteDto(noteRepository.save(noneEntity));
    }

    @Override
    public void deleteById(UUID id) throws NoteNotFoundException {
        if (noteRepository.findById(id).isPresent()){
            noteRepository.deleteById(id);
        }else{
            throw new NoteNotFoundException(id);
        }
    }

    @Override
    public void update(NoteDto noteDto) throws NoteNotFoundException {
        if (noteRepository.findById(noteDto.getId()).isPresent()){
            noteRepository.save(noteMapper.toNoneEntity(noteDto));
        }else{
            throw new NoteNotFoundException(noteDto.getId());
        }
    }

    @Override
    public NoteDto getById(UUID id) throws Exception {
        Optional<NoteEntity> optional = noteRepository.findById(id);
        if (optional.isPresent()){
            return noteMapper.toNoteDto(optional.get());
        }else{
            throw new NoteNotFoundException(id);
        }
    }
}
