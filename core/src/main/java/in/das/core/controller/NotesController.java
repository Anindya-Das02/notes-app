package in.das.core.controller;

import in.das.core.services.NotesService;
import in.das.entity.Notes;
import in.das.shared.models.NotesRequest;
import in.das.shared.models.NotesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes-app/notes")
@Slf4j
public class NotesController {

    @Autowired
    private NotesService notesService;

    @PutMapping("/create")
    public NotesResponse createNote(RequestEntity<NotesRequest> notesRequestEntity){
        log.info("Invoked NotesController::createNote");
        return notesService.createNote(notesRequestEntity.getBody());
    }

    @PutMapping("/update")
    public NotesResponse updateNote(RequestEntity<NotesRequest> notesRequestRequestEntity){
        log.info("Invoked NotesController::updateNote");
        return notesService.updateNote(notesRequestRequestEntity.getBody());
    }
}
