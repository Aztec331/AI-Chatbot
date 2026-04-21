const chats = [
  {
    id: 1,
    title: "Python Chat",
    messages: ["What is Python?", "Python is a programming language."],
  },
  {
    id: 2,
    title: "Java Chat",
    messages: ["What is Spring Boot?", "Spring Boot helps build Java apps."],
  },
];

const currentChatId = 2;

const currentChat = chats.find( (chat) => chat.id === currentChatId );


console.log("Current chat:");
console.log(currentChat);

console.log("-------------------------------------------------------------------------")

console.log("Current chat just becomes a simple object then ");     

const messages = currentChat ? currentChat.messages:[];
console.log("-------------------------------------------------------------------------")
console.log("Messages:");
console.log(messages);

