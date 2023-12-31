package in.das.repository.impl;

import in.das.entity.Notes;
import in.das.repository.NotesRepository;
import in.das.shared.models.NotesRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class NotesRepoImpl {

    @Autowired
    private NotesRepository notesRepository;

    public Notes saveNote(final Notes note){
        Notes noteResult = null;
        try {
            noteResult = notesRepository.save(note);
            log.info("note saved successfully! noteId : {}",noteResult.getNoteId());
        }catch (Exception ex){
            log.error("error occurred while saving the note!");
            ex.printStackTrace();
        }
        return noteResult;
    }

    public Optional<Notes> fetchNoteByIdAndAccountId(final NotesRequest notesRequest){
        return notesRepository.findNoteByIdAndAccount(notesRequest.getNoteId(),notesRequest.getAccountId());
    }

    public List<Notes> fetchNotes(final Long accountId, final boolean deleteFlag){
        return notesRepository.findNotesByAccountId(accountId, deleteFlag);
    }

    @Transactional
    public int deleteNote(final Long noteId, final Long accountId){
        return notesRepository.deleteNoteByIdAndAccount(noteId,accountId);
    }
}
