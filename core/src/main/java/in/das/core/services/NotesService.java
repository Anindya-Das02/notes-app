package in.das.core.services;

import in.das.entity.Account;
import in.das.entity.Notes;
import in.das.repository.impl.NotesRepoImpl;
import in.das.shared.enums.NoteType;
import in.das.shared.exception.BadRequestException;
import in.das.shared.exception.ValidationException;
import in.das.shared.models.NotesRequest;
import in.das.shared.models.NotesResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class NotesService {

    @Autowired
    private NotesRepoImpl notesRepoImpl;

    public NotesResponse createNote(NotesRequest notesRequest){
        Notes note = new Notes();
        validateNote(notesRequest);
        populateNoteDetails(note, notesRequest, NoteType.NOTE_CREATE);
        Notes noteResult = notesRepoImpl.saveNote(note);
        if(noteResult == null){
            return buildNoteFailureResponse(NoteType.NOTE_CREATE,"unable to save note in database");
        }
        return NotesResponse.builder()
                .noteId(noteResult.getNoteId())
                .createdBy(notesRequest.getAccountId())
                .noteCreateStatus(true)
                .message("Note saved successfully")
                .createdDate(noteResult.getCreatedDate())
                .lastModifiedDate(noteResult.getLastModifiedDate())
                .build();
    }

    public NotesResponse updateNote(NotesRequest notesRequest){
        Notes noteEntity = new Notes();
        // find if note exists for given note_id & account_id; if no record exists throw error
        Optional<Notes> noteDetails = notesRepoImpl.fetchNoteByIdAndAccountId(notesRequest);
        if(noteDetails.isEmpty()){
            log.info("no note record found!");
            return buildNoteFailureResponse(NoteType.NOTE_UPDATE,"No Note records found for given details");
        }
        populateNoteDetails(noteDetails.get(), notesRequest, NoteType.NOTE_UPDATE);
        Notes noteResult = notesRepoImpl.saveNote(noteDetails.get());
        if(noteResult == null){
            return buildNoteFailureResponse(NoteType.NOTE_UPDATE,"unable to save note in database");
        }
        return NotesResponse.builder()
                .noteId(noteResult.getNoteId())
                .createdBy(notesRequest.getAccountId())
                .noteUpdateStatus(true)
                .message("Note updated successfully")
                .createdDate(noteResult.getCreatedDate())
                .lastModifiedDate(noteResult.getLastModifiedDate())
                .build();
    }

    private void validateNote(final NotesRequest notesRequest){
        if (notesRequest.getAccountId() == null || StringUtils.isBlank(String.valueOf(notesRequest.getAccountId())) || StringUtils.isBlank(notesRequest.getTitle())){
            log.error("Create note validation failed");
            throw new ValidationException("Note validation error! Please check \"createdBy\" or \"Title\" property");
        }
    }

    private void populateNoteDetails(final Notes note ,final NotesRequest noteRequest, NoteType noteType){
        Timestamp timeStamp = Timestamp.valueOf(LocalDateTime.now());
        Account account = new Account();
        account.setAccountId(noteRequest.getAccountId());

        if(NoteType.NOTE_CREATE == noteType){
            note.setCreatedDate(timeStamp);
        }
        if(noteType == NoteType.NOTE_UPDATE){
            note.setNoteId(noteRequest.getNoteId());
        }

        note.setTitle(noteRequest.getTitle() != null ? noteRequest.getTitle() : note.getTitle());
        note.setBody(noteRequest.getBody() != null ? noteRequest.getBody() : note.getBody());
        note.setCreatedBy(account);

        note.setLastModifiedDate(timeStamp);
        note.setDeleteFlag(false);
    }

    private NotesResponse buildNoteFailureResponse(final NoteType noteType, final String message){
        log.info("building note-failure-response failureType:{}_ERROR", noteType);
        NotesResponse notesResponse = new NotesResponse();
        notesResponse.setMessage(message);
        if(noteType == NoteType.NOTE_CREATE) {
            notesResponse.setNoteCreateStatus(false);
        }
        else if(noteType == NoteType.NOTE_UPDATE){
            notesResponse.setNoteUpdateStatus(false);
        }
        else if(noteType == NoteType.NOTE_DELETE) {
            notesResponse.setNoteDeleteStatus(false);
        }
        return notesResponse;
    }
}
