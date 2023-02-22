package financial.swap.challenge.client.github.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class AuthInterceptor implements ClientHttpRequestInterceptor {
    @Value("${custom.github.authorization}")
    private String authorization;

    @Override
    public @NonNull ClientHttpResponse intercept(HttpRequest request,
                                                 @NonNull byte[] body,
                                                 ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().set(AUTHORIZATION, authorization);
        return execution.execute(request, body);
    }
}
