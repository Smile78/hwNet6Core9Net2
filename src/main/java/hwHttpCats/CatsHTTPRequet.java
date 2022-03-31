package hwHttpCats;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;

public class CatsHTTPRequet {


    private static CloseableHttpClient kmoClient() {

        return HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();
    }


    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient1 = kmoClient();

        HttpGet request = new HttpGet(
                "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats");

        CloseableHttpResponse response = httpClient1.execute(request);

        System.out.println("*** getCode " + response.getStatusLine().getStatusCode() + " *** \n");

        ObjectMapper mapper = new ObjectMapper();
        List<Cats> cats = mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {
        });

        cats.stream()
                .filter(kitty -> kitty.getUpvotes() != null && kitty.getUpvotes() > 0)
                .forEach(cat -> System.out.println(cat.getUser() + "  голосов " + cat.getUpvotes()));

    }

}
