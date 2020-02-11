package com.danbai.dblogin.client;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author DanBai
 */
@ConfigurationProperties("danbai.dblogin")
public class DBloginProperties {
    private String passwordManagement;
    private String serviceURL;

    public String getPasswordManagement() {
        return passwordManagement;
    }

    public void setPasswordManagement(String passwordManagement) {
        this.passwordManagement = passwordManagement;
    }

    public String getServiceURL() {
        return serviceURL;
    }

    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }
}
