package com.msciq.storage.controller;

import com.msciq.storage.common.SuccessMessage;
import com.msciq.storage.common.entity.Country;
import com.msciq.storage.common.entity.Currency;
import com.msciq.storage.common.entity.GroupCompany;
import com.msciq.storage.common.entity.State;
import com.msciq.storage.common.msciq.CountryDTO;
import com.msciq.storage.common.msciq.CurrencyDTO;
import com.msciq.storage.common.msciq.GroupCompanyDTO;
import com.msciq.storage.common.msciq.StateDTO;
import com.msciq.storage.model.response.SuccessResponse;
import com.msciq.storage.service.impl.DataServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataControllerTest {

    @InjectMocks
    DataController dataController;

    @Mock
    DataServiceImpl dataService;

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void shouldAddNewGroupCopmany() {
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

        when(dataService.addGroupCompany(groupCompanyDTO)).thenReturn(modelMapper.map(groupCompanyDTO, GroupCompany.class));
        SuccessResponse<GroupCompany> actualGroupCompanyResponse = dataController.addGroupCompany(groupCompanyDTO);
        GroupCompany response = (GroupCompany) ((SuccessMessage) actualGroupCompanyResponse.getBody()).getData();
        assertEquals(HttpStatus.CREATED, actualGroupCompanyResponse.getStatusCode());
        assertEquals(groupCompanyDTO.getGcCode(), response.getGcCode());
    }

    @Test
    public void shouldGetGroupCompanyById() {
        Currency currency = new Currency();
        currency.setCurrency("INR");
        currency.setDescription("Rupee");

        State state = new State();
        state.setName("TN");

        Country country = new Country();
        country.setName("India");
        country.setCountryCode("IND");
        country.setDescription("India");
        country.setStates(Collections.singletonList(state));

        GroupCompany groupCompany = new GroupCompany();
        groupCompany.setId(1L);
        groupCompany.setGcCode("GC01");
        groupCompany.setGcName("GC");
        groupCompany.setCurrency(currency);
        groupCompany.setCountry(country);

        when(dataService.findGroupCompanyById(1L)).thenReturn(groupCompany);
        SuccessResponse<GroupCompany> actualGroupCompanyResponse = dataController.getGroupCompany(1L);
        GroupCompany response = (GroupCompany) ((SuccessMessage) actualGroupCompanyResponse.getBody()).getData();
        assertEquals(HttpStatus.OK, actualGroupCompanyResponse.getStatusCode());
        assertEquals(groupCompany.getGcCode(), response.getGcCode());
    }
}