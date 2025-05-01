package br.inatel.trabalho.ejb.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "AUDIT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String recordCode;
    private String operation;
    private LocalDateTime timestamp;

    public void setOperation(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setRecordCode(String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setTimestamp(LocalDateTime parse) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
