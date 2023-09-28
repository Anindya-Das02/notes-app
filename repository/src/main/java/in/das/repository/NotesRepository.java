package in.das.repository;

import in.das.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotesRepository extends JpaRepository<Notes,Long> {

    @Query("select n from Notes n where n.noteId = :noteId and n.createdBy.accountId = :accountId")
    Optional<Notes> findNoteByIdAndAccount(@Param("noteId") Long noteId, @Param("accountId") Long accountId);


//    @Query("select * from Notes notes where notes.noteId = :noteId and notes.createdBy.accountId = :accountId")
//    Optional<Notes> findNoteByIdAndAccount(@Param("noteId") Long noteId, @Param("AccountId") Long accountId);
}
