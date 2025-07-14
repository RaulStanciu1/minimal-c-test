package io.github.raulstanciu1.services;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import io.github.raulstanciu1.models.results.TestResultsOverview;

import java.io.IOException;

public class ReportGenerator {
    public static void generateReportPdf(TestResultsOverview testResultsOverview) throws IOException {
        try(PdfWriter writer = new PdfWriter("mct_test_report.pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf)) {

            document.add(new Paragraph("MCT Test Report").setFontSize(20));

            //TODO: Add all the necessary details to the report

        }
    }
}
