package pavkovic.antonio.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pavkovic.antonio.app.exception.CustomerNotFoundException;
import pavkovic.antonio.app.model.Customer;
import pavkovic.antonio.app.service.CustomerService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private CustomerService customerService;


    @GetMapping()
    public ResponseEntity<List<Customer>> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        List<Customer> customers = customerService.getAllCustomers(pageNum, pageSize, sortBy);

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) throws CustomerNotFoundException {

        Customer customer = customerService.getCustomerById(id);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {


        System.out.println("Customer: " + customer);

        Customer c = customerService.createCustomer(customer);

        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) throws CustomerNotFoundException {

        Customer c = customerService.updateCustomer(id, customer);

        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteCustomerById(@PathVariable("id") Long id) throws CustomerNotFoundException {

        boolean isDeleted = customerService.deleteCustomerById(id);

        if (!isDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
    }

}
