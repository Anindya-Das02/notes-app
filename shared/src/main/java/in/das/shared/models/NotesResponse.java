package in.das.shared.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotesResponse {
    private Long noteId;
    private Long createdBy;
    private Timestamp createdDate;
    private Timestamp lastModifiedDate;
    private Boolean noteCreateStatus;
    private Boolean noteUpdateStatus;
    private Boolean noteDeleteStatus;
    private Boolean deleteFlag;
    private String message;
}
