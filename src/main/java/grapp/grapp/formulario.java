package grapp.grapp;

import java.io.File;

public class formulario {
    //@Size(min=2,max=10)
    private String text;
    private File img;
    
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public File getImg() {
        return img;
    }
    public void setImg(File img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Formulario [img=" + img + ", text=" + text + "]";
    }    
    
}

