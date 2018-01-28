/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ism.model.cropper;

import com.google.gson.Gson;

/**
 *
 * @author r.hendrick
 */
public class CropError {

    private Integer code = 0;
    private SizeError size;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public SizeError getSize() {
        return size;
    }

    public void setSize(SizeError size) {
        this.size = size;
    }
    
    
    
    
    public static class SizeError {

        public Integer code = 0; 
        public long value = 0;
        public String  msg = ""; 
        public long min; 
        public long max;
    }
    
    
    
    public static CropError unparse(String cropStr){
        Gson g = new Gson();
        return (g.fromJson(cropStr, CropError.class)) ;
    }
    
}
