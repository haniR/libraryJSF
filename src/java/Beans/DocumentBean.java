/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Daos.BookDao;
import java.io.File;
import java.io.FileOutputStream;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ASUS
 */
@ManagedBean
@RequestScoped
public class DocumentBean {

    private UploadedFile file;
    private BookDao bookDao = new BookDao();

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        try {
            if (file != null) {
                File file2 = new File("");
                String fullPath = file2.getAbsolutePath();
                String path = fullPath.substring(0, fullPath.lastIndexOf("\\")) + "\\webapps\\attachments\\";
                System.err.println(path);
                File f = new File(path + file.getFileName());
                FileOutputStream os = new FileOutputStream(f);
                os.write(file.getContents());
                os.close();
                bookDao.uploadFile(file.getFileName());
                FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   

}
