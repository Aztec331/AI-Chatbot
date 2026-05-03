from fastapi import FastAPI
from pydantic import BaseModel
import ollama

#this file directly calls the ollama library and creates an API endpoint for chat
app = FastAPI()

'''Springboot is sending fastapi a json body like this
{
  "messages": [
    {
      "role": "user",
      "content": "Hello AI"
    },
    {
      "role": "user",
      "content": "What is Java?"
    }
  ]
}'''

#one single message object inside the messages list
#example :
#{"role":"user", "content":"Hello AI"}
class ChatMessage(BaseModel):
    role:str
    content:str

#Request model to validate the incoming request body for the /chat endpoint
class ChatRequest(BaseModel):
    messages: list[ChatMessage]

#Response model to validate the outgoing response body for the /chat endpoint
class ChatResponse(BaseModel):
    response:str

@app.get("/")
def home():
    return {"message": "FastAPI is working"}

# AI Chat Endpoint
@app.post("/chat")
def chat(request: ChatRequest):
    '''ChatRequest converts incoming JSON into request.messages
    Example incoming JSON:
    
    {
    "messages": [
    {
      "role": "user",
      "content": "Hello AI"
    },
    {
      "role": "assistant",
      "content": "Hi there"
    },
    {
      "role": "user",
      "content": "What is Java?"
    }
    ]
    }

    After conversion -> request.messages = list of ChatMessage objects
    and it looks like this in python
    request.messages = [
    ChatMessage(role="user", content="Hello AI"),
    ChatMessage(role="assistant", content="Hi there"),
    ChatMessage(role="user", content="What is Python?")
    ]'''

    messages = [message.model_dump() for message in request.messages]


    #Call Ollama with full conversation history
    ollama_response = ollama.chat(
        model = 'llama3',
        #below line is input to AI 
        messages=messages
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

