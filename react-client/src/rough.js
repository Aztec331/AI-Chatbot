const chats = [
  {
    id: 1,
    title: "New Chat",
    messages: [
      { user: "Hello" },
      { assistant: "Hi there!" },
      { user: "How are you?" },
      { assistant: "I'm doing well, thank you!" },
    ],
  },
  {
    id: 2,
    title: "React Doubts",
    messages: [{ user: "What is map?" }],
  },
];

const addMessageToChat = (chatId, message) => {
  
  const updatedChats = chats.map((chat) => {
    if (chat.id !== chatId) return chat;

    return {
      ...chat,
      messages: [...chat.messages, message],
    };
  });

  return updatedChats;
};

const allChats = addMessageToChat(1, {
  assistant: "This new message was added only to chat id 1.",
});

console.log(allChats);
