# 🤖 DevAI Assistant (AI Chatbot)

A full-stack AI chatbot application powered by **Llama 3** running locally via Ollama. The architecture uses a Spring Boot API gateway, a FastAPI inference layer, a React frontend, and Langfuse for observability — with persistent chat history across sessions.

---

## Architecture Overview

```
React Client
     │
     ▼
Spring Boot Server  (REST API gateway, chat history persistence)
     │
     ▼
FastAPI Server      (LLM inference via Ollama + Langfuse tracing)
     │
     ▼
Ollama (Llama 3)    (local LLM runtime)
     │
Langfuse            (LLM observability & tracing)
```

| Layer | Technology | Role |
|---|---|---|
| Frontend | React | Chat UI |
| API Gateway | Spring Boot | Routing, chat history management |
| Inference | FastAPI + Python | Ollama integration, Langfuse tracing |
| LLM | Ollama (Llama 3) | Local language model |
| Observability | Langfuse | Prompt tracing & monitoring |

---

## Features

- 💬 Conversational AI powered by Llama 3 running entirely locally
- 🗂️ Persistent chat history — conversations are saved and restored across sessions
- 🔭 LLM observability via Langfuse (traces, scores, prompt management)
- 🧩 Decoupled microservice architecture — each layer is independently runnable
- ⚡ React frontend with a clean chat interface

---

## Prerequisites

Make sure you have the following installed:

- [Node.js](https://nodejs.org/) (v18+)
- [Java 17+](https://adoptium.net/) and Maven
- [Python 3.10+](https://www.python.org/)
- [Ollama](https://ollama.com/) with the Llama 3 model pulled
- A [Langfuse](https://langfuse.com/) account (cloud or self-hosted)

---

## Getting Started

### 1. Pull the Llama 3 model

```bash
ollama pull llama3
```

### 2. FastAPI Server

```bash
cd fastapi-server
pip install -r requirements.txt
```

Create a `.env` file in `fastapi-server/`:

```env
LANGFUSE_PUBLIC_KEY=your_public_key
LANGFUSE_SECRET_KEY=your_secret_key
LANGFUSE_HOST=https://cloud.langfuse.com   # or your self-hosted URL
```

Start the server:

```bash
uvicorn app.main:app --reload --port 8000
```

### 3. Database (PostgreSQL)

Make sure PostgreSQL is running and configured in your Spring Boot `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=your_user
spring.datasource.password=your_password
```

### 4. Spring Boot Server

```bash
cd springboot-server
./mvnw spring-boot:run
```

The Spring Boot server starts on `http://localhost:8080` by default.

### 5. React Client

```bash
cd react-client
npm install
npm start
```

The app will be available at `http://localhost:5173`.

---

## Project Structure

```
AI-Chatbot/
├── fastapi-server/
│   └── app/              # FastAPI app — Ollama inference + Langfuse tracing
├── springboot-server/    # Spring Boot — REST gateway + chat history
└── react-client/         # React — chat UI
```

---

## How It Works

1. The user sends a message from the React frontend.
2. Spring Boot receives the request, appends it to the session's chat history, and forwards it to the FastAPI server along with prior conversation context.
3. FastAPI sends the prompt to Ollama (Llama 3) and returns the response.
4. Every inference call is traced in Langfuse for observability.
5. The response is returned to the frontend and the updated history is persisted.

---

## Observability with Langfuse

All LLM calls are traced via Langfuse, giving you:

- Full prompt/response logs per session
- Latency and token usage tracking
- The ability to score and evaluate responses

Access your traces at [cloud.langfuse.com](https://cloud.langfuse.com) or your self-hosted Langfuse dashboard.

---

## Tech Stack

- **React** — Frontend UI
- **Spring Boot** — Java REST API gateway
- **FastAPI** — Python inference server
- **Ollama + Llama 3** — Local LLM runtime
- **PostgreSQL** — Chat history persistence
- **Langfuse** — LLM observability

---

## Screenshots

![Chat UI](./screenshots/chat.png)

---

## License

MIT
