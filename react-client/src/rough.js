const addMessageToChat = (chatId, message) => {
  setChats((prevChats) =>
    prevChats.map((chat) => {
      if (chat.id !== chatId) return chat;

      return {
        ...chat,
        messages: [...chat.messages, message],
      };
    })
  );
};
