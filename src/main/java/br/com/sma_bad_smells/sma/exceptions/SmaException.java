package br.com.sma_bad_smells.sma.exceptions;

public class SmaException extends RuntimeException {
    public SmaException(String message) { super(message); }
    public SmaException(String message, Throwable cause) { super(message, cause); }
}