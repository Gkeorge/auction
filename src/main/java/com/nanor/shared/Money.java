package com.nanor.shared;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Money {

    private BigDecimal value;

    public boolean isGreaterThan(Money money) {
        return this.value.compareTo(money.value) > 0;
    }

}
