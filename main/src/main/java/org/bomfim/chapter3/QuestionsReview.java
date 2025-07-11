package org.bomfim.chapter3;

public class QuestionsReview {

    public static void main(String[] args) {
        String zooStatus = "Closed";
        int visitors = switch (zooStatus) {
            case String s when s.equals("Open") -> 10;
            case Object s when s != null && !s.equals("") -> 20;
            case null -> {
                yield 30;
            }
            default -> 40;
        };
        System.out.println(visitors);

        switchDefaultOrder();
        getFish("abc");
    }

    private static void switchDefaultOrder() {
        String instrument = "violin";
        final String CELLO = "cello";
        String viola = "viola";
        int p = -1;
        switch (instrument) {
            case "bass":
                break;
            case CELLO:
                p++;
            default:
                p++;
            case "VIOLIN":
                p++;
            case "viola":
                ++p;
                break;
        }
        System.out.print(p);
    }

    static void getFish(Object fish) {
        if (!(fish instanceof String guppy))
            System.out.print("Eat!");
//        else if (!(fish instanceof String guppy)) { //DOES NOT COMPILE
//        }
//        System.out.print("Swim!");
    }

//    double dance(Object speed) {
//        return switch (speed) {
//            case 5 -> {yield 4}; //DOES NOT COMPILE
//            case 10 -> 8; //DOES NOT COMPILE
//            case 15, 20 -> 12; //DOES NOT COMPILE
//            default -> 20;
//            case null -> 16; //DOES NOT COMPILE
//        };
//    }
}
