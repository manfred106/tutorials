package com.baeldung.springai.rag.preretrieval;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.preretrieval.query.transformation.QueryTransformer;
import org.springframework.ai.rag.preretrieval.query.transformation.TranslationQueryTransformer;
import org.springframework.stereotype.Service;

@Service
public class QueryTranslationService {

    private static final Logger logger = LoggerFactory.getLogger(QueryTranslationService.class);
    private final QueryTransformer queryTransformer;

    public QueryTranslationService(ChatClient.Builder chatClientBuilder) {
        this.queryTransformer = TranslationQueryTransformer.builder()
            .chatClientBuilder(chatClientBuilder)
            .targetLanguage("english")
            .build();
    }

    public String translate(String userQuery) {
        Query transformed = queryTransformer.transform(new Query(userQuery));
        logger.info("Translation: [{}] -> [{}]", userQuery, transformed.text());
        return transformed.text();
    }
}
