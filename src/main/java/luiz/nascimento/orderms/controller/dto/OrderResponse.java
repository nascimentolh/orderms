package luiz.nascimento.orderms.controller.dto;

import java.math.BigDecimal;

import luiz.nascimento.orderms.entity.OrderEntity;
import luiz.nascimento.orderms.util.CastBigDecimalToMicro;

public record OrderResponse(Long orderId, Long customerId, BigDecimal total) {
    public static OrderResponse fromEntity(OrderEntity entity) {
        return new OrderResponse(entity.getOrderId(), entity.getCustomerId(),
                CastBigDecimalToMicro.cast(entity.getTotal()));
    }
}
