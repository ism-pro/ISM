/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.services.file;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.ism.jsf.util.JsfUtil;
import org.ism.model.cropper.CroppedImage;
import org.ism.util.UtilImage;

/**
 *
 * @author r.hendrick
 */
public class FileService {

    public static final String BUNDLE = "/src";
    public static final String SEP = File.separator;
    ;
    
    public static final String ism = ResourceBundle.getBundle(BUNDLE).getString("ism"); //ism=ISM

    public static final String rsc = ResourceBundle.getBundle(BUNDLE).getString("rsc"); ///ISM/rsc
    public static final String img = ResourceBundle.getBundle(BUNDLE).getString("img"); ///ISM/rsc/img
    public static final String img_tmp = ResourceBundle.getBundle(BUNDLE).getString("img_tmp"); ///ISM/rsc/img/tmp
    public static final String nc_request = ResourceBundle.getBundle(BUNDLE).getString("nc_request"); ///ISM/rsc/img/ncr

    /**
     * Remove filename contain in the directory img_tmp.
     *
     * @param filename like filename.ext
     */
    public static void imgTmpRemove(String filename) {
        String path = img_tmp + SEP + filename;
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }
    
    
    public static void imgTmpRemoveOld(CroppedImage croppedImage) {
        if(croppedImage.getOriginalFilenameOld()==null || croppedImage.getOriginalFilenameOld().isEmpty()){
            JsfUtil.addErrorMessage("FileService : imgTmpRemoveOld", "Old Cropped file name does not exist !");
            return;
        }
        imgTmpRemove(croppedImage.getOriginalFilenameOld());
    }
    

    /**
     * Image Temporarly Create allow to create a file image defined by
     * croppedImage.
     * Note preview file is not remove !
     *
     * @param croppedImage
     */
    public static void imgTmpCreate(CroppedImage croppedImage) {
        String path = img_tmp + SEP
                + FilenameUtils.removeExtension(croppedImage.getOriginalFilename())
                + croppedImage.getFile().ext;;

        File fileOut = new File(path);
        if (!fileOut.exists()) {
            fileOut.mkdirs();
            try {
                fileOut.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, ex);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "File Service [imgTmp.Create]", ex.getMessage()));
                return;
            }
        }

        try {
            byte[] imageData = UtilImage.blobToByte(croppedImage.getBlob());
            UtilImage.byteToImage(imageData, fileOut);
        } catch (IOException ex) {
            Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "File Service [imgTmp.Create]", ex.getMessage()));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "File Service [imgTmp.Create]", "Image Temporarly Uploaod"));

    }


}
