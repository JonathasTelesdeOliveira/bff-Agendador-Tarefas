package com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.infraestruture.exceptions;



public class BusinesException extends RuntimeException {
    public BusinesException(String message) {
        super(message);
    }
    public BusinesException(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
}
