package Billing.service.Billing.service.Service;

import Billing.service.Billing.service.Entity.Bill;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class PdfGenerator {


    public static ByteArrayInputStream generateBillPdf(Bill bill)
    {
        Document document=new Document();
        ByteArrayOutputStream outputStream= new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document,outputStream);
            document.open();

            Font titleFont= FontFactory.getFont(FontFactory.HELVETICA_BOLD,18);
            Paragraph title= new Paragraph("Telecom Bill",titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Bill Id: "+ bill.getId()));
            document.add(new Paragraph("Customer Id: "+bill.getCustomerId()));
            document.add(new Paragraph("Amount: "+bill.getAmount()));
            document.add(new Paragraph("Billing Date: "+bill.getGeneratedDate()));
            document.add(new Paragraph("Plan Details: "+bill.getCallMinutes()));

            document.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

}
