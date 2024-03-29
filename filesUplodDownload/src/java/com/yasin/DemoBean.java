/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yasin;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.servlet.http.Part;

/**
 *
 * @author Students
 */
@ManagedBean
@SessionScoped
public class DemoBean {
    List<Student> students = new ArrayList<>();
    private Part file1;
    private Part file2;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public Part getFile2() {
        return file2;
    }

    public void setFile2(Part file2) {
        this.file2 = file2;
    }
    
    public String upload() throws IOException{
        
        file1.write("E:\\my git\\primfaces\\filesUplodDownload\\web\\resources\\images\\"+getFilename(file1));
        file2.write("E:\\my git\\primfaces\\filesUplodDownload\\web\\resources\\images\\"+getFilename(file2));
        students.add(new Student("Mr"+Math.random()*100, getFilename(file1)));
        students.add(new Student("Mr"+Math.random()*100, getFilename(file2)));
        System.out.println("Students:"+students.size());
        return "success";
        
    }
    private static String getFilename(Part part){
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf("=")+1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/')+1).substring(filename.lastIndexOf('\\')+1);
            }
        }
        return null;
    }
}
