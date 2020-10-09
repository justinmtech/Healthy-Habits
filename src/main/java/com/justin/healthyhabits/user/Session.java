package com.justin.healthyhabits.user;

import javax.persistence.*;

@Entity
public class Session {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int sessionId;
    //HttpServletRequest request;
    @OneToOne
    private SiteUsers siteUser;

    //public HttpServletRequest getRequest() {
    //    return request;
    //}

    //public void setRequest(HttpServletRequest request) {
    //    this.request = request;
    //}

    public Session() {}

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
