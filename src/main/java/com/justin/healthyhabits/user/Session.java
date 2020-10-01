package com.justin.healthyhabits.user;

import javax.persistence.*;

@Entity
public class Session {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int sessionId;
    @OneToOne
    private SiteUsers siteUser;


    public Session(SiteUsers siteUser) {
        this.siteUser = siteUser;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public SiteUsers getSiteUser() {
        return siteUser;
    }

    public void setSiteUser(SiteUsers siteUser) {
        this.siteUser = siteUser;
    }
}
