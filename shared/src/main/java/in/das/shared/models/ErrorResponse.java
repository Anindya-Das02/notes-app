package in.das.shared.models;


import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class ErrorResponse {
    private String errorCode;
    private String message;
    private String uuid;
    private Timestamp timestamp;
}
