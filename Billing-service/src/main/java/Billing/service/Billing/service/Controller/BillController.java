package Billing.service.Billing.service.Controller;
import Billing.service.Billing.service.Dtos.EmailDetails;
import Billing.service.Billing.service.Dtos.customerDto;
import Billing.service.Billing.service.Entity.Bill;
import Billing.service.Billing.service.External.customerClient;
import Billing.service.Billing.service.Service.EmailServiceImpl;
import Billing.service.Billing.service.Service.PdfGenerator;
import Billing.service.Billing.service.Service.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bills")
public class BillController {

    private final ServiceImpl service;
    private final EmailServiceImpl emailService;
    private final customerClient client;


    //Generating bill (post)
    @PostMapping
    public ResponseEntity<Bill> generateBill(@RequestBody Bill bill)
    {
       Bill generatedBill= service.generateBill(bill);
       return new ResponseEntity<>(generatedBill, HttpStatus.CREATED);
    }

    //Getting bill by customer id (Get)
    @GetMapping("customer/{customerId}")
    public ResponseEntity<List<Bill>> getBillsByCustomerId(@PathVariable Long customerId)
    {
       List<Bill> bills= service.getBillByCustomerId(customerId);
       return new ResponseEntity<>(bills,HttpStatus.OK);
    }

    //Getting bill by id (Get)
    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long id)
    {
        Bill bill=service.getBillById(id);
        return new ResponseEntity<>(bill,HttpStatus.OK);
    }

    //Updating Bill (Put)
    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long id, @RequestBody Bill bill)
    {
       Bill updatedBill= service.updateBill(id, bill);
       return new ResponseEntity<>(updatedBill,HttpStatus.OK);
    }

    //Delete bill(Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBill(@PathVariable Long id)
    {
        service.deleteBill(id);
        return new ResponseEntity<>("Bill deleted successfully",HttpStatus.OK);
    }

    //Generating the PDF
    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id)
    {
       Bill bill= service.getBillById(id);
       ByteArrayInputStream pdf = PdfGenerator.generateBillPdf(bill);

        // Convert the ByteArrayInputStream to byte[] (as required by ResponseEntity)
        byte[] pdfBytes;
        try {
            pdfBytes = pdf.readAllBytes(); // Java 9+ method
        } catch (Exception e) {
            // Handle exception if PDF generation fails
            throw new RuntimeException("Failed to read PDF data", e);
        }
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "bill_" + id + ".pdf");


        return new ResponseEntity<>(pdfBytes,headers,HttpStatus.OK);
    }

    //Sending email with Bill pdf attachment
    @PostMapping("/email/{id}")
    public ResponseEntity<String> sendBillByEmail(@PathVariable Long id) {
        try {
            // Fetch bill from DB
            Bill bill = service.getBillById(id);

            customerDto customer = client.getCustomerById(bill.getCustomerId());

            String email = customer.getEmail();
            if (email == null || email.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Customer does not have a valid email address.");
            }

            // Generate PDF from the bill entity
            try (ByteArrayInputStream pdfStream = PdfGenerator.generateBillPdf(bill)) {

                byte[] pdfBytes = pdfStream.readAllBytes();

                //Prepare email details
                EmailDetails emailDetails = new EmailDetails();
                emailDetails.setTo(email);
                emailDetails.setSubject("Your Bill from Telecom Service");
                emailDetails.setMessage("Dear Customer,<br><br>Please find attached your bill.<br><br>Regards,<br>Telecom Team");
                emailDetails.setAttachment(pdfBytes);
                emailDetails.setAttachmentName("bill_" + id + ".pdf");

                //send email
                String status = emailService.sendMailWithAttachment(emailDetails);
                return new ResponseEntity<>(status, HttpStatus.OK);
            }
            } catch (Exception e) {
                // Handle all other unexpected exceptions
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to send bill email: " + e.getMessage());
            }

        }
    }


