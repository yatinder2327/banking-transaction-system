package com.example.banking_transaction_system.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.banking_transaction_system.dto.CreateCustomerRequest;
import com.example.banking_transaction_system.dto.CustomerResponse;
import com.example.banking_transaction_system.entity.Customer;
import com.example.banking_transaction_system.repository.CustomerRepository;

@Service 
public class CustomerService {
    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional 
    public CustomerResponse createCustomer(CreateCustomerRequest request){
        if(customerRepository.existsByEmail(request.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already Registered");
        }
        if(customerRepository.existsByPhone(request.phone())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already Registered");
        }
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setPhone(request.phone());
        Customer savedCustomer = customerRepository.save(customer);
        return mapToCustomerResponse(savedCustomer);
    }

    @Transactional(readOnly = true)
    public CustomerResponse getCustomerById(Long customerId){
        Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
        return mapToCustomerResponse(customer);
    }

    @Transactional (readOnly = true)
    public List<CustomerResponse> getAllCustomers(){
        return customerRepository.findAll().stream().map(this::mapToCustomerResponse).toList();
    }

    private CustomerResponse mapToCustomerResponse(Customer customer){
        return new CustomerResponse(
            customer.getCustomerId(),
            customer.getName(),
            customer.getEmail(),
            customer.getPhone(),
            customer.getCreatedAt()
        );
    }
}
        
