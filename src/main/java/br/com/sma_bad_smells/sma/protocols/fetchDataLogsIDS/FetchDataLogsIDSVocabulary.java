package br.com.sma_bad_smells.sma.protocols.fetchDataLogsIDS;

import jade.domain.FIPANames;

public final class FetchDataLogsIDSVocabulary {
    private FetchDataLogsIDSVocabulary(){}

    public static final String PROTOCOL = FIPANames.InteractionProtocol.FIPA_REQUEST;

    public static final String RESPONDER_NAME = "persistenceAgent";

    public static final String ACTION_FETCH = "fetch-logs-ids-data";
}
