package pkg42.util.objects;

public class ObjectCheck {

    public int check;
    public ObjectProject project;


    public ObjectCheck(int check, ObjectProject project) {
        this.check = check;
        this.project = project;
    }

    @Override
    public String toString() {
        return "\ncheck: " + check + " project: " + project + "\n";
    }
}
