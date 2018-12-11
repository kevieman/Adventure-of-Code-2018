import java.util.List;

public class D5_AlchemicalReduction {
    private List<String> lines;
    private StringBuilder input;

    public static void main(String[] args) {
        new D5_AlchemicalReduction("assets", "Day5_Input.txt");
    }

    private D5_AlchemicalReduction(String location, String file) {
        lines = new LineReader(location, file).getLines();
        input  = new StringBuilder();

        for (String line : lines) {
            input.append(line);
        }

        System.out.println(reactingPolymer(input).length());
        System.out.println(getMinStringSize());
    }

    private StringBuilder reactingPolymer(StringBuilder str) {
        boolean redo = false;
        for (int i = 0; i < str.length(); i++) {
            if ( i == str.length() - 1)
                break;
            Character c1 = str.charAt(i);
            Character c2 = str.charAt(i+1);
            if (Character.isUpperCase(c1) && Character.isLowerCase(c2) && c1.equals(Character.toUpperCase(c2))){
                str.deleteCharAt(i+1);
                str.deleteCharAt(i);
                redo = true;
            } else if (Character.isLowerCase(c1) && Character.isUpperCase(c2)  && c1.equals(Character.toLowerCase(c2))) {
                str.deleteCharAt(i+1);
                str.deleteCharAt(i);
                redo = true;
            }
        }
        if (redo)
            reactingPolymer(str);

        return str;
    }

    private int getMinStringSize() {
        int minLenght = 0;
        String[] alphabet = "abcdefghijklmnopqrstuvwxyz".split("");

        for (String chr : alphabet) {
            String str = input.toString();
            str = str.replace(chr.toLowerCase(), "");
            str = str.replace(chr.toUpperCase(), "");
            str = reactingPolymer(new StringBuilder(str)).toString();
            if (minLenght == 0 || str.length() < minLenght)
                minLenght = str.length();
        }

        return minLenght;
    }

}
