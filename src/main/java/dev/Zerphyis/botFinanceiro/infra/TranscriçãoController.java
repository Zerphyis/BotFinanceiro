package dev.Zerphyis.botFinanceiro.infra;

import dev.Zerphyis.botFinanceiro.application.services.WhisperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/audio")
public class TranscriçãoController {
    @Autowired
    private WhisperService whisperService;

    @PostMapping("/transcrever")
    public ResponseEntity<String> transcrever(@RequestParam("audio") MultipartFile file) throws IOException {
        File temp = File.createTempFile("audio", file.getOriginalFilename());
        file.transferTo(temp);

        String resultado = whisperService.transcrever(temp);

        temp.delete();

        return ResponseEntity.ok(resultado);
    }
}
