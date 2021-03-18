package com.itstep;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//описывает функциональсть NoteDao
//унаследовал базовые CRUD-методы от CrudRepository
@Repository
public interface NoteRepository extends CrudRepository<Note, Integer> {
	
	//List<Note>findByTitleContainingOrMessageContaining(String word1, String word2);
	
	//SELECT * FROM note WHERE title LIKE word OR message LIKE word
	@Query("SELECT n FROM Note n WHERE n.title LIKE :word OR n.message LIKE :word OR n.zd LIKE :word")
	List<Note> search(@Param("word")String word);
	
	
	
	

	
	
	
	
			
	
	
}
