/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.model.cropper;

import com.google.gson.Gson;
import java.util.Date;

/**
 *
 * @author r.hendrick
 */
public class CroppedImage extends org.primefaces.model.CroppedImage {

    String originalFilenameOld = null;
    String blob = null;
    String blobIn = null;
    CanvasData canvasData = null;
    ContainerData containerData = null;
    CropBoxData cropBoxData = null;
    Data data = null;
    FileDesc file = null;
    ImageData imageData = null;

    //
    //
    //
    //
    //
    //
    public static CroppedImage unparse(String croppedImageStr) {
        Gson g = new Gson();
        return g.fromJson(croppedImageStr, CroppedImage.class);
    }
    
    
    //
    //
    //
    //
    public String getOriginalFilenameOld() {
        return originalFilenameOld;
    }

    public void setOriginalFilenameOld(String originalFilenameOld) {
        this.originalFilenameOld = originalFilenameOld;
    }

    public String getBlob() {
        return blob;
    }

    public void setBlob(String blob) {
        this.blob = blob;
    }

    public String getBlobIn() {
        return blobIn;
    }

    public void setBlobIn(String blobIn) {
        this.blobIn = blobIn;
    }

    public CanvasData getCanvasData() {
        return canvasData;
    }

    public void setCanvasData(CanvasData canvasData) {
        this.canvasData = canvasData;
    }

    public ContainerData getContainerData() {
        return containerData;
    }

    public void setContainerData(ContainerData containerData) {
        this.containerData = containerData;
    }

    public CropBoxData getCropBoxData() {
        return cropBoxData;
    }

    public void setCropBoxData(CropBoxData cropBoxData) {
        this.cropBoxData = cropBoxData;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public FileDesc getFile() {
        return file;
    }

    public void setFile(FileDesc file) {
        this.file = file;
    }

    public ImageData getImageData() {
        return imageData;
    }

    //
    //
    //
    //
    public void setImageData(ImageData imageData) {
        this.imageData = imageData;
    }

    //
    //
    //
    //
    //
    //
    //
    //
    public static class CanvasData {

        public Double left = 0d; //the offset left of the canvas
        public Double top = 0d; //the offset top of the canvas
        public Double width = 0d; //the width of the canvas
        public Double height = 0d; //the height of the canvas
        public Double naturalWidth = 0d; //the natural width of the canvas (read only)
        public Double naturalHeight = 0d; //the natural height of the canvas (read only)
    }

    public static class ContainerData {

        public Double width = 0d;
        public Double height = 0d;
    }

    public static class CropBoxData {

        public Double left = 0d; // the offset left of the crop box
        public Double top = 0d; //the offset top of the crop box
        public Double width = 0d; //the width of the crop box
        public Double height = 0d; //the height of the crop box
    }

    public static class Data {

        public Double x = 0d; // the offset left of the cropped area
        public Double y = 0d; // the offset top of the cropped area
        public Double width = 0d; // the width of the cropped area
        public Double height = 0d; // the height of the cropped area
        public Double rotate = 0d; // the rotated degrees of the image
        public Double scaleX = 0d; // the scaling factor to apply on the abscissa of the image
        public Double scaleY = 0d; // the scaling factor to apply on the ordinate of the image
    }

    public static class ImageData {

        public Double left = 0d; // the offset left of the image
        public Double top = 0d; // the offset top of the image
        public Double width = 10d; // the width of the image
        public Double height = 10d; // the height of the image
        public Integer naturalWidth = 4096; // the natural width of the image
        public Integer naturalHeight = 4096; // the natural height of the image
        public Double aspectRatio = 0d; // the aspect ratio of the image
        public Double rotate = 0d; // the rotated degrees of the image if rotated
        public Double scaleX = 1d; // the scaling factor to apply on the abscissa of the image if scaled
        public Double scaleY = 1d; // the scaling factor to apply on the ordinate of the image if scaled

    }

    public static class FileDesc {

        public Long lastModified;
        public Date lastModifiedDate;
        public String name;
        public Integer size;
        public String type;

        public String filename;
        public String ext;
    }
}
