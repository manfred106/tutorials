package com.baeldung.springai.rag.preretrieval;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.rag.Query;
import org.springframework.ai.rag.preretrieval.query.transformation.QueryTransformer;
import org.springframework.ai.rag.preretrieval.query.transformation.RewriteQueryTransformer;
import org.springframework.stereotype.Service;

@Service
public class QueryRewriteService {

    private static final Logger logger = LoggerFactory.getLogger(QueryRewriteService.class);
    private final QueryTransformer queryTransformer;

    public QueryRewriteService(ChatClient.Builder chatClientBuilder) {
        this.queryTransformer = RewriteQueryTransformer.builder()
            .chatClientBuilder(chatClientBuilder)
            .build();
    }

    public String rewrite(String userQuery) {
        Query transformed = queryTransformer.transform(new Query(userQuery));
        logger.info("Rewrite: [{}] -> [{}]", userQuery, transformed.text());
        return transformed.text();
    }
}
