package in.das.repository;

import in.das.entity.Notes;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotesRepository extends JpaRepository<Notes,Long> {

    @Query("select n from Notes n where n.noteId = :noteId and n.createdBy.accountId = :accountId")
    Optional<Notes> findNoteByIdAndAccount(@Param("noteId") Long noteId, @Param("accountId") Long accountId);

    @Query("select n from Notes n where n.createdBy.accountId = :accountId and n.deleteFlag = :deleteFlag")
    List<Notes> findNotesByAccountId(@Param("accountId") Long accountId, @Param("deleteFlag") boolean deleteFlag);

    @Modifying
    @Query("update Notes n set n.deleteFlag = true where n.noteId = :noteId and n.createdBy.accountId = :accountId and n.deleteFlag = false")
    int deleteNoteByIdAndAccount(@Param("noteId") Long noteId, @Param("accountId") Long accountId);
}
