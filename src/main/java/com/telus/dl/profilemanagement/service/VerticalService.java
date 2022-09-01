package com.telus.dl.profilemanagement.service;

import com.telus.dl.profilemanagement.document.Vertical;
import com.telus.dl.profilemanagement.dto.VerticalDto;
import com.telus.dl.profilemanagement.repository.VerticalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerticalService {
    private final VerticalRepository verticalRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VerticalService(VerticalRepository verticalRepository, ModelMapper modelMapper) {
        this.verticalRepository = verticalRepository;
        this.modelMapper = modelMapper;
    }

    public VerticalDto createVertical(VerticalDto verticalDto) {
        Vertical vertical = modelMapper.map(verticalDto, Vertical.class);
        vertical = verticalRepository.save(vertical);
        return modelMapper.map(vertical, VerticalDto.class);
    }

    public void deleteVertical(String verticalId) {
        verticalRepository.deleteById(verticalId);
    }

    public VerticalDto findVerticalById(String verticalId) {
        return verticalRepository
                .findById(verticalId)
                .map(vertical -> modelMapper.map(vertical, VerticalDto.class))
                .orElse(null);
    }

    public List<VerticalDto> findAllVerticals() {
        return verticalRepository.findAll().stream()
                .map(vertical -> modelMapper.map(vertical, VerticalDto.class))
                .toList();
    }
}
