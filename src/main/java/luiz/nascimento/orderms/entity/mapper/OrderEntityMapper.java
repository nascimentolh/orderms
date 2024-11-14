package luiz.nascimento.orderms.entity.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import luiz.nascimento.orderms.entity.OrderEntity;
import luiz.nascimento.orderms.entity.OrderItem;
import luiz.nascimento.orderms.listener.dto.OrderCreatedEvent;
import luiz.nascimento.orderms.listener.dto.OrderItemEvent;
import luiz.nascimento.orderms.util.CastBigDecimalToMicro;

public class OrderEntityMapper {

    public static OrderEntity mapCreatedEventToEntity(OrderCreatedEvent event) {
        var entity = new OrderEntity();

        entity.setCustomerId(event.customerId());
        entity.setOrderId(event.orderId());
        entity.setItems(mapOrderItems(event));
        entity.setTotal(calculateTotal(event.items()));

        return entity;
    }

    private static List<OrderItem> mapOrderItems(OrderCreatedEvent event) {
        return event.items().stream()
                .map(item -> new OrderItem(item.product(), item.quantity(), CastBigDecimalToMicro.cast(item.price())))
                .collect(Collectors.toList());
    }

    private static Long calculateTotal(List<OrderItemEvent> items) {
        var total = items.stream()
                .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        return CastBigDecimalToMicro.cast(total);
    }
}
