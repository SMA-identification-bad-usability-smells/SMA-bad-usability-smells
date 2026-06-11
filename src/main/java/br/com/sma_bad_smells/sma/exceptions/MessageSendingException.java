package br.com.sma_bad_smells.sma.exceptions;

public class MessageSendingException extends SmaException {
    public MessageSendingException(String message, Throwable cause) {
        super(message, cause);
    }
}