import logo from './images/aztec_main.png'
import { useState } from 'react';

export default function App() {

  //chats stores all conversations, each conversation has its own messages
  const [chats, setChats] = useState([
    {
      id: 1,
      title: "New Chat",
      messages: [],
    }

  ])

  //currentChatId decides which chat is open in the main chat area
  const [currentChatId, setCurrentChatId] = useState(1);

  const currentChat = chats.find( (chat) => chat.id === currentChatId );
  const messages = currentChat ? currentChat.messages : [];

  //store the current input value what user is typing in the input field
  const [input, setInput] = useState("");
  

  //creates a new chat object with unique id, default title, and empty messages array.
  //sets CurrentChatId to the new chat's id, so the new chat opens immediately and looks brigter than other chats
  const createNewChat = () =>{

    const newChat ={
      id: Date.now(),
      title: "New Chat",
      messages:[]
    };

    setChats( (prevChats) => [newChat, ...prevChats] );
    setCurrentChatId(newChat.id);
    setInput("");

  }


  //
  const addMessageToChat = (chatId, message) =>{}

  //Sends user input to the backend and updates messages with the response.
  //Runs when we press the Enter key in the input field.
  const sendMessage = async () => {

  if (!input.trim()) return;

  const userText = input;

  // show user message
  //REPLACE WITH addMessageToChat function 
  setMessages((prev) => [
    ...prev,
    { role: "user", content: userText },
  ]);

  setInput("");

  //try catch and async await because backend will take time to respond
  //if failed , you can show text: "Error connecting to server"
  try {

    const res = await fetch("http://localhost:8080/api/chat", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ message: userText }),
    });

    const data = await res.json();

    //REPLACE with addMessageToChat function 
    setMessages((prev) => [
      ...prev,
      { role: "assistant", content: data.response },
    ]);

  } 
  
  catch {

    setMessages((prev) => [
      ...prev,
      { role: "assistant", content: "Error connecting to server" },
    ]);

  }

  };


  return (

    <>

  <div className="Daddy flex h-screen ">

      {/* Sidebar- fixed height, won't scroll with chat */}
      
      <div className="Child_1 w-64 text-white p-4 bg-gray-900">

      <img className='w-12 cursor-pointer' src={logo} alt="Logo" />

      <button 
      onClick={createNewChat}
      className='mt-4 w-full rounded p-2 cursor-pointer text-left hover:bg-gray-900 bg-gray-700'>
      + New Chat
      </button>

      <h3 className='text-lg font-semibold mt-3'>Recent Chats</h3>

      <div className='overflow-x-hidden overflow-y-auto h-100 p-1 space-y-2'>

        {chats.map( (chat) => (

          <div
          key = {chat.id}
          onClick={ () => setCurrentChatId(chat.id) }
          className={`p-3 rounded cursor-pointer hover:bg-gray-900
          ${ chat.id === currentChatId ? "bg-gray-500" : "bg-gray-700" }`}

          >
      
          {chat.title}

          </div>

        ))}

      </div>


      </div>



      {/* flex-1 means the other child will take the remaining space in the flex container. */}
      {/* Chat Area— takes remaining space, locked column layout */}
      <div className="Child_2 flex-1 flex flex-col bg-gray-100 min-h-0">

      {/* header */}
      <div className="flex justify-center p-4 border-b">
        <h1 className="font-semibold">Ollama3</h1>
      </div>

      {/* Messages Area — scrolls independently, starts from top */}

      <div className="flex-1 flex flex-col overflow-x-hidden overflow-y-auto p-4">

        { messages.map( (msg,i) => (
          <div key={i} className=' mb-2 flex justify-center'>

              <strong>
                {msg.role === "user" ? "You: " : "Ollama: "}
              </strong>
              {msg.content}
          
          </div>
          
        ))}

      </div>

      {/* Input Area  stays at bottom always*/}
      {/* it has input and a button- 2 children in a flex container */}
      <div className='flex justify-center p-4'>

      <input 
      type="text"
      value={input}
      onChange={(e) => setInput(e.target.value)}
      onKeyDown={(e) => e.key === 'Enter' && sendMessage()}
      placeholder='Ask something'
      className='border w-130 p-3 rounded-full'
      />
      
      </div>

      </div>



    </div>
    
    </>



  );
}

