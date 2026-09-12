package com.baeldung.springai.rag.preretrieval;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PreRetrievalQueryTransformerLiveTest {

    @Autowired
    private QueryRewriteService queryRewriteService;

    @Autowired
    private QueryTranslationService queryTranslationService;

    @Autowired
    private QueryCompressionService queryCompressionService;

    @Test
    void whenQueryIsVerbose_thenRewrite() {
        String userQuery = "I'm studying machine learning and honestly the whole thing is confusing. What is an LLM anyway?";
        String transformedText = queryRewriteService.rewrite(userQuery);
        assertThat(transformedText).isNotEqualTo(userQuery);
    }

    @Test
    void whenQueryIsInTraditionalChinese_thenTranslate() {
        String userQuery = "什麼是向量資料庫？";
        String transformedText = queryTranslationService.translate(userQuery);
        assertThat(transformedText).isNotEqualTo(userQuery);
    }

    @Test
    void whenQueryIsFollowUpWithHistory_thenCompress() {
        List<Message> history = List.of(
            new UserMessage("What is Spring AI?"),
            new AssistantMessage("Spring AI is an application framework that brings Spring's design principles to AI engineering."));
        String followUpQuery = "And how does it support RAG?";
        String transformedText = queryCompressionService.compress(history, followUpQuery);
        assertThat(transformedText).isNotEqualTo(followUpQuery);
    }
}
