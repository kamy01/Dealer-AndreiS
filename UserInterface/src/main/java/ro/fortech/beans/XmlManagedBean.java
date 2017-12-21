package ro.fortech.beans;

import org.primefaces.model.UploadedFile;
import ro.fortech.services.XmlService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class XmlManagedBean {
    private UploadedFile file;
    @EJB
    private XmlService xmlService;

    public void upload() {
        if (file.getFileName().contains("car"))
            xmlService.registerCarFromXml(file);
        else
            xmlService.uploadUserXml(file);
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
}
