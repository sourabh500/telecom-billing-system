package telecom_customer_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telecom_customer_service.Entities.Customer;
import telecom_customer_service.Exceptions.ResourceNotFoundException;
import telecom_customer_service.repositories.customerRepo;

import java.util.List;

@Service
public class customerImplementation implements customerServiceInterface {

    // Encapsulation and final fields to avoid modification
    private final customerRepo customerRepo;

    // Constructor Injection - Best Practice
    @Autowired
    public customerImplementation(customerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    //Creating customer
    @Override
    public Customer createCustomer(Customer customer) {

        if (customerRepo.existsByEmail(customer.getEmail()))
        {
            throw new RuntimeException("Email already exists");
        }
        return customerRepo.save(customer);
    }

    //Getting customer by id
    @Override
    public Customer getCustomerById(Long id) {

       return customerRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Customer not found with given id :" +id));
    }

    //Getting all the customers
    @Override
    public List<Customer> getAllCustomers() {
       return customerRepo.findAll();
    }

    //Updating customer
    @Override
    public Customer updateCustomer(Long id, Customer updatedCustomer) {

        // Check if customer exists before updating
        Customer exisistingCustomer=  getCustomerById(id);
        exisistingCustomer.setName(updatedCustomer.getName());
        exisistingCustomer.setEmail(updatedCustomer.getEmail());
        exisistingCustomer.setMobile(updatedCustomer.getMobile());
        exisistingCustomer.setPassword(updatedCustomer.getPassword());
        exisistingCustomer.setAddress(updatedCustomer.getAddress());
       return customerRepo.save(exisistingCustomer);

    }

    //Deleting customer
    @Override
    public void deleteCustomerById(Long id) {
        // Try to find the customer; if not found, a ResourceNotFoundException will be thrown
        Customer customer = customerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id : " + id));

        // Delete the found customer
        customerRepo.delete(customer);
    }
}
