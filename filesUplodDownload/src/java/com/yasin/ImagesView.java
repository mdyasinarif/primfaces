/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yasin;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Students
 */
@ManagedBean
public class ImagesView {
     
    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        
            images.add("ChistiUdan.png");
            images.add("JillurQuran.png");
            images.add("QuranDarshanPart-1.png");
            images.add("QuranDarshanPart-2.png");
            images.add("Qurbani-2.png");
            images.add("SiamDarson.png");
        
    }
 
    public List<String> getImages() {
        return images;
    }
}
