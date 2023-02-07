package com.msciq.storage.repository;

import com.msciq.storage.common.entity.Country;
import com.msciq.storage.common.entity.Currency;
import com.msciq.storage.common.entity.GroupCompany;
import com.msciq.storage.common.entity.State;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupCompanyRepositoryTest {

    @Autowired
    GroupCompanyRepository groupCompanyRepository;

    private GroupCompany groupCompany = new GroupCompany();

    @Before
    public void setUp() {
        groupCompany = groupCompanyRepository.save(createGroupCompanyEntity("GC01"));
    }

    private GroupCompany createGroupCompanyEntity(String gcCode) {
        Currency currency = new Currency();
        currency.setCurrency("INR");
        currency.setDescription("Rupee");

        State state = new State();
        state.setName("TN");

        Country country = new Country();
        country.setCountryCode("CON01");
        country.setDescription("India");
        country.setName("India");
        country.setStates(Collections.singletonList(state));

        groupCompany.setGcName("GC");
        groupCompany.setGcCode(gcCode);
        groupCompany.setCurrency(currency);
        groupCompany.setCountry(country);

        return groupCompany;
    }

    @Test
    public void shouldFindGroupCompanyByIdAndIsDeletedFalse() {
        GroupCompany actual = groupCompanyRepository.findByIdAndIsDeleted(groupCompany.getId(), false);

        assertEquals(groupCompany.getGcCode(),actual.getGcCode());
    }

    @Test
    public void shouldReturnTrueIfGroupCompanyExists() {

        assertTrue(groupCompanyRepository.isGroupCompanyExists(groupCompany.getId(), "GC01"));
    }

    @Test
    public void shouldReturnFalseIfGroupCompanyDoesNotExists() {

        assertFalse(groupCompanyRepository.isGroupCompanyExists(groupCompany.getId(), "GC02"));
    }

    @Test
    public void shouldReturnGroupCompanyByGCCodeAndName() {
        GroupCompany actualGroupCompany = groupCompanyRepository.findByGcCodeAndGcName("GC01", "GC");

        assertEquals(groupCompany.getGcCode(), actualGroupCompany.getGcCode());
    }
}