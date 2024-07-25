package br.com.alura.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import net.suuft.libretranslate.Language;
import net.suuft.libretranslate.Translator;

public class ConsultaLibreTranslate {
    public static String obterTraducao(String texto) {
        Translator.setUrlApi("https://libretranslate.de/translate");
        String resposta = Translator.translate(Language.ENGLISH, Language.PORTUGUESE, texto);
        System.out.println(resposta);
        return resposta;
    }
}

