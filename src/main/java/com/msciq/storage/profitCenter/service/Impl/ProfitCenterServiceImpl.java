package com.msciq.storage.profitCenter.service.Impl;

import com.msciq.storage.model.ProfitCenter;
import com.msciq.storage.model.request.ProfitCenterDTO;
import com.msciq.storage.profitCenter.repository.ProfitCenterRepository;
import com.msciq.storage.profitCenter.service.ProfitCenterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfitCenterServiceImpl implements ProfitCenterService {

    @Autowired
    private ProfitCenterRepository profitCenterRepository;

    ModelMapper modelMapper = new ModelMapper();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProfitCenter> getAllProfitCenters() {
        return profitCenterRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProfitCenter> addProfitCenters(List<ProfitCenterDTO> profitCenterDTOS) {
        List<ProfitCenter> profitCenters = new ArrayList<>();
        profitCenterDTOS.stream().forEach(profitCenterDTO -> {
            profitCenters.add(modelMapper.map(profitCenterDTO, ProfitCenter.class));
        });
        return profitCenterRepository.saveAll(profitCenters);
    }
}
