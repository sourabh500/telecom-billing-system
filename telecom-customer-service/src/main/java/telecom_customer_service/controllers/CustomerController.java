package telecom_customer_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import telecom_customer_service.Entities.Customer;
import telecom_customer_service.services.customerImplementation;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final customerImplementation customerImplementation;

    @Autowired
    public CustomerController(customerImplementation customerImplementation) {
        this.customerImplementation = customerImplementation;
    }

    //Create customer api(Post)

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer)
    {
      Customer newCustomer=  customerImplementation.createCustomer(customer);
      return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }


    //Get customer by id (Get)
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id)
    {
       Customer customer= customerImplementation.getCustomerById(id);
       return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    //Get all customers(Get)
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers()
    {
      List<Customer> allCustomers=  customerImplementation.getAllCustomers();
      return new ResponseEntity<>(allCustomers,HttpStatus.OK);
    }


    //Update Customers by id(Put)

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer)
    {
      Customer updatedCustomer=  customerImplementation.updateCustomer(id, customer);
      return new ResponseEntity<>(updatedCustomer,HttpStatus.OK);
    }

    //Delete Customer(Delete)

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id)
    {
        customerImplementation.deleteCustomerById(id);
        return new ResponseEntity<>("Customer deleted successfully",HttpStatus.OK);
    }

}


