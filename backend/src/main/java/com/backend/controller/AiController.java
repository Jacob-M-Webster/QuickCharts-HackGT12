package com.backend.controller;

import com.backend.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final OpenAiService openAiService;

    // In-memory store for simplicity
    private final Map<String, List<Map<String, String>>> conversationStore = new HashMap<>();

    @Autowired
    public AiController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @PostMapping("/next-question")
    public Map<String, String> nextQuestion(@RequestParam String sessionId,
                                            @RequestBody Map<String, String> payload) {
        String lastAnswer = payload.get("answer");

        List<Map<String, String>> conversation = conversationStore.computeIfAbsent(sessionId, k -> new ArrayList<>());

        // Add last answer with placeholder question (filled after API call)
        conversation.add(new HashMap<>() {{
            put("question", "");
            put("answer", lastAnswer);
        }});

        String nextQuestion = openAiService.getNextQuestion(conversation);

        // Update last entry with the AI-generated question
        conversation.get(conversation.size() - 1).put("question", nextQuestion);

        return Map.of("question", nextQuestion);
    }
}
