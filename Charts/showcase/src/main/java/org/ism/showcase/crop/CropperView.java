/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.showcase.crop;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import org.ism.util.Util;
import org.primefaces.model.CroppedImage;

/**
 *
 * @author r.hendrick
 */
@ManagedBean(name = "cropperView")
@SessionScoped
public class CropperView implements Serializable {

    public static final String PATH_RSC =  File.separator + "ISM" + File.separator + "rsc";
    public static final String PATH_TODIR = File.separator + "img"
                + File.separator + "tmp" + File.separator;
    public static final String PATH_SAVE = PATH_RSC  + PATH_TODIR;
    
    private CroppedImage croppedImage;
    private String newImageName;
    
    private CroppedImage croppedImage1;
    private String newImageName1;

    @PostConstruct
    public void init() {
    }

    //
    //
    //
    //
    //
    private String getRandomImageName() {
        int i = (int) (Math.random() * 100000);
        return String.valueOf(System.currentTimeMillis()) +  "_" + String.valueOf(i);
    }

    public void handleCrop() {
        if (croppedImage == null) {
            return;
        }

        setNewImageName(getRandomImageName());
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String newFileName =  PATH_SAVE + getNewImageName() + ".jpg";
        
        Properties prop = new Properties();
        try {
            prop.load(ec.getResourceAsStream("/WEB-INF/app-config.properties"));
        } catch (IOException ex) {
            Logger.getLogger(CropperView.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(croppedImage.getBytes(), 0, croppedImage.getBytes().length);
            imageOutput.close();
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cropping failed."));
            return;
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Cropping finished."));
    }

    public void handleCrop1() {
        if (croppedImage == null) {
            return;
        }

        setNewImageName1(getRandomImageName());
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String newFileName1 = externalContext.getRealPath("") + PATH_SAVE + getNewImageName() + ".jpg";
        Util.out(newFileName1);

        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName1));
            imageOutput.write(croppedImage.getBytes(), 0, croppedImage.getBytes().length);
            imageOutput.close();
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cropping failed."));
            return;
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Cropping finished."));
    }

    //
    //
    //
    //
    //
    public CroppedImage getCroppedImage() {
        return croppedImage;
    }

    public void setCroppedImage(CroppedImage croppedImage) {
        this.croppedImage = croppedImage;
    }

    public String getNewImageName() {
        return newImageName;
    }

    public void setNewImageName(String newImageName) {
        this.newImageName = newImageName;
    }

    public CroppedImage getCroppedImage1() {
        return croppedImage1;
    }

    public void setCroppedImage1(CroppedImage croppedImage1) {
        this.croppedImage1 = croppedImage1;
    }

    public String getNewImageName1() {
        return newImageName1;
    }

    public void setNewImageName1(String newImageName1) {
        this.newImageName1 = newImageName1;
    }


    
    
}
