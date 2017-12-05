package website.service.auth;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.*;

import java.net.URI;
import java.util.Map;
import java.util.Set;

public class AuthenticatedRestTemplate implements RestOperations
{
    private RestOperations restOperations;

    public AuthenticatedRestTemplate(final RestOperations restOperations)
    {
        this.restOperations = restOperations;
    }

    private static URI appendLoginToken(final URI uri)
    {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null)
        {
            return uri;
        }
        else
        {
            return URI.create(appendLoginToken(uri.toString()));
        }
    }

    private static String appendLoginToken(final String uri)
    {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null)
        {
            return uri;
        }
        else
        {
            final StringBuilder uriWithToken = new StringBuilder(uri);
            uriWithToken.append(uri.contains("?") ? '&' : '?');
            uriWithToken.append("t="); //todo externalise
            uriWithToken.append(auth.getName()); //todo change to token
            return uriWithToken.toString();
        }

    }

    @Override
    public <T> T getForObject(final String url, final Class<T> responseType, final Object... uriVariables) throws RestClientException
    {
        return restOperations.getForObject(appendLoginToken(url), responseType, uriVariables);
    }

    @Override
    public <T> T getForObject(final String url, final Class<T> responseType, final Map<String, ?> uriVariables) throws RestClientException
    {
        return restOperations.getForObject(appendLoginToken(url), responseType, uriVariables);
    }

    @Override
    public <T> T getForObject(final URI url, final Class<T> responseType) throws RestClientException
    {
        return restOperations.getForObject(appendLoginToken(url), responseType);
    }

    @Override
    public <T> ResponseEntity<T> getForEntity(final String url, final Class<T> responseType, final Object... uriVariables) throws RestClientException
    {
        return restOperations.getForEntity(appendLoginToken(url), responseType, uriVariables);
    }

    @Override
    public <T> ResponseEntity<T> getForEntity(final String url, final Class<T> responseType, final Map<String, ?> uriVariables) throws RestClientException
    {
        return restOperations.getForEntity(appendLoginToken(url), responseType, uriVariables);
    }

    @Override
    public <T> ResponseEntity<T> getForEntity(final URI url, final Class<T> responseType) throws RestClientException
    {
        return restOperations.getForEntity(appendLoginToken(url), responseType);
    }

    @Override
    public HttpHeaders headForHeaders(final String url, final Object... uriVariables) throws RestClientException
    {
        return restOperations.headForHeaders(appendLoginToken(url), uriVariables);
    }

    @Override
    public HttpHeaders headForHeaders(final String url, final Map<String, ?> uriVariables) throws RestClientException
    {
        return restOperations.headForHeaders(appendLoginToken(url), uriVariables);
    }

    @Override
    public HttpHeaders headForHeaders(final URI url) throws RestClientException
    {
        return restOperations.headForHeaders(appendLoginToken(url));
    }

    @Override
    public URI postForLocation(final String url, final Object request, final Object... uriVariables) throws RestClientException
    {
        return restOperations.postForLocation(appendLoginToken(url), request, uriVariables);
    }

    @Override
    public URI postForLocation(final String url, final Object request, final Map<String, ?> uriVariables) throws RestClientException
    {
        return restOperations.postForLocation(appendLoginToken(url), request, uriVariables);
    }

    @Override
    public URI postForLocation(final URI url, final Object request) throws RestClientException
    {
        return restOperations.postForLocation(appendLoginToken(url), request);
    }

    @Override
    public <T> T postForObject(final String url, final Object request, final Class<T> responseType, final Object... uriVariables) throws RestClientException
    {
        return restOperations.postForObject(appendLoginToken(url), request, responseType, uriVariables);
    }

    @Override
    public <T> T postForObject(final String url, final Object request, final Class<T> responseType, final Map<String, ?> uriVariables) throws RestClientException
    {
        return restOperations.postForObject(appendLoginToken(url), request, responseType, uriVariables);
    }

    @Override
    public <T> T postForObject(final URI url, final Object request, final Class<T> responseType) throws RestClientException
    {
        return restOperations.postForObject(appendLoginToken(url), request, responseType);
    }

    @Override
    public <T> ResponseEntity<T> postForEntity(final String url, final Object request, final Class<T> responseType, final Object... uriVariables) throws RestClientException
    {
        return restOperations.postForEntity(appendLoginToken(url), request, responseType, uriVariables);
    }

    @Override
    public <T> ResponseEntity<T> postForEntity(final String url, final Object request, final Class<T> responseType, final Map<String, ?> uriVariables) throws RestClientException
    {
        return restOperations.postForEntity(appendLoginToken(url), request, responseType, uriVariables);
    }

    @Override
    public <T> ResponseEntity<T> postForEntity(final URI url, final Object request, final Class<T> responseType) throws RestClientException
    {
        return restOperations.postForEntity(appendLoginToken(url), request, responseType);
    }

    @Override
    public void put(final String url, final Object request, final Object... uriVariables) throws RestClientException
    {
        restOperations.put(appendLoginToken(url), request, uriVariables);
    }

    @Override
    public void put(final String url, final Object request, final Map<String, ?> uriVariables) throws RestClientException
    {
        restOperations.put(appendLoginToken(url), request, uriVariables);
    }

    @Override
    public void put(final URI url, final Object request) throws RestClientException
    {
        restOperations.put(appendLoginToken(url), request);
    }

    @Override
    public <T> T patchForObject(final String url, final Object request, final Class<T> responseType, final Object... uriVariables) throws RestClientException
    {
        return restOperations.patchForObject(appendLoginToken(url), request, responseType, uriVariables);
    }

    @Override
    public <T> T patchForObject(final String url, final Object request, final Class<T> responseType, final Map<String, ?> uriVariables) throws RestClientException
    {
        return restOperations.patchForObject(appendLoginToken(url), request, responseType, uriVariables);
    }

    @Override
    public <T> T patchForObject(final URI url, final Object request, final Class<T> responseType) throws RestClientException
    {
        return restOperations.patchForObject(appendLoginToken(url), request, responseType);
    }

    @Override
    public void delete(final String url, final Object... uriVariables) throws RestClientException
    {
        restOperations.delete(appendLoginToken(url), uriVariables);
    }

    @Override
    public void delete(final String url, final Map<String, ?> uriVariables) throws RestClientException
    {
        restOperations.delete(appendLoginToken(url), uriVariables);
    }

    @Override
    public void delete(final URI url) throws RestClientException
    {
        restOperations.delete(appendLoginToken(url));
    }

    @Override
    public Set<HttpMethod> optionsForAllow(final String url, final Object... uriVariables) throws RestClientException
    {
        return restOperations.optionsForAllow(appendLoginToken(url), uriVariables);
    }

    @Override
    public Set<HttpMethod> optionsForAllow(final String url, final Map<String, ?> uriVariables) throws RestClientException
    {
        return restOperations.optionsForAllow(appendLoginToken(url), uriVariables);
    }

    @Override
    public Set<HttpMethod> optionsForAllow(final URI url) throws RestClientException
    {
        return restOperations.optionsForAllow(appendLoginToken(url));
    }

    @Override
    public <T> ResponseEntity<T> exchange(final String url, final HttpMethod method, final HttpEntity<?> requestEntity, final Class<T> responseType, final Object... uriVariables) throws RestClientException
    {
        return restOperations.exchange(appendLoginToken(url), method, requestEntity, responseType, uriVariables);
    }

    @Override
    public <T> ResponseEntity<T> exchange(final String url, final HttpMethod method, final HttpEntity<?> requestEntity, final Class<T> responseType, final Map<String, ?> uriVariables) throws RestClientException
    {
        return restOperations.exchange(appendLoginToken(url), method, requestEntity, responseType, uriVariables);
    }

    @Override
    public <T> ResponseEntity<T> exchange(final URI url, final HttpMethod method, final HttpEntity<?> requestEntity, final Class<T> responseType) throws RestClientException
    {
        return restOperations.exchange(appendLoginToken(url), method, requestEntity, responseType);
    }

    @Override
    public <T> ResponseEntity<T> exchange(final String url, final HttpMethod method, final HttpEntity<?> requestEntity, final ParameterizedTypeReference<T> responseType, final Object... uriVariables) throws RestClientException
    {
        return restOperations.exchange(appendLoginToken(url), method, requestEntity, responseType, uriVariables);
    }

    @Override
    public <T> ResponseEntity<T> exchange(final String url, final HttpMethod method, final HttpEntity<?> requestEntity, final ParameterizedTypeReference<T> responseType, final Map<String, ?> uriVariables) throws RestClientException
    {
        return restOperations.exchange(appendLoginToken(url), method, requestEntity, responseType, uriVariables);
    }

    @Override
    public <T> ResponseEntity<T> exchange(final URI url, final HttpMethod method, final HttpEntity<?> requestEntity, final ParameterizedTypeReference<T> responseType) throws RestClientException
    {
        return restOperations.exchange(appendLoginToken(url), method, requestEntity, responseType);
    }

    @Override
    public <T> ResponseEntity<T> exchange(final RequestEntity<?> requestEntity, final Class<T> responseType) throws RestClientException
    {
        final RequestEntity<?> newRequest = new RequestEntity<>(
                requestEntity.getBody(),
                requestEntity.getHeaders(),
                requestEntity.getMethod(),
                appendLoginToken(requestEntity.getUrl()),
                requestEntity.getType());
        return restOperations.exchange(newRequest, responseType);
    }

    @Override
    public <T> ResponseEntity<T> exchange(final RequestEntity<?> requestEntity, final ParameterizedTypeReference<T> responseType) throws RestClientException
    {
        final RequestEntity<?> newRequest = new RequestEntity<>(
                requestEntity.getBody(),
                requestEntity.getHeaders(),
                requestEntity.getMethod(),
                appendLoginToken(requestEntity.getUrl()),
                requestEntity.getType());
        return restOperations.exchange(newRequest, responseType);
    }

    @Override
    public <T> T execute(final String url, final HttpMethod method, final RequestCallback requestCallback, final ResponseExtractor<T> responseExtractor, final Object... uriVariables) throws RestClientException
    {
        return restOperations.execute(appendLoginToken(url), method, requestCallback, responseExtractor, uriVariables);
    }

    @Override
    public <T> T execute(final String url, final HttpMethod method, final RequestCallback requestCallback, final ResponseExtractor<T> responseExtractor, final Map<String, ?> uriVariables) throws RestClientException
    {
        return restOperations.execute(appendLoginToken(url), method, requestCallback, responseExtractor, uriVariables);
    }

    @Override
    public <T> T execute(final URI url, final HttpMethod method, final RequestCallback requestCallback, final ResponseExtractor<T> responseExtractor) throws RestClientException
    {
        return restOperations.execute(appendLoginToken(url), method, requestCallback, responseExtractor);
    }
}
