package br.com.sma_bad_smells.sma.domain.dto;

public class LogsIdsDTO {
    private String listLogsIds;

    public LogsIdsDTO(String listLogsIds){
        this.listLogsIds = listLogsIds;
    }

    public String getListLogsIds() {
        return listLogsIds;
    }

    public void setListLogsIds(String listLogsIds) {
        this.listLogsIds = listLogsIds;
    }
}
