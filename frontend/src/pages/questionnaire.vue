<script setup>
import { ref } from 'vue';

const OPENAI_API_KEY = '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';

const conversation = ref([
  { role: 'assistant', content: "Hello! Let's start. How are you feeling today?" }
]);

const userInput = ref('');
const isLoading = ref(false);

async function submitAnswer() {
  if (!userInput.value.trim()) return;

  // Add user answer to conversation
  conversation.value.push({ role: 'user', content: userInput.value });
  const userMessage = userInput.value;
  userInput.value = '';
  isLoading.value = true;

  try {
    const response = await fetch('https://api.openai.com/v1/chat/completions', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${OPENAI_API_KEY}`,
      },
      body: JSON.stringify({
        model: 'gpt-4o-mini',
        messages: [
          ...conversation.value,
          { role: 'system', content: 'You are a medical assistant asking follow-up questions based on user responses. Ask one new question at a time. Avoid repeating questions.' }
        ]
      })
    });

    const data = await response.json();
    const aiMessage = data.choices[0].message.content;

    // Add AI's follow-up question
    conversation.value.push({ role: 'assistant', content: aiMessage });
  } catch (err) {
    console.error('Error fetching AI question:', err);
    conversation.value.push({ role: 'assistant', content: 'Something went wrong. Please try again.' });
  } finally {
    isLoading.value = false;
  }
}
</script>

<template>
  <div class="max-w-xl mx-auto p-6 space-y-4">
    <div v-for="(msg, index) in conversation" :key="index" :class="msg.role === 'assistant' ? 'text-blue-600' : 'text-gray-800'">
      <strong>{{ msg.role === 'assistant' ? 'Q:' : 'A:' }}</strong> {{ msg.content }}
    </div>

    <div class="mt-4">
      <textarea
        v-model="userInput"
        placeholder="Type your answer..."
        class="w-full border rounded p-2"
      ></textarea>
      <button
        @click="submitAnswer"
        :disabled="isLoading"
        class="mt-2 px-4 py-2 bg-blue-600 text-white rounded"
      >
        Submit
      </button>
    </div>

    <div v-if="isLoading" class="text-gray-500 mt-2">Thinking...</div>
  </div>
</template>
