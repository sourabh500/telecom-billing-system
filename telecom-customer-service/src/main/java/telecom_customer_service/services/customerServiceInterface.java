package telecom_customer_service.services;

import telecom_customer_service.Entities.Customer;

import java.util.List;

public interface customerServiceInterface {

    Customer createCustomer(Customer customer);
    Customer getCustomerById(Long id);
    List<Customer> getAllCustomers();
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomerById(Long id);

}
