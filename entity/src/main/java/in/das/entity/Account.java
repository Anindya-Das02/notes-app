package in.das.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long accountId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "email_id", nullable = false, unique = true)
    private String emailId;

    private String password;

    @Column(name = "created_dt",nullable = false, updatable = false)
    private Timestamp createdDate;

    @OneToMany(mappedBy = "createdBy")
    private List<Notes> notes;

}
