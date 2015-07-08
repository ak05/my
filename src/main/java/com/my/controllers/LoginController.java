/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.my.controllers;

import com.my.services.UserService;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author anku0513
 */
@ManagedBean
@SessionScoped
@Controller
public class LoginController implements Serializable
{
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public UserService getUserService()
    {
        return userService;
    }

    public void setUserService(UserService userService)
    {
        this.userService = userService;
    }
    
    public String login()
    {
        System.out.println("userService = " + userService);
        if (userService.login(username, password))
        {
            LOGGER.debug("Access provided.");
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Access provided.", ""));
        }
        else
        {
            LOGGER.debug("Access denied.");
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Access denied.", ""));
        }

        return "";
    }
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private UserService userService;
    private String username;
    private String password;
}
