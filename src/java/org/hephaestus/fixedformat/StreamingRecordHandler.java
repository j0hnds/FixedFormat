package org.hephaestus.fixedformat;

public interface StreamingRecordHandler
{
    void handleRecord(String recordId, String record);
}
