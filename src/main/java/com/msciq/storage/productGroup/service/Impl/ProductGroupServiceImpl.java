package com.msciq.storage.productGroup.service.Impl;

import com.msciq.storage.model.ProductGroup;
import com.msciq.storage.model.request.ProductGroupDTO;
import com.msciq.storage.productGroup.repository.ProductGroupRepository;
import com.msciq.storage.productGroup.service.ProductGroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductGroupServiceImpl implements ProductGroupService {

    @Autowired
    private ProductGroupRepository productGroupRepository;

    ModelMapper modelMapper = new ModelMapper();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductGroup> getAllProductGroups() {
        return productGroupRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductGroup> addProductGroups(List<ProductGroupDTO> productGroupDTOs) {
        List<ProductGroup> productGroups = new ArrayList<>();
        productGroupDTOs.stream().forEach(productGroupDTO -> {
            productGroups.add(modelMapper.map(productGroupDTO, ProductGroup.class));
        });
        return productGroupRepository.saveAll(productGroups);
    }
}
