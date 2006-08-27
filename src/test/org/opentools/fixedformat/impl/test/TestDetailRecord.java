package org.opentools.fixedformat.impl.test;

import java.math.BigDecimal;
import java.util.Date;

public class TestDetailRecord extends TestRecordBase
{
    private String description;
    private BigDecimal amount;
    private Date date;
    private Date time;

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
    public Date getDate()
    {
        return date;
    }
    public void setDate(Date date)
    {
        this.date = date;
    }
    public Date getTime()
    {
        return time;
    }
    public void setTime(Date time)
    {
        this.time = time;
    }

}
