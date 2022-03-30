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
//        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        CloseableHttpResponse response = httpClient1.execute(request);

        System.out.println("*** getCode " + response.getStatusLine().getStatusCode() + " *** \n");


//        byte[] body = response.getEntity().getContent().readAllBytes();
//        for (byte b : body) {
//            System.out.print((char) b);
//        }
//        System.out.println("\n\n ***END***");

//        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
//        System.out.println(body);


        ObjectMapper mapper = new ObjectMapper();
        List<Cats> cats = mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {
        });
//        System.out.println(cats.get(1).getUser()+" ... "+cats.get(1).getText() +" ... "+cats.get(1).getUpvotes());
//        cats.forEach(Obj-> System.out.println(Obj.toString()));


        cats.stream()
                .filter(kitty -> kitty.getUpvotes() != 0 && kitty.getUpvotes() > 0)
                .forEach(cat -> System.out.println(cat.getUser() + "  голосов " + cat.getUpvotes()));


    }


}
