package pavkovic.antonio.app.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pavkovic.antonio.app.model.Order;
import pavkovic.antonio.app.repository.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;


    public List<Order> getAllOrders() {

        return orderRepository.findAll();

    }

}
