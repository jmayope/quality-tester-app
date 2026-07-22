package com.apps.pkador666.quality_api.service;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.stereotype.Service;

import com.apps.pkador666.quality_api.dto.request.EvaluationResultRequest;
import com.apps.pkador666.quality_api.model.EvaluableElement;
import com.apps.pkador666.quality_api.model.EvaluationModel;
import com.apps.pkador666.quality_api.model.EvaluationResult;
import com.apps.pkador666.quality_api.model.EvaluationResultDetail;
import com.apps.pkador666.quality_api.model.User;
import com.apps.pkador666.quality_api.repository.EvaluationResultRepository;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

// iText / OpenPDF (usa el mismo paquete que ya usas para Document, PdfWriter, etc.)
import com.lowagie.text.Image;

@Service
public class EvaluationResultService {
  
  private final EvaluationResultRepository evaluationResultRepository;
  private final EvaluationModelService evaluationModelService;
  private final EvaluationResultDetailService evaluationResultDetailService;
  private final UserService userService;
  private final EvaluableElementService evaluableElementService;

  public EvaluationResultService(
    EvaluationResultRepository evaluationResultRepository,
    EvaluationModelService evaluationModelService,
    UserService userService,
    EvaluableElementService evaluableElementService,
    EvaluationResultDetailService evaluationResultDetailService
  ) {
    this.evaluationResultRepository = evaluationResultRepository;
    this.evaluationModelService = evaluationModelService;
    this.userService = userService;
    this.evaluableElementService = evaluableElementService;
    this.evaluationResultDetailService = evaluationResultDetailService;
  }

  public List<EvaluationResult> findAll() {
    return evaluationResultRepository.findAll();
  }

  public Optional<EvaluationResult> findById(Long id) {
    return evaluationResultRepository.findAll().stream().filter(e -> e.getId().equals(id)).findFirst();
  }

  public EvaluationResult create(EvaluationResultRequest evaluationResult) {
    Optional<EvaluationModel> evaluationModel = evaluationModelService.findById(evaluationResult.getEvaluationModelId());
    Optional<User> user = userService.findById(evaluationResult.getUserId());
    Optional<EvaluableElement> evaluableElement = evaluableElementService.findById(evaluationResult.getEvaluableElementId());

    EvaluationResult newEvaluationResult = new EvaluationResult();
    newEvaluationResult.setEvaluationModel(evaluationModel.get());
    newEvaluationResult.setUser(user.get());
    newEvaluationResult.setEvaluableElement(evaluableElement.get());
    newEvaluationResult.setState(evaluationResult.getState());

    return evaluationResultRepository.save(newEvaluationResult);
  }

  public EvaluationResult update(Long id, EvaluationResultRequest evaluationResult) {
    Optional<EvaluationResult> evaluationToUpdate = evaluationResultRepository.findById(id);
    if (!evaluationToUpdate.isPresent()) {
      return null;
    }
    EvaluationResult evaluation = evaluationToUpdate.get();
    evaluation.setState(evaluationResult.getState());
    return evaluationResultRepository.save(evaluation);
  }

  public ByteArrayInputStream generateStatsReport(Long evaluationResultId) {
    
    List<EvaluationResultDetail> questions = evaluationResultDetailService.findByEvaluationResultId(evaluationResultId);

    Document document = new Document(PageSize.A4);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {
        PdfWriter.getInstance(document, out);
        document.open();

        // Título general
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph title = new Paragraph("Reporte Estadístico de Respuestas", titleFont);
        title.setSpacingAfter(20);
        document.add(title);

        Font questionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);

        // for (Question q : questions) {
        //     // Título de la pregunta
        //     Paragraph preguntaTitulo = new Paragraph(q.getText(), questionFont);
        //     preguntaTitulo.setSpacingBefore(15);
        //     preguntaTitulo.setSpacingAfter(8);
        //     document.add(preguntaTitulo);

        //     // 1) Construir el dataset: respuesta -> cantidad de veces elegida
        //     Map<String, Long> conteo = q.getAnswers().stream()
        //         .collect(Collectors.groupingBy(Answer::getText, Collectors.counting()));

        //     DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //     conteo.forEach((respuesta, cantidad) -> dataset.addValue(cantidad, "Respuestas", respuesta));

        //     // 2) Crear el gráfico de barras con JFreeChart
        //     JFreeChart chart = ChartFactory.createBarChart(
        //         null,                 // sin título propio (ya pusimos el de la pregunta arriba)
        //         "Respuesta",          // eje X
        //         "Cantidad",           // eje Y
        //         dataset,
        //         PlotOrientation.VERTICAL,
        //         false,                // leyenda
        //         false,                // tooltips
        //         false                 // urls
        //     );
        //     chart.setBackgroundPaint(java.awt.Color.WHITE);

        //     CategoryPlot plot = chart.getCategoryPlot();
        //     plot.setBackgroundPaint(java.awt.Color.WHITE);
        //     plot.setOutlineVisible(false);
        //     plot.getRenderer().setSeriesPaint(0, new java.awt.Color(22, 33, 62)); // navy, igual que tu tabla

        //     // 3) Renderizar el gráfico como PNG en memoria
        //     ByteArrayOutputStream chartOut = new ByteArrayOutputStream();
        //     ChartUtils.writeChartAsPNG(chartOut, chart, 500, 280);

        //     // 4) Insertar la imagen en el PDF
        //     Image chartImage = Image.getInstance(chartOut.toByteArray());
        //     chartImage.setAlignment(Element.ALIGN_CENTER);
        //     chartImage.scaleToFit(500, 280);
        //     document.add(chartImage);
        // }

        document.close();
    } catch (Exception e) {
        throw new RuntimeException("Error al generar el PDF con gráficos", e);
    }
    return new ByteArrayInputStream(out.toByteArray());
}


}
