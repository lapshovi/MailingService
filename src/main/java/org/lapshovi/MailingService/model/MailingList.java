package org.lapshovi.MailingService.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Proxy(lazy=false)
@Table(name = "mailinglist")
public class MailingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mailinglist_id")
    private Long id;

    @Column(name = "datetime_start")

    @JsonFormat(pattern = "dd/MM/yy HH:mm x", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime dateTimeStart;
    private String message;
    private String filter;

    @Column(name = "datetime_end")
    @JsonFormat(pattern = "dd/MM/yy HH:mm x", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime dateTimeEnd;

    public MailingList() {
    }

    public MailingList(OffsetDateTime dateTimeStart, String message, String filter, OffsetDateTime dateTimeEnd) {
        this.dateTimeStart = dateTimeStart;
        this.message = message;
        this.filter = filter;
        this.dateTimeEnd = dateTimeEnd;
    }

    public Long getId() {
        return id;
    }

    public OffsetDateTime getDateTimeStart() {
        return dateTimeStart;
    }

    public void setDateTimeStart(OffsetDateTime dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public OffsetDateTime getDateTimeEnd() {
        return dateTimeEnd;
    }

    public void setDateTimeEnd(OffsetDateTime dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }

    @Override
    public String toString() {
        return "MailingList{" +
                "id=" + id +
                ", dateTimeStart=" + dateTimeStart +
                ", message='" + message + '\'' +
                ", filter='" + filter + '\'' +
                ", dateTimeEnd=" + dateTimeEnd +
                '}';
    }
}
