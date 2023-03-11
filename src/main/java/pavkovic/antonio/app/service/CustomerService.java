package pavkovic.antonio.app.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pavkovic.antonio.app.exception.CustomerNotFoundException;
import pavkovic.antonio.app.model.Customer;
import pavkovic.antonio.app.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;


    public List<Customer> getAllCustomers(Integer pageNum, Integer pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(sortBy));

        Page<Customer> pagedResult = customerRepository.findAll(pageable);


        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }

    }


    public Customer getCustomerById(Long id) throws CustomerNotFoundException {

        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new CustomerNotFoundException("Employee with the id: " + id + " not found!");
        }
    }


    public Customer createCustomer(Customer customer) {

        Customer c = new Customer();

        customer.setFirstName(c.getFirstName());
        customer.setLastName(c.getLastName());
        customer.setEmail(c.getEmail());

        return customerRepository.save(customer);
    }


    public Customer updateCustomer(Long id, Customer customer) {

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer c = optionalCustomer.get();

            c.setFirstName(customer.getFirstName());
            c.setLastName(customer.getLastName());
            c.setEmail(customer.getEmail());

            return customerRepository.save(c);
        } else {
            throw new CustomerNotFoundException("Customer with id: " + id + " not found!");
        }
    }

    public boolean deleteCustomerById(Long id) throws CustomerNotFoundException {

        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isPresent()) {
            customerRepository.deleteById(id);
            return true;
        } else {
            throw new CustomerNotFoundException("Customer with id: " + id + " not found!");
        }

    }

}
