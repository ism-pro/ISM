/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.showcase.crop;

import java.io.File;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.ism.event.CropErrorEvent;
import org.ism.event.CroppedEvent;
import org.ism.model.cropper.CroppedImage;
import org.ism.showcase.file.FileService;
import org.ism.showcase.jsf.util.JsfUtil;

/**
 *
 * @author r.hendrick
 */
@ManagedBean(name = "cropperView")
@SessionScoped
public class CropperView implements Serializable {

    public static final String PATH_RSC = File.separator + "ISM" + File.separator + "rsc";
    public static final String PATH_TODIR = File.separator + "img"
            + File.separator + "tmp" + File.separator;
    public static final String PATH_SAVE = PATH_RSC + PATH_TODIR;

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
        return String.valueOf(System.currentTimeMillis()) + "_" + String.valueOf(i);
    }

    public void handleCroppedImage(CroppedEvent event) {

        croppedImage = (org.ism.model.cropper.CroppedImage) event.getCroppedImage();
        if (croppedImage == null) {
            JsfUtil.addErrorMessage("Aucune image n'a été rognée !");
            return;
        }

        FileService.imgTmpRemoveOld(croppedImage);
        FileService.imgTmpCreate(croppedImage);
    }

    public void handleCropError(CropErrorEvent cropErrorEvent) {
        JsfUtil.addErrorMessage("Crop Error", cropErrorEvent.getCropError().getSize().msg);
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
