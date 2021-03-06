package com.itstep.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itstep.model.Note;
import com.itstep.model.User;
import com.itstep.repository.NoteRepository;
import com.itstep.repository.UserRepository;

@Controller
@RequestMapping("/notes")
public class NoteController {
	// private List<Note> notes = new ArrayList<>();
	private NoteRepository noteRepository;
	private UserRepository userRepositoty;

	
	

	@GetMapping("/create")
	public String create() {
		return "create_note";
	}
	
	@Autowired
	public NoteController(NoteRepository noteRepository, UserRepository userRepositoty) {
		this.noteRepository = noteRepository;
		this.userRepositoty = userRepositoty;
	}

	@PostMapping("/add")
	public String add(@ModelAttribute(name = "note") Note note, Principal principal) {
	    User user = userRepositoty.findByUsername(principal.getName());
	    
		note.setZd(LocalDateTime.now().toString());
		user.addNote(note);
		noteRepository.save(note);
		userRepositoty.save(user);
		return "redirect:/notes";
	}

	@GetMapping
	public String all(Model model,Principal principal) {
		 User user = userRepositoty.findByUsername(principal.getName());
		List<Note> notes= user.getNotes();
		 model.addAttribute("notes", user.getNotes());
		
		return "notes";
	}

	@GetMapping("/{id}")
	public String info(@PathVariable(name = "id") int id, Model model,Principal principal) {
		 User user = userRepositoty.findByUsername(principal.getName());
		List<Note> notes = user.getNotes();
		 Note note = noteRepository.findById(id).get();
		 if(notes.contains(note)) {
		model.addAttribute("note", note);
		return "info";
		 }
		 else {
			 return "redirect:/notes";
		 }
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(name = "id") int id,Principal principal) {
		 User user = userRepositoty.findByUsername(principal.getName());
		 List<Note> notes = user.getNotes();
		 Note note = noteRepository.findById(id).get();
		 if(user.getNotes().contains(note)) {
		 noteRepository.deleteById(id);
		 return "redirect:/notes";
		 }
		 else {
			 return "redirect:/notes";
		 }
		
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable(name = "id") int id, Model model,Principal principal) {
		 User user = userRepositoty.findByUsername(principal.getName());
		 List<Note> notes = user.getNotes();
		 Note note = noteRepository.findById(id).get();
		 if(user.getNotes().contains(note)) {
		model.addAttribute("note", note);
		return "edit";
		 }
		 else {
			 return "redirect:/notes";
		 }
	}

	@PostMapping("/edit/{id}")
	public String editNote(@ModelAttribute Note note, @PathVariable(name = "id") int id,Principal principal) {
		// String s = LocalDateTime.now().toString();
		 User user = userRepositoty.findByUsername(principal.getName());
		note.setZd(LocalDateTime.now().toString());
		note.setUser(user);
		noteRepository.save(note);
		return "redirect:/notes/";
	}

	@GetMapping("/search")
	public String search(@RequestParam(name = "word") String word, Model model, Principal prl) {
		 User user = userRepositoty.findByUsername(prl.getName());
		 word="%" + word + "%";
		List<Note> notes = noteRepository.search(word, user.getId());
		model.addAttribute("notes", notes);
		return "notes";
	}

}
