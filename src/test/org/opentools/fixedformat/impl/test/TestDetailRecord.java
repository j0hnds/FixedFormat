package org.opentools.fixedformat.impl.test;

import java.math.BigDecimal;

public class TestDetailRecord extends TestRecordBase
{
    private String description;
    private BigDecimal amount;

    public BigDecimal getAmount()
    {
        return amount;
    }
    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

}
