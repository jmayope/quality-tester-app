package com.apps.pkador666.quality_api.service;

import com.apps.pkador666.quality_api.repository.EvaluationModelRepository;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.util.stream.Stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.dto.request.EvaluationModelRequest;
import com.apps.pkador666.quality_api.dto.response.EvaluationMetricResponse;
import com.apps.pkador666.quality_api.dto.response.EvaluationModelResponse;
import com.apps.pkador666.quality_api.dto.response.EvaluationSectionResponse;
import com.apps.pkador666.quality_api.model.EvaluationModel;

@Service
public class EvaluationModelService {
  private final EvaluationModelRepository evaluationModelRepository;
  private final EvaluationSectionService evaluationSectionService;
  private final EvaluationMetricService evaluationMetricService;

  public EvaluationModelService(
    EvaluationModelRepository evaluationModelRepository,
    EvaluationSectionService evaluationSectionService,
    EvaluationMetricService evaluationMetricService
  ) {
    this.evaluationModelRepository = evaluationModelRepository;
    this.evaluationSectionService = evaluationSectionService;
    this.evaluationMetricService = evaluationMetricService;
  }

  public List<EvaluationModelResponse> findAll() {
    List<EvaluationModel> evaluations = evaluationModelRepository.findAll();
    List<EvaluationModelResponse> response = new ArrayList<EvaluationModelResponse>();
    evaluations.forEach(e -> {
      EvaluationModelResponse evaluation = new EvaluationModelResponse();
      evaluation.setId(Optional.of(e.getId()));
      evaluation.setAbbr(e.getAbbr());
      evaluation.setCode(e.getCode());
      evaluation.setName(e.getName());
      evaluation.setStatus(Optional.of(e.getStatus()));
      evaluation.setCreatedAt(Optional.of(e.getCreatedAt()));
      evaluation.setDescription(e.getDescription());
      List<EvaluationSectionResponse> sections = evaluationSectionService.findByEvaluationModelId(e.getId());
      evaluation.setSections(sections);
      List<EvaluationMetricResponse> metrics = evaluationMetricService.findByEvaluationModelIdEntity(e.getId());
      evaluation.setMetrics(metrics);
      response.add(evaluation);
    });

    return response;
  }

  public Optional<EvaluationModel> findById(Long id) {
    return evaluationModelRepository.findById(id);
  }

  public EvaluationModel create(EvaluationModelRequest evaluationModel) {

    EvaluationModel evaluationModelCreated = new EvaluationModel();

    evaluationModelCreated.setCode(evaluationModel.getCode());
    evaluationModelCreated.setAbbr(evaluationModel.getAbbr());
    evaluationModelCreated.setName(evaluationModel.getName());
    evaluationModelCreated.setDescription(evaluationModel.getDescription());
    evaluationModelCreated.setStatus(evaluationModel.getStatus().get() == null ? true : evaluationModel.getStatus().get());

    return evaluationModelRepository.save(evaluationModelCreated);
  }

  public EvaluationModel update(EvaluationModelRequest evaluationModel) {
    Optional<EvaluationModel> evaluationModelUpdated = evaluationModelRepository.findById(evaluationModel.getId().get());
    evaluationModelUpdated.get().setCode(evaluationModel.getCode());
    evaluationModelUpdated.get().setAbbr(evaluationModel.getAbbr());
    evaluationModelUpdated.get().setName(evaluationModel.getName());
    evaluationModelUpdated.get().setDescription(evaluationModel.getDescription());
    evaluationModelUpdated.get().setStatus(evaluationModel.getStatus().get() == null ? true : evaluationModel.getStatus().get());
    return evaluationModelRepository.save(evaluationModelUpdated.get());
  }

  public ByteArrayInputStream generateReport() {
    List<EvaluationModel> models = evaluationModelRepository.findAll();
    Document document = new Document(PageSize.A4);
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    try {
      PdfWriter.getInstance(document, out);
      document.open();
      
      // Título
      Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
      Paragraph titulo = new Paragraph("Reporte de Módelos de Evaluación", tituloFont);
      titulo.setSpacingAfter(15);
      document.add(titulo);

      // Tabla
      PdfPTable table = new PdfPTable(4); // 4 columnas
      table.setWidthPercentage(100);
      table.setWidths(new float[]{1f, 3f, 4f, 2f});
      
      Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, Color.WHITE);
      Stream.of("ID","Abrv", "Nombre", "Descripción").forEach(col -> {
        PdfPCell header = new PdfPCell(new Phrase(col, headerFont));
        header.setBackgroundColor(new Color(22, 33, 62)); // navy
        header.setPadding(6);
        table.addCell(header);
      });
      
      Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 9);

      for (EvaluationModel e : models) {
        table.addCell(new PdfPCell(new Phrase(String.valueOf(e.getId()), bodyFont)));
        table.addCell(new PdfPCell(new Phrase(e.getAbbr().toUpperCase(), bodyFont)));
        table.addCell(new PdfPCell(new Phrase("%s - %s".formatted(e.getCode(), e.getName()).toUpperCase(), bodyFont)));
        table.addCell(new PdfPCell(new Phrase(e.getDescription().toUpperCase(), bodyFont)));
      }

      document.add(table);
      document.close();

    } catch (Exception e) {
      throw new RuntimeException("Error al generar el PDF", e);
    }

    return new ByteArrayInputStream(out.toByteArray());
  }

}
