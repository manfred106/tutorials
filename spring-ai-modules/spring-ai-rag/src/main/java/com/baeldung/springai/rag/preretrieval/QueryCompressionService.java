package com.baeldung.springai.rag.preretrieval;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.preretrieval.query.transformation.CompressionQueryTransformer;
import org.springframework.ai.rag.preretrieval.query.transformation.QueryTransformer;
import org.springframework.stereotype.Service;

@Service
public class QueryCompressionService {

    private static final Logger logger = LoggerFactory.getLogger(QueryCompressionService.class);
    private final QueryTransformer queryTransformer;

    public QueryCompressionService(ChatClient.Builder chatClientBuilder) {
        this.queryTransformer = CompressionQueryTransformer.builder()
            .chatClientBuilder(chatClientBuilder)
            .build();
    }

    public String compress(List<Message> history, String followUpQuery) {
        Query query = Query.builder()
            .text(followUpQuery)
            .history(history)
            .build();

        Query transformed = queryTransformer.transform(query);
        logger.info("Compression: [{}] -> [{}]", followUpQuery, transformed.text());
        return transformed.text();
    }
}
