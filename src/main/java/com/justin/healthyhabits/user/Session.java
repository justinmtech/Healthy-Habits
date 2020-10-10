package com.justin.healthyhabits.user;

import javax.persistence.*;

@Entity
public class Session {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int sessionId;
    //HttpServletRequest request;
    @OneToOne
    private SiteUser siteUser;

    public Session() {}

    public Session(SiteUser user) {
        this.siteUser = user;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public SiteUser getSiteUser() {
        return siteUser;
    }

    public void setSiteUser(SiteUser siteUser) {
        this.siteUser = siteUser;
    }
}
