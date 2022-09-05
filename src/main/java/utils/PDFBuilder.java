package utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Tariff;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.time.LocalDate;

public class PDFBuilder {
    public static void tariffPDF(HttpServletResponse response, Tariff tariff) {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            BaseFont arial = BaseFont.createFont("C:\\Windows\\Fonts\\arial.ttf", "cp1251", BaseFont.EMBEDDED);

            String name = "Тариф <" + tariff.getName() + ">";
            Paragraph title = new Paragraph(name, new Font(arial, 16));
            document.add(title);

            PdfPTable t = new PdfPTable(3);
            t.setSpacingBefore(25);
            t.setSpacingAfter(25);

            PdfPCell c1 = new PdfPCell(new Phrase("Тариф", new Font(arial, 12)));
            t.addCell(c1);

            PdfPCell c2 = new PdfPCell(new Phrase("Ціна", new Font(arial, 12)));
            t.addCell(c2);

            PdfPCell c3 = new PdfPCell(new Phrase("Опис", new Font(arial, 12)));
            t.addCell(c3);

            t.addCell(new Phrase(tariff.getName(), new Font(arial, 12)));
            t.addCell(new Phrase(String.valueOf(tariff.getPrice()), new Font(arial, 12)));
            t.addCell(new Phrase(tariff.getDescription(), new Font(arial, 12)));

            document.add(t);

            Paragraph footer = new Paragraph(String.valueOf(LocalDate.now()), new Font(arial, 10));
            document.add(footer);
            document.close();

            openInBrowser(response, baos);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }


    private static void openInBrowser(HttpServletResponse response, ByteArrayOutputStream baos) {
        response.setContentType("application/pdf");
        response.setContentLength(baos.size());
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}