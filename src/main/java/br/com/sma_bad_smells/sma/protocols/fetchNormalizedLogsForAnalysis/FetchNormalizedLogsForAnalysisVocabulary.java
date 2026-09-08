package br.com.sma_bad_smells.sma.protocols.fetchNormalizedLogsForAnalysis;

public final class FetchNormalizedLogsForAnalysisVocabulary {
    private FetchNormalizedLogsForAnalysisVocabulary(){}

    // Protocolo próprio (não reaproveita FIPANames.InteractionProtocol.FIPA_REQUEST):
    // o TranslateAgent já tem um AchieveREResponder para o PersistenceAgent nesse mesmo
    // formato de conversa; usar o mesmo protocolo genérico faria os dois responders do
    // TranslateAgent competirem pela mesma mensagem, já que o template só olha
    // performativa + protocolo (não o conversationId).
    public static final String PROTOCOL = "fetch-normalized-logs-for-analysis";

    public static final String RESPONDER_NAME = "translateAgent";

}
