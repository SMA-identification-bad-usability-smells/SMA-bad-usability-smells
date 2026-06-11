package br.com.sma_bad_smells.sma.models;

public class LogsIdsRequest {
    private String listLogsIds;

    public LogsIdsRequest(String listLogsIds){
        this.listLogsIds = listLogsIds;
    }

    public String getListLogsIds() {
        return listLogsIds;
    }

    public void setListLogsIds(String listLogsIds) {
        this.listLogsIds = listLogsIds;
    }
}
