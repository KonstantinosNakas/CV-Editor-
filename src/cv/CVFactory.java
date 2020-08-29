package cv;

import java.util.ArrayList;

public class CVFactory {

    public ArrayList<String> getCVTypesList() {
        ArrayList<String> result = new ArrayList<String>();

        result.add("Create a new chronological CV");
        result.add("Create a new functional CV");
        result.add("Create a new combined CV");

        return result;
    }

    public CV createCV(String description) {
        if (isChronologicalCV(description)) {
            return new ChronologicalCV();
        } else if (isFunctionalCV(description)) {
            return new FunctionalCV();
        } else if (isCombinedCV(description)) {
            return new CombinedCV();
        }
        return null;
    }

    private boolean isCombinedCV(String description) {
        return description.equals("Create a new combined CV") || description.equals("cv.CombinedCV");
    }

    private boolean isFunctionalCV(String description) {
        return description.equals("Create a new functional CV") || description.equals("cv.FunctionalCV");
    }

    private boolean isChronologicalCV(String description) {
        return description.equals("Create a new chronological CV") || description.equals("cv.ChronologicalCV");
    }

}
