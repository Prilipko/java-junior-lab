package propertyEditors;

import org.springframework.context.support.GenericXmlApplicationContext;

public class TestPropertyEditor {
    UkrainianName ukrainianName;

    public void setUkrainianName(UkrainianName ukrainianName) {
        this.ukrainianName = ukrainianName;
    }

    public UkrainianName getUkrainianName() {
        return ukrainianName;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("property-editors.xml");
        ctx.refresh();

        System.out.println(
                ctx.getBean("myNameTest",TestPropertyEditor.class)
                        .getUkrainianName());
    }
}
