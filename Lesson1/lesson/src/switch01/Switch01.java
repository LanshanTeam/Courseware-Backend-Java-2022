package switch01;

public class Switch01 {
    public static void main(String[] args) {
        String flag = "A";
        switch (flag ) {
            case "A":
                System.out.println("A");
                break;
            case "B":
                System.out.println("B");
                break;
            default:
                System.out.println("C");
        }
    }
}
