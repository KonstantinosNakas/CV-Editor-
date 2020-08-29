package sections.items;

import io.reader.IReader;
import io.writer.IWriter;

import java.util.Calendar;

public class CareerSummarySectionItem implements ISectionItem {

    private String companyName;
    private String jobTitle;
    private String errorDescription;
    private int date;

    public CareerSummarySectionItem() {
        companyName = jobTitle = "";
        date = Calendar.getInstance().get(Calendar.YEAR);
    }

    public boolean hasError(ISectionItem previous) {
        if (companyName.isEmpty() || jobTitle.isEmpty()) {
            errorDescription = "Some elements are empty. Please add information.";
            return true;
        } else if (previous != null && ((CareerSummarySectionItem)previous).date < date) {
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

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getDate() {
        return date;
    }

}
