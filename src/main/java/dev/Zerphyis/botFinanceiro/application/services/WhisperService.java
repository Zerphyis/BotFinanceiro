package dev.Zerphyis.botFinanceiro.application.services;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Service
public class WhisperService {
    @Value("${openai.api.key}")
    private String openaiApiKey;

    public String transcrever(File audioFile) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost("https://api.openai.com/v1/audio/transcriptions");

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", audioFile, ContentType.DEFAULT_BINARY, audioFile.getName());
            builder.addTextBody("model", "whisper-1");
            builder.setCharset(StandardCharsets.UTF_8);

            request.setHeader("Authorization", "Bearer " + openaiApiKey);
            request.setEntity(builder.build());

            return httpClient.execute(request, response -> {
                Scanner scanner = new Scanner(response.getEntity().getContent()).useDelimiter("\\A");
                return scanner.hasNext() ? scanner.next() : "";
            });
        }
    }
}
