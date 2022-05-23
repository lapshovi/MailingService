package org.lapshovi.MailingService.model;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Proxy(lazy=false)
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @Column(name = "phone_number")
    @Pattern(regexp = "^7\\d{10}$", message = "Номер телефона должен быть формата: 7XXXXXXXXXX")
    private String phone;

    private String tag;

    @Generated(GenerationTime.NEVER)
    @Column(name = "operators_code")
    private String operatorsCode;

    @Column(name = "time_zone")
    @Pattern(regexp = "^[+\\-]([0-9]|10|11|12)(:[0-5][0-9])?$", message = "Часовой пояс должен быть формата: +1:00")
    private String timeZone;

    public Client() {
    }

    public Client(String phone, String tag, String timeZone) {
        this.phone = phone;
        this.tag = tag;
        this.timeZone = timeZone;
    }

    public Long getId() {
        return id;
    }

    public String getphone() {
        return phone;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getOperatorsCode() {
        return operatorsCode;
    }

    public void setOperatorsCode(String operatorsCode) {
        this.operatorsCode = operatorsCode;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", tag='" + tag + '\'' +
                ", operatorsCode='" + operatorsCode + '\'' +
                ", timeZone='" + timeZone + '\'' +
                '}';
    }
}
