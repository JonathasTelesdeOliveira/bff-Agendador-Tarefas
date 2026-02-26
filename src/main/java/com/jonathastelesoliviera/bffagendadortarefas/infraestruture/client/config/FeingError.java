package com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.infraestruture.client.config;

import com.jonathastelesoliviera.bffagendadortarefas.infraestruture.exceptions.BusinesException;
import com.jonathastelesoliviera.bffagendadortarefas.infraestruture.exceptions.ConflictException;
import com.jonathastelesoliviera.bffagendadortarefas.infraestruture.exceptions.ResourceNotFoundException;
import com.jonathastelesoliviera.bffagendadortarefas.infraestruture.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeingError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        String message = String.format("Erro Feign [%s] - Status: %d", s, response.status());
        switch (response.status()) {
            case 409:
                return new ConflictException("Error atributo já existente " + message);
            case 403:
                return new ResourceNotFoundException("Erro de atributo não encontrado "+ message);
            case 401:
                return new UnauthorizedException("Erro usuário não autorizado "+ message);
            default:
                return new BusinesException("Erro de Servidor "+ message);
        }
    }

}


