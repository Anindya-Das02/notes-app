package in.das.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "notes")
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "note_id")
    private Long noteId;

    private String title;

    @Column(length = 2000)
    private String body;

    @Column(name = "created_dt",nullable = false,updatable = false)
    private Timestamp createdDate;

    @Column(name = "last_modified_dt", nullable = false)
    private Timestamp lastModifiedDate;

    @Column(name = "delete_flg")
    private boolean deleteFlag = false;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account createdBy;

}
