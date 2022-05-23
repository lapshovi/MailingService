package org.lapshovi.MailingService.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Proxy(lazy=false)
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @Column(name = "datetime_sending")
    private OffsetDateTime dateTimeSending;

    private String status;

    @OneToOne
    @JoinColumn(name = "mailinglist_id")
    private MailingList mailingList;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Message() {
    }

    public Message(OffsetDateTime dateTimeSending, String status, MailingList mailingList, Client client) {
        this.dateTimeSending = dateTimeSending;
        this.status = status;
        this.mailingList = mailingList;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public OffsetDateTime getDateTimeSending() {
        return dateTimeSending;
    }

    public void setDateTimeSending(OffsetDateTime dateTimeSending) {
        this.dateTimeSending = dateTimeSending;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MailingList getMailingList() {
        return mailingList;
    }

    public void setMailingList(MailingList mailingList) {
        this.mailingList = mailingList;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", dateTimeSending=" + dateTimeSending +
                ", status='" + status + '\'' +
                ", mailingList=" + mailingList +
                ", client=" + client +
                '}';
    }
}
