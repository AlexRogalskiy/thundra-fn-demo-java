package io.thundra.fn.demo.translate;

import io.thundra.agent.trace.instrument.config.Traceable;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @author serkan
 */
@Traceable(
        traceArguments = true,
        traceArgumentNames = true,
        serializeArgumentsAsJson = true,
        traceReturnValue = true,
        serializeReturnValueAsJson = true
)
public class TranslateService {

    private final Logger logger = Logger.getLogger(getClass());
    private final Map<String, Translator> translateMap = new HashMap<String, Translator>() {{
        put("EN-TR", new EnglishToTurkishTranslator());
        put("EN-ES", new EnglishToSpanishTranslator());
    }};

    public String translate(String toTranslate, String sourceLanguage, String destinationLanguage) {
        logger.info(String.format(
                "Translating '%s' from language '%s' to language '%s' ...",
                toTranslate, sourceLanguage, destinationLanguage));

        TranslateUtils.doProcess(20);

        String translatedTo;
        if (sourceLanguage.equalsIgnoreCase(destinationLanguage)) {
            translatedTo = toTranslate;
        } else {
            Translator translator =
                    translateMap.get(sourceLanguage.toUpperCase() + "-" + destinationLanguage.toUpperCase());
            if (translator != null) {
                translatedTo = translator.translate(toTranslate);
            } else {
                translatedTo = toTranslate;
            }
        }

        logger.info(String.format("Translated '%s' to '%s'", toTranslate, translatedTo));

        return translatedTo;
    }

    private interface Translator {

        String translate(String toTranslate);
    }

    @Traceable(
            traceArguments = true,
            traceArgumentNames = true,
            serializeArgumentsAsJson = true,
            traceReturnValue = true,
            serializeReturnValueAsJson = true
    )
    private static class EnglishToTurkishTranslator implements Translator {

        @Override
        public String translate(String toTranslate) {
            if ("Hello".equalsIgnoreCase(toTranslate)) {
                return "Merhaba";
            }
            return toTranslate;
        }
    }

    @Traceable(
            traceArguments = true,
            traceArgumentNames = true,
            serializeArgumentsAsJson = true,
            traceReturnValue = true,
            serializeReturnValueAsJson = true
    )
    private static class EnglishToSpanishTranslator implements Translator {

        @Override
        public String translate(String toTranslate) {
            if ("Hello".equalsIgnoreCase(toTranslate)) {
                return "Hola";
            }
            return toTranslate;
        }
    }

}
