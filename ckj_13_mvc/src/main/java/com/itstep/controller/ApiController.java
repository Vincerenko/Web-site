package com.itstep.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itstep.model.Note;
import com.itstep.model.User;
import com.itstep.repository.NoteRepository;
import com.itstep.repository.UserRepository;
import com.sun.mail.iap.Response;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ApiController {

	private UserRepository UserRepository;
	private NoteRepository noteRepository;
	
	@GetMapping("/users")
	public Iterable<User> Users() {
		return UserRepository.findAll();
	}
	
	//получение всех заметок
	@GetMapping("/notes")
	public List<EntityModel<Note>> getNotes() {
	var notes = (List<Note>) noteRepository.findAll();
	var models = notes.stream().map(note->{
	var entityModel = EntityModel.of(note,
	linkTo(methodOn(ApiController.class).getNote(note.getId())).withRel("info")
	);
	return entityModel;
	}).collect(Collectors.toList());
	return models;
	}
	
	//создание заметки
	@PostMapping("/notes")
	public Note saveNote(@RequestBody Note note) {
		note.setZd(LocalDateTime.now().toString());
		return noteRepository.save(note);
	}
	//получение одной заметки
	@GetMapping("/notes/{id}")
	public EntityModel<Note> getNote(@PathVariable Integer id) {
		//обработать ошибку и отдать not found
		var note = noteRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Note dosen't exists, with such id: " + id));
		EntityModel<Note> response = EntityModel.of(note,
				linkTo(methodOn(ApiController.class).getNotes()).withRel("notes"));
		return response;
		
		
		
	}
	//редактирование заметки
	@PutMapping("/notes")
	public EntityModel<Note> changeNote(@RequestBody Note newNote) {
		var note = noteRepository.save(newNote);
		EntityModel<Note> response = EntityModel.of(note, linkTo(methodOn(ApiController.class).getNotes()).withRel("EDIT"));
		return response;
	}
	//удаление заметки
	@DeleteMapping("/notes/{id}")
	public void deleteNote(@PathVariable Integer id) {
		noteRepository.deleteById(id);
	}
	
	
}
