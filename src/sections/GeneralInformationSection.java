package sections;

import io.reader.IReader;
import io.writer.IWriter;

public class GeneralInformationSection extends Section {

    private String name;
    private String address;
    private String email;
    private String telephone;
    private String mobile;

    public GeneralInformationSection(String sectionTitle) {
        super(sectionTitle);
        name = address = email = telephone = mobile = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public boolean hasError() {
        return (name.isEmpty() || address.isEmpty() || email.isEmpty() ||
                telephone.isEmpty() || mobile.isEmpty());
    }

    public String getErrorDescription() {
        return "Some fields are empty. Please add some information.";
    }

    public void accept(IWriter writer) {
        writer.visit(this);
    }

    public void accept(IReader reader) {
        reader.visit(this);
    }

}
