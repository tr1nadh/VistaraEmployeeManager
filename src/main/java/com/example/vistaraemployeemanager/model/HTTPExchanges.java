package com.example.vistaraemployeemanager.model;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HTTPExchanges {
    
    private final HttpServletRequest req;
    private final HttpServletResponse res;

    public HTTPExchanges(HttpServletRequest req, HttpServletResponse res) {
        this.req = req;
        this.res = res;
    }

    public HttpServletRequest getReq() {
        return req;
    }

    public HttpServletResponse getRes() {
        return res;
    }

}
