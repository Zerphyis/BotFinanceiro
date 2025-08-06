package dev.Zerphyis.botFinanceiro.application.services;

import dev.Zerphyis.botFinanceiro.infra.exceptions.TranscricaoException;
import org.springframework.stereotype.Service;
import org.vosk.Model;
import org.vosk.Recognizer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

@Service
public class WhisperService {

    public String transcrever(File audioFile){
        try (Model model = new Model("models/pt-br")) {
            try (AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile)) {
                AudioFormat format = ais.getFormat();

                if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                    throw new TranscricaoException("Formato de áudio inválido. Use WAV PCM 16-bit mono.");
                }

                Recognizer recognizer = new Recognizer(model, format.getSampleRate());
                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = ais.read(buffer)) >= 0 && recognizer.acceptWaveForm(buffer, bytesRead)) {
                }

                return recognizer.getFinalResult();

            } catch (UnsupportedAudioFileException | IOException e) {
                throw new TranscricaoException("Erro ao ler o arquivo de áudio", e);
            }
        } catch (IOException e) {
            throw new TranscricaoException("Erro ao carregar o modelo de transcrição", e);
        }
    }
}

