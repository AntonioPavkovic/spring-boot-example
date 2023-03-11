package pavkovic.antonio.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pavkovic.antonio.app.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
