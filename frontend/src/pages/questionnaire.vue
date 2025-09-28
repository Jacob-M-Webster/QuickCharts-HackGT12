<script setup>
import { ref, onMounted } from 'vue';

const OPENAI_API_KEY = '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';

const conversation = ref([]);
const userInput = ref('');
const isLoading = ref(false);

const questionCount = ref(0);
const MAX_QUESTIONS = 6;
const firstQuestionAsked = ref(false);

// ✅ Track if questionnaire is finished
const questionnaireFinished = ref(false);

// ✅ Store summary (generated only after commit)
const summary = ref('');

// ✅ Function to push the next AI question
async function pushNextQuestion() {
  if (questionCount.value >= MAX_QUESTIONS) {
    conversation.value.push({ role: 'assistant', content: 'You have completed the questionnaire. Please press "Commit" to view a summary of your responses.' });
    questionnaireFinished.value = true; 
    return;
  }

  isLoading.value = true;

  try {
    let aiMessage = '';

    if (!firstQuestionAsked.value) {
      aiMessage = 'What brings you in today?'; 
      firstQuestionAsked.value = true;
    } else {
      const response = await fetch('https://api.openai.com/v1/chat/completions', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${OPENAI_API_KEY}`,
        },
        body: JSON.stringify({
          model: 'gpt-4o-mini',
          messages: [
            { 
              role: 'system', 
              content: `You are a medical assistant that gathers information by asking relevant, accurate follow-up questions based on the user’s responses. Ask one question at a time, avoid repeating questions, and ensure your inquiries are clear, concise, and professional in tone, without emotional language or unnecessary commentary. Keep follow-up questions relevant and avoid excessive detail or extreme depth. Your goal is to compile a clear list of symptoms and provide a rough preliminary diagnosis based on the information gathered from the user. Limit yourself to 5 follow-up questions.` 
            },
            ...conversation.value
          ]
        })
      });

      const data = await response.json();
      console.log('OpenAI response for next question:', data);

      aiMessage = data?.choices?.[0]?.message?.content ?? 'Error: no message returned';
    }

    conversation.value.push({ role: 'assistant', content: aiMessage });
    questionCount.value++;
  } catch (err) {
    console.error('Error fetching AI question:', err);
    conversation.value.push({ role: 'assistant', content: 'Something went wrong. Please try again.' });
  } finally {
    isLoading.value = false;
  }
}

// ✅ Submit user answer and push next question
async function submitAnswer() {
  if (!userInput.value.trim()) return;

  conversation.value.push({ role: 'user', content: userInput.value });
  userInput.value = '';

  await pushNextQuestion();
}

// ✅ Commit button handler to generate professional bullet-point summary
async function commitSummary() {
  isLoading.value = true;
  try {
    const userResponses = conversation.value
      .filter(msg => msg.role === 'user')
      .map(msg => msg.content);

    if (userResponses.length === 0) {
      summary.value = 'No user responses to summarize.';
      return;
    }

    const prompt = `
You are a professional medical assistant. 
The user has answered the following questions:

${userResponses.map((r, i) => `${i+1}. ${r}`).join('\n')}

Please generate a concise summary in bullet points. 
Each bullet should restate the user's answers in a professional, clear manner. Do not repeat the questions, just summarize the answers.
`;

    const response = await fetch('https://api.openai.com/v1/chat/completions', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${OPENAI_API_KEY}`,
      },
      body: JSON.stringify({
        model: 'gpt-4o-mini',
        messages: [
          { role: 'system', content: 'You are a professional medical assistant summarizing patient responses.' },
          { role: 'user', content: prompt }
        ]
      })
    });

    const data = await response.json();
    console.log('Full GPT response for summary:', data);

    const generated = data?.choices?.[0]?.message?.content;
    if (!generated) {
      summary.value = 'The AI did not return a summary. Please try again.';
    } else {
      summary.value = generated;
    }

  } catch (err) {
    console.error('Error generating summary:', err);
    summary.value = 'Something went wrong while generating the summary. Please try again.';
  } finally {
    isLoading.value = false;
  }
}

onMounted(() => {
  pushNextQuestion(); // ✅ show first question immediately
});
</script>

<template>
  <div class="max-w-xl mx-auto p-6 space-y-4">
    <div class="text-blue-600 font-semibold mb-2">
      Welcome, please answer the following questions so we can provide an initial assessment.
    </div>

    <div v-for="(msg, index) in conversation" :key="index" :class="msg.role === 'assistant' ? 'text-blue-600' : 'text-gray-800'">
      <strong>{{ msg.role === 'assistant' ? 'Q:' : 'A:' }}</strong> {{ msg.content }}
    </div>

    <!-- ✅ Show textarea + submit button only if questionnaire not finished -->
    <div v-if="!questionnaireFinished" class="mt-4">
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

    <!-- ✅ Commit button shown when questionnaire is finished and summary not yet generated -->
    <div v-if="questionnaireFinished && !summary" class="mt-4">
      <button
        @click="commitSummary"
        :disabled="isLoading"
        class="px-4 py-2 bg-green-600 text-white rounded"
      >
        Commit
      </button>
    </div>

    <!-- ✅ Display professional bullet-point summary only after commit -->
    <div v-if="summary" class="mt-4 p-4 border rounded bg-gray-50 text-gray-800 whitespace-pre-wrap">
      <strong>Summary:</strong>
      <div>{{ summary }}</div>
    </div>

    <div v-if="isLoading" class="text-gray-500 mt-2">Thinking...</div>
  </div>
</template>
