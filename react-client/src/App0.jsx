import logo from './images/aztec_main.png'
import { useState } from 'react';

export default function App0() {

  // chats stores all conversations, each conversation has its own messages
  const [chats, setChats] = useState([
    {
      id: 1,
      title: "New Chat",
      messages: [],
    },
  ]);

  // currentChatId decides which chat is open in the main chat area
  const [currentChatId, setCurrentChatId] = useState(1);

  const currentChat = chats.find((chat) => chat.id === currentChatId);
  const messages = currentChat ? currentChat.messages : [];

  // store the current input value in a state variable
  const [input, setInput] = useState("");


  //creates a new chat object with unique id, default title, and empty messages array.
  //sets CurrentChatId to the new chat's id, so the new chat opens immediately and looks brigter than other chats
  const createNewChat = () => {

    const newChat = {
      id: Date.now(),
      title: "New Chat",
      messages: [],
    };

    setChats( (prevChats) => [newChat, ...prevChats]);
    setCurrentChatId(newChat.id);
    setInput("");
    
  };


  // function to add a message to a specific chat by its ID
  const addMessageToChat = (chatId, message) => {
    setChats((prevChats) =>
      prevChats.map((chat) => {
        if (chat.id !== chatId) return chat;

        //shouldUpdateTitle is true if both condtions are true
        const shouldUpdateTitle =
        chat.title === "New Chat" && message.role === "user";

        return {
          ...chat,
          title: shouldUpdateTitle
            ? message.content.slice(0, 28) || "New Chat"
            : chat.title,
          messages: [...chat.messages, message],
        };

      })
    );
  };


  //runs when user presses Enter key
  //
  const sendMessage = async () => {

    if (!input.trim()) return;

    const userText = input;
    const chatIdAtSendTime = currentChatId;

    // show user message in the active chat
    addMessageToChat(chatIdAtSendTime, {
      role: "user",
      content: userText,
    });

    setInput("");

    // try catch and async await because backend will take time to respond
    try {

      const res = await fetch("http://localhost:8080/api/chat", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ message: userText }),
      });

      const data = await res.json();

      // show AI response in the same chat where the user sent the message
      addMessageToChat(chatIdAtSendTime, {
        role: "assistant",
        content: data.response,
      });

    } 
    
    catch {

      addMessageToChat(chatIdAtSendTime, {
        role: "assistant",
        content: "Error connecting to server",
      });

    }

  };


  return (

    <>

      <div className="Daddy flex h-screen ">

        {/* Sidebar - fixed height, won't scroll with chat */}

        <div className="Child_1 w-64 text-white p-4 bg-gray-900">

          <img className='w-12 cursor-pointer' src={logo} alt="Logo" />

          <button
            onClick={createNewChat}
            className="mt-4 w-full rounded bg-gray-700 p-2 text-left hover:bg-gray-600"
          >
            + New Chat
          </button>

          <h3 className='text-lg font-semibold mt-4'>Recent Chats</h3>

          <div className='overflow-x-hidden overflow-y-auto h-100 p-1 space-y-2'>

            {chats.map( (chat) => (
              <div
                key={chat.id}
                onClick={() => setCurrentChatId(chat.id)}
                className={`p-2 rounded cursor-pointer hover:bg-gray-800 ${
                  chat.id === currentChatId ? "bg-gray-600" : "bg-gray-700"}`}
              >
                <div className="truncate font-medium">{chat.title}</div>
                <div className="text-xs text-gray-300">
                  {chat.messages.length} messages
                </div>
              </div>
            ))}

          </div>

        </div>



        {/* flex-1 means the other child will take the remaining space in the flex container. */}
        {/* Chat Area takes remaining space, locked column layout */}
        <div className="Child_2 flex-1 flex flex-col bg-gray-100 min-h-0">

          {/* header */}
          <div className="flex justify-center p-4 border-b">
            <h1 className="font-semibold">{currentChat?.title || "Ollama3"}</h1>
          </div>

          {/* Messages Area scrolls independently, starts from top */}

          <div className="flex-1 flex flex-col overflow-x-hidden overflow-y-auto p-4">

            {messages.map((msg, i) => (
              <div key={i} className=' mb-2 flex justify-center'>

                <strong>
                  {msg.role === "user" ? "You: " : "Ollama: "}
                </strong>
                {msg.content}

              </div>
            ))}

          </div>

          {/* Input Area stays at bottom always */}
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
