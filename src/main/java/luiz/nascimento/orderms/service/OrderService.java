package luiz.nascimento.orderms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import luiz.nascimento.orderms.controller.dto.OrderResponse;
import luiz.nascimento.orderms.entity.mapper.OrderEntityMapper;
import luiz.nascimento.orderms.listener.dto.OrderCreatedEvent;
import luiz.nascimento.orderms.repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(OrderCreatedEvent event) {
        var entity = OrderEntityMapper.mapCreatedEventToEntity(event);
        this.orderRepository.save(entity);
    }

    public Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pageRequest) {
        var orders = orderRepository.findAllByCustomerId(customerId, pageRequest);

        return orders.map(OrderResponse::fromEntity);
    }
}
