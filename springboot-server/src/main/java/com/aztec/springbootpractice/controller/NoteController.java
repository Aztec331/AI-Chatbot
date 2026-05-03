//Controller based class or views.py+urls.py

//Flow is below for entire project
//Controller -> Service -> Repository -> Database
package com.aztec.springbootpractice.controller;

import org.springframework.lang.NonNull;
//* contains @RestController @RequestMapping,@PostMapping,@GetMapping,@PutMapping,@DeleteMapping,@RequestBody,@PathVariable
import org.springframework.web.bind.annotation.*;

//file imports
import com.aztec.springbootpractice.entity.Note;
import com.aztec.springbootpractice.service.NoteService;

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


    
}
