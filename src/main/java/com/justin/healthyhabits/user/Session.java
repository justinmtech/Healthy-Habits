package com.justin.healthyhabits.user;

import javax.persistence.*;

@Entity
public class Session {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int sessionId;
    //HttpServletRequest request;
    @OneToOne
    private User user;

    public Session() {}

    public Session(User user) {
        this.user = user;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public User getSiteUser() {
        return user;
    }

    public void setSiteUser(User user) {
        this.user = user;
    }
}
