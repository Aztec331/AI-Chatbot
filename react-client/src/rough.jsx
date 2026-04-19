import logo from './images/aztec_main.png'
import { useState } from 'react';

export default function App() {

  //store role(your or AI) and message(your message or AI's response) in an array of objects
  const [messages, setMessages] = useState([]);

  //store the current input value in a state variable
  const [input, setInput] = useState("");

  const sendMessage = async () => {

    if (!input.trim()) return;

    const userText = input;

    // show user message
    setMessages((prev) => [
      ...prev,
      { role: "You: ", text: userText },
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

      // show AI response
      setMessages((prev) => [
        ...prev,
        { role: "Ollama: ", text: data.response },
      ]);

    } catch {

      setMessages((prev) => [
        ...prev,
        { role: "Ollama: ", text: "Error connecting to server" },
      ]);

    }

  };


  return (

    <>

      <div className="Daddy flex h-screen overflow-hidden">

        {/* Sidebar — fixed height, won't scroll with chat */}
        <div className="Child_1 w-64 bg-gray-900 text-white p-4 flex-shrink-0">

          <img className='w-12 cursor-pointer' src={logo} alt="Logo" />

          <h3 className='text-lg font-semibold mt-3'>Recent Chats</h3>

          <div className='p-1 space-y-2'>
            <div className="p-1 bg-gray-700 rounded cursor-pointer hover:bg-gray-900">Chat 1</div>
            <div className="p-1 bg-gray-700 rounded cursor-pointer hover:bg-gray-900">Chat 2</div>
            <div className="p-1 bg-gray-700 rounded cursor-pointer hover:bg-gray-900">Chat 3</div>
            <div className="p-1 bg-gray-700 rounded cursor-pointer hover:bg-gray-900">Chat 4</div>
            <div className="p-1 bg-gray-700 rounded cursor-pointer hover:bg-gray-900">Chat 5</div>
            <div className="p-1 bg-gray-700 rounded cursor-pointer hover:bg-gray-900">Chat 6</div>
          </div>

        </div>

        {/* Chat Area — takes remaining space, locked column layout */}
        <div className="Child_2 flex-1 flex flex-col bg-gray-100 min-h-0">

          {/* Header — stays at top */}
          <div className="flex justify-center p-4 border-b flex-shrink-0">
            <h1 className="font-semibold">Ollama3</h1>
          </div>

          {/* Messages Area — scrolls independently, starts from top */}
          <div className="flex-1 overflow-y-auto p-4 flex flex-col items-center">

            {messages.map((msg, i) => (
              <div key={i} className='mb-2 flex justify-center w-full'>
                <strong>{msg.role}</strong>{msg.text}
              </div>
            ))}

          </div>

          {/* Input Area — stays at bottom always */}
          <div className='flex justify-center p-4 gap-2 flex-shrink-0 border-t bg-gray-100'>

            <input
              type="text"
              value={input}
              onChange={(e) => setInput(e.target.value)}
              onKeyDown={(e) => e.key === 'Enter' && sendMessage()}
              placeholder='Ask something'
              className='border w-90 p-3 rounded-full'
            />

          </div>

        </div>

      </div>

    </>

  );
}