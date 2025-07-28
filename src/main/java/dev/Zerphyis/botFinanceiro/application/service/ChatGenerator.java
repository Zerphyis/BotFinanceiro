package dev.Zerphyis.botFinanceiro.application.service;

import dev.Zerphyis.botFinanceiro.model.expense.Expense;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ChatGenerator {
    public static byte[] generateDailyChart(List<Expense> expenses) {
        Map<LocalDate, BigDecimal> map = new TreeMap<>();
        for (Expense e : expenses) {
            map.merge(e.getDate(), e.getAmount(), BigDecimal::add);
        }

        List<String> dias = map.keySet().stream().map(LocalDate::toString).collect(Collectors.toList());
        List<Double> valores = map.values().stream().map(BigDecimal::doubleValue).collect(Collectors.toList());

        CategoryChart chart = new CategoryChartBuilder()
                .width(600)
                .height(400)
                .title("Gastos por Dia")
                .xAxisTitle("Dia")
                .yAxisTitle("Valor")
                .build();

        chart.addSeries("Gastos", dias, valores);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            BitmapEncoder.saveBitmap(chart, out, BitmapEncoder.BitmapFormat.PNG);
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
