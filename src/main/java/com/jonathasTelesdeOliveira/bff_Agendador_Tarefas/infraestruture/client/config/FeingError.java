package com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.infraestruture.client.config;

import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.infraestruture.exceptions.BusinesException;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.infraestruture.exceptions.ConflictException;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.infraestruture.exceptions.IllegalArgumentException;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.infraestruture.exceptions.ResourceNotFoundException;
import com.jonathasTelesdeOliveira.bff_Agendador_Tarefas.infraestruture.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeingError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
//        String message = String.format("Erro Feign [%s] - Status: %d", s, response.status());

        String mesagemErro = mesagemErro(response);

        switch (response.status()) {
            case 409:
                return new ConflictException("Error: " + mesagemErro);
            case 403:
                return new ResourceNotFoundException("Erro: "+ mesagemErro);
            case 400:
                return new IllegalArgumentException("Erro: "+ mesagemErro);
            case 401:
                return new UnauthorizedException("Erro: "+ mesagemErro);
            default:
                return new BusinesException("Erro: "+mesagemErro);
        }
    }

    private String mesagemErro(Response response) {

        try {
            if(Objects.isNull(response.body())) {
                return "";
            }
            return new String(
                            response.body()
                                    .asInputStream()
                                    .readAllBytes(), StandardCharsets.UTF_8
                    );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}


