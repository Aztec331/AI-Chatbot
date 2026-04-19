from fastapi import FastAPI
from pydantic import BaseModel
import ollama

#this file directly calls the ollama library and creates an API endpoint for chat

app = FastAPI()

#Request model
class ChatRequest(BaseModel):
    message:str

#Response model
class ChatResponse(BaseModel):
    response:str

@app.get("/")
def home():
    return {"message": "FastAPI is working"}

# AI Chat Endpoint
@app.post("/chat")
def chat(request: ChatRequest):
    #ChatRequest converts json into request.message = "Hello AI"
    #here request.message = "Hello AI"
    user_message = request.message

    #Cll Ollama
    ollama_response = ollama.chat(
        model = 'llama3',
        #below line is input to AI 
        messages=[{"role":"user", "content":user_message}]
    )

    #below is the response you actually get from ollama
    #when do the above request

    # response = {
    # "model": "llama3",
    # "message": {
    #     "role": "assistant",
    #     "content": "Java is a programming language used to build applications."
    # },
    # "done": True
    # }

    reply = ollama_response['message']['content']

    return ChatResponse(response=reply)

