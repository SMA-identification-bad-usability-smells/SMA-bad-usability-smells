package br.com.sma_bad_smells.sma.protocols.fetchNormalizedLogs;

import jade.domain.FIPANames;

public final class FetchNormalizedLogsVocabulary {
    private FetchNormalizedLogsVocabulary(){}

    public static final String PROTOCOL = FIPANames.InteractionProtocol.FIPA_REQUEST;

    public static final String RESPONDER_NAME = "translateAgent";

}
