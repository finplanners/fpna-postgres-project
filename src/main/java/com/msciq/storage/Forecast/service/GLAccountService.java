package com.msciq.storage.forecast.service;

import com.msciq.storage.model.GLAccount;
import com.msciq.storage.model.request.BudgetCategoryGLAccountMappingDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.List;

public interface GLAccountService {

    /**
     * This method returns list of all forecasting templates
     *
     * @return List of GL Account Entity
     * @author mkseenu
     */
    public List<GLAccount> getAllGLAccountData();

    /**
     * This method adds a symptom
     *
     * @param file
     * @return List of GL Account Entity
     * @author mkseenu
     */
    public List<GLAccount> importGLAccountData(MultipartFile file) throws IOException;

    List<GLAccount> mapBudgetCategoryToGLAccount(List<BudgetCategoryGLAccountMappingDTO> budgetCategoryGLAccountMapping);
}
