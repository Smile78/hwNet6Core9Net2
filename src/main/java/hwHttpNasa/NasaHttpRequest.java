package hwHttpNasa;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NasaHttpRequest {


    private static void downloadUsingStream3(String urlStr, String file) throws IOException{

        CloseableHttpClient client = clientKmo();

        HttpGet request = new HttpGet(urlStr);
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
//        int responseCode = response.getStatusLine().getStatusCode();
        InputStream inputStream = entity.getContent();

        String fileName = file;
        FileOutputStream fos = new FileOutputStream(fileName);
        int  byte1;
        while((byte1 = inputStream.read()) != -1) {
            fos.write(byte1);
        }
         //проверку бы что все ок
    }



    private static CloseableHttpClient clientKmo() {

        return HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();
    }


    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient1 = clientKmo();
        HttpGet request = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=tcwomQOixzzJ44pBIkrGnxhO3rooddaVxiG1Qhq9");
        CloseableHttpResponse response = httpClient1.execute(request);
        ObjectMapper mapper = new ObjectMapper();
        NasaApodSerializ nas1 = mapper.readValue(response.getEntity().getContent(), new TypeReference<NasaApodSerializ>() {
        });

//        String stringUrl = nas1.getUrl();                                                                     // щас в Nasa youtube
        String stringUrl = "https://apod.nasa.gov/apod/image/2007/DSC1028_PetersNEOWISEAuroralSpike_800.jpg";   // такчто качаем ваш джепег

        URL urlNasa = new URL(stringUrl);                             // щас  youtube - такчто буду пока делать на ваш jpg

        String filePath = urlNasa.toString();

        if (filePath.contains(".jpg")) {
            String nameFile = filePath.substring(filePath.lastIndexOf("/")+1);
            System.out.print ("качаем джепег "+nameFile);

            downloadUsingStream3(stringUrl,nameFile);

        } else if (filePath.contains("youtube.com")) {
            System.out.println("скачаем видосик c youtube");        //доработать бы чтоб качал видео
        }

    }

}
