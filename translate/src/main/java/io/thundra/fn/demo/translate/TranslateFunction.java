package io.thundra.fn.demo.translate;

import io.thundra.agent.fn.core.handler.FnContext;
import io.thundra.agent.fn.core.handler.request.FnRequestHandler;

/**
 * @author serkan
 */
public class TranslateFunction implements FnRequestHandler<TranslateFunction.TranslateRequest, String> {

    private final TranslateService translateService = new TranslateService();

    @Override
    public String handleRequest(TranslateRequest request) {
        return executeHandleRequest(request);
    }

    @Override
    public String doHandleRequest(TranslateRequest request, FnContext context) {
        return translateService.translate(
                request.toTranslate,
                request.sourceLanguage,
                request.destinationLanguage);
    }

    public static class TranslateRequest {

        private String toTranslate;
        private String sourceLanguage;
        private String destinationLanguage;

        public String getToTranslate() {
            return toTranslate;
        }

        public void setToTranslate(String toTranslate) {
            this.toTranslate = toTranslate;
        }

        public String getSourceLanguage() {
            return sourceLanguage;
        }

        public void setSourceLanguage(String sourceLanguage) {
            this.sourceLanguage = sourceLanguage;
        }

        public String getDestinationLanguage() {
            return destinationLanguage;
        }

        public void setDestinationLanguage(String destinationLanguage) {
            this.destinationLanguage = destinationLanguage;
        }

    }

}
