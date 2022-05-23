package org.lapshovi.MailingService.request;

public class MessageRequest {

    private Long id;
    private String phone;
    private String text;

    public MessageRequest() {
    }

    public MessageRequest(Long id, String phone, String text) {
        this.id = id;
        this.phone = phone;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getphone() {
        return phone;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }

    public String gettext() {
        return text;
    }

    public void settext(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "MessageRequest{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
