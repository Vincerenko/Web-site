package com.itstep.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private  String username;
	private String lastname;
	private int age;
	@JsonIgnore
	private String password;
	private String role;
	private String email;
	private boolean enabled;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	@ToString.Exclude
	@lombok.EqualsAndHashCode.Exclude
	@JsonIgnore
	private List<Note> notes = new ArrayList<>();
	
	public void addNote(Note note) {
		note.setUser(this);
		notes.add(note);
	}
}
