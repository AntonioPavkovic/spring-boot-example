package pavkovic.antonio.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pavkovic.antonio.app.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
