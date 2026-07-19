package com.apps.pkador666.quality_api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.dto.request.EvaluationModelRequest;
import com.apps.pkador666.quality_api.dto.request.EvaluationSectionRequest;
import com.apps.pkador666.quality_api.dto.response.EvaluationSectionResponse;
import com.apps.pkador666.quality_api.model.EvaluationModel;
import com.apps.pkador666.quality_api.model.EvaluationSection;
import com.apps.pkador666.quality_api.repository.EvaluationModelRepository;
import com.apps.pkador666.quality_api.repository.EvaluationSectionRepository;

import jakarta.transaction.Transactional;

@Service
public class EvaluationSectionService {
  private final EvaluationSectionRepository evaluationSectionRepository;
  private final EvaluationModelRepository evaluationModelRepository;

  public EvaluationSectionService(
    EvaluationSectionRepository evaluationSectionRepository,
    EvaluationModelRepository evaluationModelRepository
  ) {
    this.evaluationSectionRepository = evaluationSectionRepository;
    this.evaluationModelRepository = evaluationModelRepository;
  }

  public List<EvaluationSection> findAll() {
    return evaluationSectionRepository.findAll();
  }

  public Optional<EvaluationSection> findById(Long id) {
    return evaluationSectionRepository.findById(id);
  }

  public EvaluationSection create(EvaluationSectionRequest evaluationSection) {
    EvaluationSection newEvaluationSection = new EvaluationSection();
    newEvaluationSection.setName(evaluationSection.getName());
    newEvaluationSection.setDescription(evaluationSection.getDescription());
    newEvaluationSection.setSectionOrder(evaluationSection.getSectionOrder());
    Optional<EvaluationModel> evaluationModel = evaluationModelRepository.findById(evaluationSection.getEvaluationModelId());
    newEvaluationSection.setEvaluationModel(evaluationModel.get());
    if (evaluationSection.getParent() != null) {
      Optional<EvaluationSection> parent = evaluationSectionRepository.findById(evaluationSection.getParent().get());
      newEvaluationSection.setParent(parent.get());
    }
    newEvaluationSection.setStatus(evaluationSection.getStatus() == null ? true : evaluationSection.getStatus().get());
    return evaluationSectionRepository.save(newEvaluationSection);
  }

  @Transactional
    public void saveSections(
            EvaluationModel evaluation,
            List<EvaluationSectionRequest> requests) {

        for (EvaluationSectionRequest dto : requests) {

            EvaluationSection section = buildEntity(
                    dto,
                    evaluation,
                    null
            );
            evaluationSectionRepository.save(section);
        }

    }

    /**
     * Construye toda la jerarquía en memoria.
     */
    private EvaluationSection buildEntity(
            EvaluationSectionRequest dto,
            EvaluationModel evaluation,
            EvaluationSection parent) {

        EvaluationSection entity = new EvaluationSection();

        entity.setEvaluationModel(evaluation);
        entity.setParent(parent);

        entity.setSectionOrder(dto.getSectionOrder());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        if (dto.getSections() != null) {

            for (EvaluationSectionRequest childDto : dto.getSections()) {

                EvaluationSection child = buildEntity(
                        childDto,
                        evaluation,
                        entity
                );

                entity.getSections().add(child);

            }

        }

        return entity;

    }

  public List<EvaluationSectionResponse> findByEvaluationModelId(Long evaluationModelId) {

    List<EvaluationSection> roots =
            evaluationSectionRepository
                    .findByEvaluationModelIdAndParentIsNull(evaluationModelId);

    return roots.stream()
            .map(this::toResponse)
            .toList();
  }

  private EvaluationSectionResponse toResponse(EvaluationSection entity) {

    EvaluationSectionResponse response = new EvaluationSectionResponse();

    response.setId(Optional.of(entity.getId()));
    response.setName(entity.getName());
    response.setEvaluationModelId(entity.getEvaluationModel().getId());
    response.setDescription(entity.getDescription());
    response.setParent(entity.getParent() == null ? null : entity.getParent().getId());
    response.setSectionOrder(entity.getSectionOrder());
    response.setStatus(Optional.of(entity.getStatus() == null ? true : false));

    if (entity.getSections() != null) {

        List<EvaluationSectionResponse> children = entity.getSections()
                .stream()
                .map(this::toResponse)
                .toList();

        response.setSections(children);

    }

    return response;

}

  // public List<EvaluationSection> createMany(List<EvaluationSection> evaluationSections) {
  //   return evaluationSectionRepository.saveAll(evaluationSections);
  // }

  // public EvaluationSection create(EvaluationSectionRequest evaluationSection) {
  //   EvaluationSection evaluationSectionCreated = new EvaluationSection();
  //   Optional<EvaluationModel> evaluationModel = evaluationModelRepository.findById(evaluationSection.getEvaluationModelId());
    
  //   evaluationSectionCreated.setEvaluationModel(evaluationModel.get());
  //   evaluationSectionCreated.setParent(evaluationSection.getParent());
  //   evaluationSectionCreated.setName(evaluationSection.getName());
  //   evaluationSectionCreated.setSectionOrder(evaluationSection.getSectionOrder());
  //   evaluationSectionCreated.setDescription(evaluationSection.getDescription());
  //   evaluationSectionCreated.setStatus(evaluationSection.getStatus().get() == null ? true : evaluationSection.getStatus().get());
  //   return evaluationSectionRepository.save(evaluationSectionCreated);
  // }
}
