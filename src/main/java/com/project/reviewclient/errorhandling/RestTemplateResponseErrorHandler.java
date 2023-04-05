package com.project.reviewclient.errorhandling;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        switch (response.getStatusCode().value()) {
            case 400 -> throw new BadRequestException(response.getStatusText());
            case 404 -> throw new NotFoundException(response.getStatusText());
        }
    }
}
