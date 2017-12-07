package website.service.auth;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import website.service.BaseService;

@Service
public class LoginService extends BaseService
{
    public String login(final String business, final String username, final String password)
    {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        final MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("u", username);
        map.add("p", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        final ResponseEntity<String> response
                = this.getRestOperations().postForEntity(
                        "/business/" + business + "/login", request, String.class);
        return response.getBody();
    }
}
