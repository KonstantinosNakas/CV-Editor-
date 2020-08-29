package sections.items;

import io.reader.IReader;
import io.writer.IWriter;

import java.util.Calendar;

public class EducationSectionItem implements ISectionItem{

    private String qualification;
    private String educationType;
    private String establishment;
    private String location;
    private String errorDescription;
    private int date;

    public EducationSectionItem(String educationType) {
        this.educationType = educationType;
        qualification = establishment = location = "";
        date = Calendar.getInstance().get(Calendar.YEAR);
    }

    public boolean hasError(ISectionItem previous) {
        if (qualification.isEmpty() || establishment.isEmpty() || location.isEmpty()) {
            errorDescription = "Some fields are empty. Please add information.";
            return true;
        } else if (previous != null && ((EducationSectionItem)previous).date < date) {
            errorDescription = "Some dates are wrong. Please fix their order.";
            return true;
        }
        return false;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void accept(IWriter writer) {
        writer.visit(this);
    }

    public void accept(IReader reader) {
        reader.visit(this);
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getQualification() {
        return qualification;
    }

    public String getEducationType() {
        return educationType;
    }

    public void setEstablishment(String establishment) {
        this.establishment = establishment;
    }

    public String getEstablishment() {
        return establishment;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }

}
