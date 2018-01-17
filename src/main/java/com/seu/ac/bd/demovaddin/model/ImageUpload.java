package com.seu.ac.bd.demovaddin.model;

import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ImageUpload implements Upload.Receiver, Upload.SucceededListener {

    private File file;
    private final Image image;

    public ImageUpload(Image image){
        this.image = image;
        this.image.setVisible(false);
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent event) {
        this.image.setVisible(true);
        this.image.setSource(new FileResource(file));
    }

    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        FileOutputStream fos = null;
        try{
            file = new File(filename);
            fos = new FileOutputStream(file);
        }catch(FileNotFoundException ex){
            new Notification("File not found \n",
                    ex.getLocalizedMessage(),
                    Notification.Type.ERROR_MESSAGE)
                    .show(Page.getCurrent());
        }
        return fos;
    }


}
