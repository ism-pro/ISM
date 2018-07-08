/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.view.app;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "viewSessionCounter")
@SessionScoped
public class ViewSessionCounter {

    private static final long serialVersionUID = 20120925L;
    
  
    public Integer getSessionCounter() {
        return 1;
    }

    public Integer getTotalActiveSessionsAuthenticated() {
        return 1;
    }

    public List<Object> getActiveStaff() {
        return new ArrayList<>();
    }
    
    public long currentSessionDuration(){
        return 1;
    }
    
    public long staffSessionDuration(Object staff){
        return 1;
    }
    
    public Boolean removeSessionActive(Object staff){
        return true;
    }
    
    
    /// ////////////////////////////////////////////////////////////////////////
    ///
    ///
    /// GETTER / SETTER
    ///
    ///
    /// ////////////////////////////////////////////////////////////////////////

}
