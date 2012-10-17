package org.agoncal.cdi.inject.producedispose;

import javax.enterprise.inject.Produces;

public class PostfixGenerator {

    @Produces
    @ThirteenDigits
    public int getIsbnPostfix() {
        return 1;
    }

    @Produces
    @EightDigits
    public int getIssnPostfix() {
        return 9;
    }
}
