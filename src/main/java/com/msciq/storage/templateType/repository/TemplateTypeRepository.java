package com.msciq.storage.templateType.repository;

import com.msciq.storage.model.TemplateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateTypeRepository extends JpaRepository<TemplateType, Long> {

}
