package br.com.sma_bad_smells.sma.domain.dto;

public class NormalizedLogsDTO {
    private String content;

    public NormalizedLogsDTO(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
