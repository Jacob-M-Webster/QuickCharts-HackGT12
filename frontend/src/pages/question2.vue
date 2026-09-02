<script setup>
import { ref, onMounted } from 'vue';

const OPENAI_API_KEY = import.meta.env.VITE_OPEN_AI_KEY;

const conversation = ref([]);
const userInput = ref('');
const isLoading = ref(false);

const questionCount = ref(0);
const MAX_QUESTIONS = 6;
const firstQuestionAsked = ref(false);

const questionnaireFinished = ref(false);
const summary = ref(null);

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
              content: `You are a professional medical assistant. Your goal is to collect relevant patient information in a strict, concise, and fact-based manner.
Ask one direct, medically relevant question at a time. Avoid emotional language, unnecessary explanations, or repetitive questions.
Keep each question short, clear, and structured for efficient data collection. Limit to 5 follow-up questions.`
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

async function submitAnswer() {
  if (!userInput.value.trim()) return;

  conversation.value.push({ role: 'user', content: userInput.value });
  userInput.value = '';

  await pushNextQuestion();
}

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

Please generate a concise, professional medical summary in bullet points. 
- Use clear, natural language.
- Organize information logically but without a strict rigid template.
- Place each main point on a separate line.
- Include details such as symptoms, duration, severity, associated symptoms, and relevant history where applicable.
- Avoid repeating information.
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
  pushNextQuestion();
});
</script>

<template>
  <div class="max-w-2xl mx-auto p-6 bg-white rounded-lg shadow-lg space-y-6">
    <!-- Header -->
    <div class="text-center">
      <h1 class="text-3xl font-bold text-navy-800 mb-2">Healthcare Patient Assessment</h1>
      <div v-if="!summary" class="text-lg text-navy-600 font-semibold">
        Welcome, please answer the following questions so we can provide an initial assessment.
      </div>
    </div>

    <!-- Transition -->
    <transition name="fade-slide" mode="out-in">
      <!-- Q&A Section -->
      <div v-if="!summary" key="qa" class="space-y-4">
        <div v-for="(msg, index) in conversation" :key="index" class="p-4 rounded-md border border-gray-300"
             :class="msg.role === 'assistant' ? 'bg-blue-50 text-navy-800' : 'bg-gray-50 text-gray-800'">
          <template v-if="msg.role === 'assistant'">
            <strong>Q{{ conversation.filter((m, i) => m.role === 'assistant' && i <= index).length }}:</strong>
          </template>
          <p class="ml-2">{{ msg.content }}</p>
        </div>

        <div v-if="!questionnaireFinished" class="flex flex-col items-center space-y-3">
          <textarea
            v-model="userInput"
            placeholder="Type your answer..."
            class="w-full border border-gray-300 rounded-lg p-3 focus:outline-none focus:ring-2 focus:ring-blue-500"
          ></textarea>

          <button
            @click="submitAnswer"
            :disabled="isLoading"
            class="px-6 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition"
          >
            Submit
          </button>
        </div>

        <div v-if="questionnaireFinished" class="text-center mt-4">
          <button
            @click="commitSummary"
            :disabled="isLoading"
            class="px-6 py-2 bg-green-600 text-white rounded-lg hover:bg-green-700 transition"
          >
            Commit
          </button>
        </div>
      </div>

      <!-- Summary Section -->
      <div v-else key="summary" class="p-6 bg-gray-50 rounded-lg border border-gray-200">
        <h2 class="text-xl font-semibold mb-4 text-navy-700">Summary</h2>
        <pre class="whitespace-pre-line text-gray-800">{{ summary }}</pre>
        <div class="mt-4 text-center">
          <button class="px-6 py-2 bg-red-600 text-white rounded-lg hover:bg-red-700 transition">
            Exit
          </button>
        </div>
      </div>
    </transition>

    <div v-if="isLoading" class="text-gray-500 text-center">Thinking...</div>
  </div>
</template>

<style scoped>
.fade-slide-enter-active,
.fade-slide-leave-active {
  transition: all 0.5s ease;
}
.fade-slide-enter-from,
.fade-slide-leave-to {
  opacity: 0;
  transform: translateY(20px);
}
.fade-slide-enter-to,
.fade-slide-leave-from {
  opacity: 1;
  transform: translateY(0);
}

/* Font styling */
* {
  font-family: 'IBM Plex Mono', monospace;
}

/* Navy blue text colors */
.text-navy-600 { color: #1c3962; }
.text-navy-700 { color: #3c4555; }
.text-navy-800 { color: #1e2d55; }
</style>
