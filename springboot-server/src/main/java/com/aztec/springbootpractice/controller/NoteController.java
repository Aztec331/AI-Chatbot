//Controller based class or views.py+urls.py

//Flow is below for entire project
//Controller -> Service -> Repository -> Database
package com.aztec.springbootpractice.controller;

import org.springframework.lang.NonNull;
//* contains @RestController @RequestMapping,@PostMapping,@GetMapping,@PutMapping,@DeleteMapping,@RequestBody,@PathVariable
import org.springframework.web.bind.annotation.*;

//file imports
import com.aztec.springbootpractice.service.NoteService;

//Entities or tables imports
import com.aztec.springbootpractice.entity.Note;
import com.aztec.springbootpractice.entity.Chat;
import com.aztec.springbootpractice.entity.Message;

//AI file imports
import com.aztec.springbootpractice.dto.ChatRequest;
import com.aztec.springbootpractice.dto.ChatResponse;
import com.aztec.springbootpractice.dto.ChatMessage;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173") // Allow requests from React frontend
@RestController
@RequestMapping("/api")
public class NoteController {

    // Stores a NoteService object (gives methods like createNote(), getAllNotes() )
    // Not used to store data, only to access NoteService methods
    // Type = NoteService (it holds a repository object with methods like save(), findAll())
    //noteService = pre-filled object with ready-made database methods
    private final NoteService noteService;

    //Constructor for NoteController class
    //Spring injects the NoteService object to controller or noteService variable
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // CREATE
    //creates note object of type Note
    //automatically filled with JSON
    @PostMapping
    public Note createNote(@NonNull @RequestBody Note note) {
        return noteService.createNote(note);
    }

    // READ ALL
    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    // READ ONE
    @GetMapping("/{id}")
    public Note getNoteById(@NonNull @PathVariable Long id) {
        return noteService.getNoteById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Note updateNote(@NonNull @PathVariable Long id, @NonNull @RequestBody Note note) {
        return noteService.updateNote(id, note);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteNote(@NonNull @PathVariable Long id) {
        noteService.deleteNote(id);
    }

    
    // POST request to send chat messages to AI
    // URL -> http://localhost:8080/api/chat
    // This method receives this from frontend:
    // { "messages": [ { "role": "user", "content": "Hello AI" } ] }
    @PostMapping("/chat")
    public ChatResponse askAI(@RequestBody ChatRequest request){

    // Step 1: Extract chat messages from JSON
    // Example incoming JSON:
    // { "messages": [ { "role": "user", "content": "Hello AI" } ] }
    // After conversion -> request.getMessages() = List<ChatMessage>
    List<ChatMessage> messages = request.getMessages();

    // Step 2: Call service layer with the message list (which calls FastAPI -> Ollama)
    // This returns a plain String like: "Hi there!"
    String aiReply = noteService.askAI(messages);

    // Step 3: Convert String -> JSON response object
    // We do this so frontend always receives proper JSON
    ChatResponse response = new ChatResponse();
    response.setResponse(aiReply);

    // Step 4: Return JSON to frontend
    // Final response:
    // { "response": "Hi there!" }
    return response;
    }



    // POST request to save a chat in database
    // URL -> http://localhost:8080/api/chats
    // This method receives this from frontend:
    // { "title": "New Chat" }
    // This method sends this back to frontend after saving:
    // { "id": 1, "title": "New Chat" }
    @PostMapping("/chats")
    public Chat saveChat( @NonNull @RequestBody Chat chat ){
        // controller return this Chat object
        // Chat
        // id = 1
        // title = "Python doubts"
        return noteService.saveChat(chat);

    }

    //Post request to save a message based on a chat id
    @PostMapping("/chats/{chatId}/messages")
    public Message saveMessage(@PathVariable Long chatId, @RequestBody Message message) {

    //creats a new empty chat object
    Chat chat = new Chat();

    //this sets chatId as 1 for the empty chat object that u created above
    chat.setId(chatId);

    //take the chat object that you created above
    //and put it inside the message object's chat field 
    message.setChat(chat);

    //after the above line message object becomes 
    // message object
    // id = null
    // role = "user"
    // content = "What is Python?"
    // chat = Chat(id=1, title=null)


    return noteService.saveMessage(message);
    }

    //get a List with multiple Chat objects
    @GetMapping("/chats")
    public List<Chat> getAllChats(){
        return noteService.getAllChats();        
    }

    //get a list of 
    @GetMapping("/chats/{chatId}/messages")
    public List<Message> getMessagesByChatId( @PathVariable Long chatId ){
        return noteService.getMessagesByChatId(chatId);
    }



   
    
}
