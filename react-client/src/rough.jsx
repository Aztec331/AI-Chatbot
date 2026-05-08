useEffect(() => {

  const loadChats = async () => {
    
    try {
      const res = await fetch("http://localhost:8080/api/chats");
      //data contains the list from 
      const data = await res.json();

      const chatsWithMessages = data.map((chat) => ({
        ...chat,
        messages: [],
      }) );

      setChats(chatsWithMessages);

      if (chatsWithMessages.length > 0) {
        setCurrentChatId(chatsWithMessages[0].id);
      }

    }
    //if api fails show error
    catch (error) {
      console.error("Failed to load chats:", error);
    }

  };

  loadChats();

}, []);
