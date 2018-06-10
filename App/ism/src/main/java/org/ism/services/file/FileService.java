/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.services.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.application.ResourceHandler;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.ism.jsf.util.JsfUtil;
import org.ism.model.cropper.CroppedImage;
import org.ism.services.Util;
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
     * @param croppedImage  a cropped image type 
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
     * @param croppedImage a cropped image
     * @return string corresponding to the cropped image
     */
    public static String filenameComplete(CroppedImage croppedImage) {
        return FilenameUtils.removeExtension(croppedImage.getOriginalFilename())
                + croppedImage.getFile().ext;
    }

    /**
     * Move a temporary file from the temporary directory to the new one at
     * absoluteDestination
     *
     * @param croppedImage a cropped image 
     * @param absoluteDestinationPath an absolute destination path
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
     * @param croppedImage a cropped image
     */
    public static void tmpMoveSmqNC(CroppedImage croppedImage) {
        tmpMove(croppedImage, NC_REQUEST);
    }

    /**
     * Allow to copy file to NC_REQUEST directory than delete it
     *
     * @param croppedImage a cropped image 
     */
    public static void tmpCopyDeleteToSmqNC(CroppedImage croppedImage) {
        tmpCopyDelete(croppedImage, NC_REQUEST);
    }

    /**
     * Convert a path resource to system path
     *
     * @param path a system path
     * @return  convert to system path
     */
    public static String toSys(String path) {
        return SYS + path.replace("\\", "/");
    }

    /**
     * Allow to find a specified name of file starting by fileStart in a
     * absolute directory defined by absoluteDirectory
     *
     * @param fileStart the beginin of the file to search
     * @param absoluteDirectory the absolute directory
     * @return a array of file il any file found
     */
    public static File[] fileStartWith(String fileStart, String absoluteDirectory) {
        File dir = new File(absoluteDirectory);
        File[] matches = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith(fileStart);
            }
        });

        return matches;
    }

    public static void deleteImgFromSMQNC(String filename) {
        File bfile = new File(NC_REQUEST + SEP + filename);
        if (bfile.exists()) {
            JsfUtil.addErrorMessage("FileService : delete Image From SMQ NC", filename + " : does not exist in " + bfile.getParent());
        }
        String dir = bfile.getParent();

        File[] filesToDel = fileStartWith(filename, dir);
        for (File f : filesToDel) {
            if (f.isFile()) {
                f.delete();
            }
        }
    }

    /// ////////////////////////////////////////////////////////////////////////
    /// 
    ///
    /// File opérations
    ///
    /// ////////////////////////////////////////////////////////////////////////
    /**
     * Convenient method for write an input stream to a file
     *
     * @param initialStream stream to be write to new filename
     * @param filename a path and name which to be create
     */
    public static void writeToFileSystem(InputStream initialStream, String filename) {
        File targetFile = new File(filename);
        targetFile.getParentFile().mkdirs();
        try {
            java.nio.file.Files.copy(
                    initialStream,
                    targetFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(JsfUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        IOUtils.closeQuietly(initialStream);
    }

    /**
     * Utility method that demonstrates how to write an input stream to the
     * server's local file system.
     *
     * @param byteArrayInputStream byte to rite to file system
     * @param exportFile name of the file
     */
    public static void writeToFileSystem(ByteArrayInputStream byteArrayInputStream, String exportFile) {

        //Use the Java I/O libraries to write the exported content to the file system.
        byte byteArray[] = new byte[byteArrayInputStream.available()];

        //Create a new file that will contain the exported result.
        File file = new File(exportFile);
        file.getParentFile().mkdirs();
        try (FileOutputStream fileOutputStream = new FileOutputStream(file); ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(byteArrayInputStream.available())) {
            int x = byteArrayInputStream.read(byteArray, 0, byteArrayInputStream.available());

            byteArrayOutputStream.write(byteArray, 0, x);
            byteArrayOutputStream.writeTo(fileOutputStream);

            //Close streams.
            byteArrayInputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Permet de supprimer le rapport si il existe
     *
     * @param fieldname nom de chemin absolu du rapport
     * @return true if successful delete
     */
    public static Boolean fileDelete(String fieldname) {
        File f = new File(fieldname);
        return f.delete();
    }

    /**
     * writeResourceToFileSystem allow to read a resource in the current
     * application defined by absoluteFilePathname then write it in the
     * corresponding resouce place.
     *
     * @param absoluteFilePathname the name and file of the resource example
     * "img/ism/ism.png" for application logo in the resources directory
     * directories img and ism then you have the file
     * @param rewrite if true the file is neccessarly rewrite
     * @param parentPath path of the parent
     * @return the full path name create
     */
    public static String writeResourceToFileSystem(String absoluteFilePathname, Boolean rewrite, String parentPath) {
        String absFilePathName = absoluteFilePathname;
        File file = new File(absFilePathName);
        String filename = absFilePathName;

        // Check if the file does not already exist
        if (!file.isFile() || rewrite) {
            // maybe it is a resources
            ResourceHandler resourceHandler = FacesContext.getCurrentInstance().getApplication().getResourceHandler();
            InputStream in = null;
            try {
                in = resourceHandler.createResource(absFilePathName).getInputStream();
            } catch (IOException ex) {
                Logger.getLogger(FileService.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (in == null) {
                JsfUtil.out(filename + " is not a resources of application...");
                return null;
            }

            // If it is an resource now check if it is not already create
            String rscname = parentPath + absFilePathName;
            file = new File(rscname);
            if (!file.isFile() || rewrite) {
                JsfUtil.out("File " + rscname + " does not exist or need to been recreate ... ");
                writeToFileSystem(in, rscname);
            } else {
                //JsfUtil.out("File " + rscname + " already exist....");
            }

            // Définit le nom du fichier
            filename = rscname;
        }
        return filename;
    }

    /**
     * Surcharge writeResouceToFileSystem which default does not rewrite the
     * file
     *
     * @param absoluteFilePathname the name and file of the resource example
     * "img/ism/ism.png" for application logo in the resources directory
     * directories img and ism then you have the file
     * @return the full path name create
     */
    public static String writeResourceToFileSystem(String absoluteFilePathname) {
        return writeResourceToFileSystem(absoluteFilePathname, false, "/ISM/rsc/");
    }

    public static String writeResourceToFileSystem(String absoluteFilePathname, String parentPath) {
        return writeResourceToFileSystem(absoluteFilePathname, false, parentPath);
    }

    public static String writeResourceToFileSystem(String absoluteFilePathname, Boolean rewrite) {
        return writeResourceToFileSystem(absoluteFilePathname, rewrite, "/ISM/rsc/");
    }
}
