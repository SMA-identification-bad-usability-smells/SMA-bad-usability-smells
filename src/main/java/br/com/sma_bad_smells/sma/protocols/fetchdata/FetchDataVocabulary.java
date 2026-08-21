package br.com.sma_bad_smells.sma.protocols.fetchdata;

import jade.domain.FIPANames;

public final class FetchDataVocabulary {
    private FetchDataVocabulary(){}

    public static final String PROTOCOL = FIPANames.InteractionProtocol.FIPA_REQUEST;

    public static final String RESPONDER_NAME = "dataAgent";

    public static final String ACTION_FETCH = "fetch-latest-data";
}
