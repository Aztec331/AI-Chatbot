// handles business logic and service based class
package com.aztec.springbootpractice.service;

import org.springframework.stereotype.Service;
import org.springframework.lang.NonNull;

//CRUD file imports
import com.aztec.springbootpractice.entity.Note;
import com.aztec.springbootpractice.repository.NoteRepository;

//AI file imports and RestTemplate to call another API
import org.springframework.web.client.RestTemplate;
import com.aztec.springbootpractice.dto.ChatRequest;
import com.aztec.springbootpractice.dto.ChatResponse;

import java.util.List;

@Service
public class NoteService {

    // Stores a NoteRepository object (gives methods like save(), findAll())
    // Not used to store data, only to access repository methods
    // Type = NoteRepository (it holds a repository object with methods like save(), findAll())
    //noteRepository = pre-filled object with ready-made database methods 👍
    private final NoteRepository noteRepository;

    //Constructor for NoteService class
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    //function type Note which returns a note object
   // CREATE
    public Note createNote(@NonNull Note note) {
    return noteRepository.save(note);
    }

    // READ ALL
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // READ ONE
    public Note getNoteById(@NonNull Long id) {
    return noteRepository.findById(id).orElse(null);
    }

    // UPDATE
    public Note updateNote(@NonNull Long id, @NonNull Note updatedNote) {
    Note existingNote = noteRepository.findById(id).orElse(null);

    if (existingNote != null) {
        existingNote.setTitle(updatedNote.getTitle());
        existingNote.setContent(updatedNote.getContent());
        return noteRepository.save(existingNote);
    }

    return null;
    }


    // DELETE
    public void deleteNote(@NonNull Long id) {
    noteRepository.deleteById(id);
    }

    // POST request 
    //this askAI function get pure string message like "How are ya?"
    public String askAI(String message){

        //creates a tool to call another API
        //we call fastAPI using this
        //bridge between springboot and fastapi
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://127.0.0.1:8000/chat";

        //these 2 lines convert the String
        //"Hello AI" -> {"message":"Hello AI"}
        //create an empty object request and use
        //.setMessage method to make like this request= {message:null}
        //hence request object will now have request={message:"Hello AI"}
        ChatRequest request = new ChatRequest();
        request.setMessage(message);

        
        //restTemplate.postForObject means send post request to another api
        //url means where to send
        //request means what to send, request which means -> request={message:"Hello AI"}
        //ChatResponse.class tell spring convert this JSON ->{"message":"Hello AI"} into ChatResponse object 
       //JSON is finally coverted to the following below 
        //ChatResponse obj = new ChatResponse();
        //obj.setResponse("You said: Hello AI");
        ChatResponse response = restTemplate.postForObject(url, request , ChatResponse.class);

        //if response is not null show it else show "Error: No response from AI"
        //why not return json ? because function's return type is String
        //if we do return response , we get -> { "response": "You said: Hello AI" }
        //thats why we do -> response.getResponse() , we get - "You said: Hello AI"
        return response != null ? response.getResponse(): "Error: No response from AI";

    }



    
}