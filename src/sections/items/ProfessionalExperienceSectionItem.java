package sections.items;

import io.reader.IReader;
import io.writer.IWriter;

import java.util.Calendar;

import sections.ListSection;

public class ProfessionalExperienceSectionItem implements ISectionItem {

    private String companyName;
    private String jobTitle;
    private String errorDescription;
    private int startDate;
    private int endDate;
    private String responsibilities;
    private ListSection achievements;

    public ProfessionalExperienceSectionItem() {
        companyName = jobTitle = responsibilities = "";
        startDate = endDate = Calendar.getInstance().get(Calendar.YEAR);
        achievements = new ListSection("Achievements");
    }

    public boolean hasError(ISectionItem previous) {
        if (companyName.isEmpty() || jobTitle.isEmpty() ||
                responsibilities.isEmpty()) {
            errorDescription = "Some fields are empty. Please add information.";
            return true;
        } else if (startDate > endDate) {
            errorDescription = "Start and end date are in wrong order: " +
                startDate + ", " + endDate;
            return true;
        } else if (previous != null &&
                ((ProfessionalExperienceSectionItem)previous).startDate < endDate) {
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

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setResponsibilities(String responsibilities) {
        // we do not want to allow black lines to make reading from file easier
        responsibilities = responsibilities.replace("\n\n", "\n");
        if (responsibilities.endsWith("\n")) {
            responsibilities =
                responsibilities.substring(0, responsibilities.length() - 1);
        }
        this.responsibilities = responsibilities;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public ListSection getAchievementsList() {
        return achievements;
    }

}
