/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carsFinder.action;

/**
 *
 * @author rianddy
 */
import common.Constants;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;
public class BasicAction {

    public String getCookieValue(String name, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
        javax.servlet.http.Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (!cookie.getName().equals(name)) {
                    continue;
                }
                return cookie.getValue();
            }
        }
       
        if(name.equals(Constants.SESSION_USER)){
            String username=getSession(Constants.SESSION_USER, request);
            if(username==null){
                String uId = UUID.randomUUID().toString();
                saveCookie(Constants.SESSION_USER, uId, Constants.BROWSER_COOKIE_MAX_AGE, response);
                return username;
            }else{
                saveCookie(Constants.SESSION_USER, username, Constants.BROWSER_COOKIE_MAX_AGE, response);
                return username;
            }
        }
        return null;
    }

    public void saveCookie(String name, String value, int maxAge, javax.servlet.http.HttpServletResponse response) {
        javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie(name,
                value);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
    
    public String getSession(String name, javax.servlet.http.HttpServletRequest request){
        HttpSession session = request.getSession();
        return (String)session.getAttribute(name);
    }
}
