package handlers;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class EMSResponseErrorHandler implements ResponseErrorHandler 
{
    private static final Logger log = Logger.getLogger(EMSResponseErrorHandler.class);

    @Override
    public void handleError(ClientHttpResponse response) throws IOException 
    {
        log.error("Response error: " + response.getStatusCode() 
        	+ " : "+ response.getStatusText());
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException 
    {
        return (response.getStatusCode() != HttpStatus.BAD_REQUEST
        		&& response.getStatusCode() != HttpStatus.OK);
    }
}
