package propertyEditors;


import java.beans.PropertyEditorSupport;

public class UkrNameEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] parts = text.split("\\s");
        setValue(new UkrainianName(parts[0], parts[1], parts[2]));
    }
}
