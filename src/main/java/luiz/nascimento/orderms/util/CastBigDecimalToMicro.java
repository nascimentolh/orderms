package luiz.nascimento.orderms.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CastBigDecimalToMicro {

    public static Long cast(BigDecimal value) {
        return value.multiply(BigDecimal.valueOf(1_000_000))
                .setScale(0, RoundingMode.HALF_UP)
                .longValue();
    }
}
