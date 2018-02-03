/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.services.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
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
    public static final String SYS = ResourceBundle.getBundle(BUNDLE).getString("sys");
    public static final String SEP = File.separator;
    

    public static final String ISM = toSys(ResourceBundle.getBundle(BUNDLE).getString("ism")); //ism=ISM

    public static final String RSC = toSys(ResourceBundle.getBundle(BUNDLE).getString("rsc")); ///ISM/rsc
    public static final String TMP = toSys(ResourceBundle.getBundle(BUNDLE).getString("tmp")); ///ISM/rsc/tmp
    public static final String IMG = toSys(ResourceBundle.getBundle(BUNDLE).getString("img")); ///ISM/rsc/img

    public static final String NC_REQUEST = toSys(ResourceBundle.getBundle(BUNDLE).getString("nc_request")); ///ISM/rsc/img/ncr

    /**
     * Remove filename contain in the directory img_tmp.
     *
     * @param filename like filename.ext
     */
    public static void tmpRemove(String filename) {
        String path = TMP + SEP + filename;
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    public static void tmpRemoveOld(CroppedImage croppedImage) {
        if (croppedImage.getOriginalFilenameOld() == null || croppedImage.getOriginalFilenameOld().isEmpty()) {
            JsfUtil.addErrorMessage("FileService : imgTmpRemoveOld", "Old Cropped file name does not exist !");
            return;
        }
        tmpRemove(croppedImage.getOriginalFilenameOld());
    }

    /**
     * Image Temporarly Create allow to create a file image defined by
     * croppedImage. Note preview file is not remove !
     *
     * @param croppedImage
     */
    public static void imgCreate(CroppedImage croppedImage) {
        String path = TMP + SEP
                + FilenameUtils.removeExtension(croppedImage.getOriginalFilename())
                + croppedImage.getFile().ext;

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

    /**
     * Renvoie le nom de fichier avec l'extension
     *
     * @param croppedImage
     */
    public static String filenameComplete(CroppedImage croppedImage) {
        return FilenameUtils.removeExtension(croppedImage.getOriginalFilename())
                + croppedImage.getFile().ext;
    }

    /**
     * Move a temporary file from the temporary directory to the new one at
     * absoluteDestination
     *
     * @param croppedImage
     * @param absoluteDestinationPath
     */
    public static void tmpMove(CroppedImage croppedImage, String absoluteDestinationPath) {
        try {
            File afile = new File(TMP + SEP + filenameComplete(croppedImage));
            File nf = new File(absoluteDestinationPath + SEP + afile.getName());
            nf.mkdirs();
            nf.delete();
            nf = new File(absoluteDestinationPath + SEP + afile.getName());
            if (!afile.renameTo(nf)) {
                JsfUtil.addErrorMessage("FileService : tmpMove", "Unable to move file : " + afile.getAbsolutePath());
            }
            afile.delete();
        } catch (Exception e) {
           JsfUtil.addErrorMessage("FileService : tmpMove", "Following error occured : " + e.getMessage());
        }
    }

    public static void tmpCopyDelete(CroppedImage croppedImage, String absoluteDestinationPath) {
        InputStream inStream = null;
        OutputStream outStream = null;

        try {
            File afile = new File(TMP + SEP + filenameComplete(croppedImage));
            File bfile = new File(absoluteDestinationPath + SEP + afile.getName());

            inStream = new FileInputStream(afile);
            bfile.mkdir();
            outStream = new FileOutputStream(bfile);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0) {

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();

            //delete the original file
            afile.delete();

        } catch (IOException e) {
            JsfUtil.addErrorMessage("FileService : tmpCopyDelete", e.getLocalizedMessage());
        }
    }

    /**
     * Allow to move from temporary file to abasolute destination
     *
     * @param croppedImage
     */
    public static void tmpMoveSmqNC(CroppedImage croppedImage) {
        tmpMove(croppedImage, NC_REQUEST);
    }

    /**
     * Allow to copy file to NC_REQUEST directory than delete it
     *
     * @param croppedImage
     */
    public static void tmpCopyDeleteToSmqNC(CroppedImage croppedImage) {
        tmpCopyDelete(croppedImage, NC_REQUEST);
    }

    /**
     * Convert a path resource to système apth
     *
     * @param path
     */
    public static String toSys(String path) {
        return SYS + path.replace("\\", "/");
    }
}
