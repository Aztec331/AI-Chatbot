const chats = [
  {
    id: 1,
    title: "New Chat",
    messages: [
      { role: "user", content: "Hello, how are you?" },
      {
        role: "assistant",
        content: "I'm good, thank you! How can I assist you today?",
      },
    ],
  },
  {
    id: 2,
    title: "Project Discussion",
    messages: [
      { role: "user", content: "Can you help me build a chatbot UI?" },
      {
        role: "assistant",
        content: "Yes, we can start with sidebar, chat area, and input box.",
      },
      { role: "user", content: "Nice, show me the folder structure too." },
    ],
  },
  {
    id: 3,
    title: "Bug Fix Chat",
    messages: [
      { role: "user", content: "My messages are not rendering." },
      {
        role: "assistant",
        content: "Check whether you are mapping over the correct messages array.",
      },
    ],
  },
  {
    id: 4,
    title: "Long Chat Example",
    messages: [
      { role: "user", content: "Message 1" },
      { role: "assistant", content: "Message 2" },
      { role: "user", content: "Message 3" },
      { role: "assistant", content: "Message 4" },
      { role: "user", content: "Message 5" },
      { role: "assistant", content: "Message 6" },
      { role: "user", content: "Message 7" },
      { role: "assistant", content: "Message 8" },
      { role: "user", content: "Message 9" },
      { role: "assistant", content: "Message 10" },
      { role: "user", content: "Message 11" },
      { role: "assistant", content: "Message 12" },
      { role: "user", content: "Message 13" },
      { role: "assistant", content: "Message 14" },
      { role: "user", content: "Message 15" },
    ],
  },
];

const currentChatId = 2;

const currentChat = chats.find( (chat) => chat.id=== currentChatId );

const messages = currentChat ? currentChat.messages : [];

const chatIdAtSendTime = 4;

const chatAtSendTime = chats.find( (chat) =>  chat.id === chatIdAtSendTime );

const previousMessages = chatAtSendTime ? chatAtSendTime.messages.slice(-10) : [];


console.log(previousMessages)

