package com.jonathastelesoliviera.bffagendadortarefas.infraestruture.exceptions;



public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
    public UnauthorizedException(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
}
