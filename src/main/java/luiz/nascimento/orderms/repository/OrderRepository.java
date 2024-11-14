package luiz.nascimento.orderms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import luiz.nascimento.orderms.entity.OrderEntity;

public interface OrderRepository extends MongoRepository<OrderEntity, Long> {

}
