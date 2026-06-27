package com.apps.pkador666.quality_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apps.pkador666.quality_api.model.EvaluationSection;

public interface EvaluationSectionRepository extends JpaRepository<EvaluationSection, Long> {
  List<EvaluationSection> findByEvaluationModelIdAndParentIsNull(Long evaluationModelId);

}
