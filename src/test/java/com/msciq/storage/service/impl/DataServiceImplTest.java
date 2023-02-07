package com.msciq.storage.service.impl;

import com.msciq.storage.common.entity.GroupCompany;
import com.msciq.storage.common.msciq.CountryDTO;
import com.msciq.storage.common.msciq.CurrencyDTO;
import com.msciq.storage.common.msciq.GroupCompanyDTO;
import com.msciq.storage.common.msciq.StateDTO;
import com.msciq.storage.exception.BadRequestException;
import com.msciq.storage.exception.DataConflictException;
import com.msciq.storage.repository.GroupCompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
class DataServiceImplTest {

    @Mock
    GroupCompanyRepository groupCompanyRepository;
    @InjectMocks
    DataServiceImpl dataService;

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void shouldAddNewGroupCompany() {
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setCurrency("INR");
        currencyDTO.setDescription("Rupee");

        StateDTO stateDTO = new StateDTO();
        stateDTO.setName("TN");

        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setName("India");
        countryDTO.setCountryCode("IND");
        countryDTO.setDescription("India");
        countryDTO.setStates(Collections.singletonList(stateDTO));

        GroupCompanyDTO groupCompanyDTO = new GroupCompanyDTO();
        groupCompanyDTO.setId(1L);
        groupCompanyDTO.setGcCode("GC01");
        groupCompanyDTO.setGcName("GC");
        groupCompanyDTO.setCurrency(currencyDTO);
        groupCompanyDTO.setCountry(countryDTO);

        GroupCompany groupCompany = modelMapper.map(groupCompanyDTO, GroupCompany.class);
        when(groupCompanyRepository.save(groupCompany)).thenReturn(groupCompany);
        GroupCompany actualGroupCompany = dataService.addGroupCompany(groupCompanyDTO);

        assertEquals(groupCompanyDTO.getGcCode(), actualGroupCompany.getGcCode());
    }

    @Test
    public void shouldThrowBadRequestExceptionWhenTheInputGroupCompanyIsNull() {
        assertThrows(BadRequestException.class, () -> {
            dataService.addGroupCompany(null);
        });
    }

    @Test
    public void shouldThrowDataConflictExceptionWhenGroupCompanyAlreadyExists() {
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setCurrency("INR");
        currencyDTO.setDescription("Rupee");

        StateDTO stateDTO = new StateDTO();
        stateDTO.setName("TN");

        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setName("India");
        countryDTO.setCountryCode("IND");
        countryDTO.setDescription("India");
        countryDTO.setStates(Collections.singletonList(stateDTO));

        GroupCompanyDTO groupCompanyDTO = new GroupCompanyDTO();
        groupCompanyDTO.setId(1L);
        groupCompanyDTO.setGcCode("GC01");
        groupCompanyDTO.setGcName("GC");
        groupCompanyDTO.setCurrency(currencyDTO);
        groupCompanyDTO.setCountry(countryDTO);

        GroupCompany groupCompany = modelMapper.map(groupCompanyDTO, GroupCompany.class);
        dataService.addGroupCompany(groupCompanyDTO);
        when(groupCompanyRepository.findByGcCodeAndGcName(groupCompanyDTO.getGcCode(), groupCompanyDTO.getGcName())).thenReturn(groupCompany);

        assertThrows(DataConflictException.class, () -> {
            dataService.addGroupCompany(groupCompanyDTO);
        });
    }

    @Test
    public void shouldReturnAllActiveGroupCompanies() {
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setCurrency("INR");
        currencyDTO.setDescription("Rupee");

        StateDTO stateDTO = new StateDTO();
        stateDTO.setName("TN");

        CountryDTO countryDTO = new CountryDTO();
        countryDTO.setName("India");
        countryDTO.setCountryCode("IND");
        countryDTO.setDescription("India");
        countryDTO.setStates(Collections.singletonList(stateDTO));

        GroupCompanyDTO groupCompanyDTO = new GroupCompanyDTO();
        groupCompanyDTO.setId(1L);
        groupCompanyDTO.setGcCode("GC01");
        groupCompanyDTO.setGcName("GC");
        groupCompanyDTO.setCurrency(currencyDTO);
        groupCompanyDTO.setCountry(countryDTO);

        GroupCompany groupCompany = modelMapper.map(groupCompanyDTO, GroupCompany.class);
        when(groupCompanyRepository.findByIsActiveAndIsDeleted(true, false)).thenReturn(Collections.singletonList(groupCompany));

        List<GroupCompanyDTO> groupCompanyList = dataService.getAllGroupCompany(true, false);
        assertEquals(Collections.singletonList(groupCompanyDTO), groupCompanyList);
    }

}