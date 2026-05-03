# AI Chat Endpoint
@app.post("/chat")
def chat(request: ChatRequest):
    # ChatRequest converts incoming JSON into request.messages
    # Example incoming JSON:
    # {
    #   "messages": [
    #     { "role": "user", "content": "Hello AI" },
    #     { "role": "assistant", "content": "Hi there" }
    #   ]
    # }
    # After conversion -> request.messages = list of ChatMessage objects

    # Convert each ChatMessage object into a normal dictionary
    # so Ollama can understand it
    messages = [message.model_dump() for message in request.messages]

    # Call Ollama with the full conversation history
    ollama_response = ollama.chat(
        model='llama3',
        messages=messages
    )

    # below is the response you actually get from ollama
    # when we do the above request

    # response = {
    #   "model": "llama3",
    #   "message": {
    #       "role": "assistant",
    #       "content": "Java is a programming language used to build applications."
    #   },
    #   "done": True
    # }

    reply = ollama_response['message']['content']

    return ChatResponse(response=reply)
