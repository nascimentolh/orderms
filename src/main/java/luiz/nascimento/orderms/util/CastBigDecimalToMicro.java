package luiz.nascimento.orderms.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CastBigDecimalToMicro {

    public static Long cast(BigDecimal value) {
        return value.multiply(BigDecimal.valueOf(1_000_000))
                .setScale(0, RoundingMode.HALF_UP)
                .longValue();
    }

    public static BigDecimal cast(Long micros) {
        return BigDecimal.valueOf(micros)
                .divide(BigDecimal.valueOf(1_000_000), 6, RoundingMode.HALF_UP);
    }
}
