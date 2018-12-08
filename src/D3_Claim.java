public class D3_Claim {
    private int id;
    private int fromLeft;
    private int fromTop;
    private int width;
    private int height;

    public D3_Claim(String line){
        String[] a1 = line.split(" @ ");
        String[] a2 = a1[1].split(": ");
        String[] a3 = a2[0].split(",");
        String[] a4 = a2[1].split("x");

        id = Integer.parseInt(a1[0]);
        fromLeft = Integer.parseInt(a3[0]);
        fromTop = Integer.parseInt(a3[1]);
        width = Integer.parseInt(a4[0]);
        height = Integer.parseInt(a4[1]);
    }

    public int getId() {return id;}

    public int[] getTopLeft() {return new int[]{fromLeft, fromTop};}
    public int[] getTopRight() {return new int[]{fromLeft + width, fromTop};}
    public int[] getBottomLeft() {return new int[]{fromLeft, fromTop + height};}
    public int[] getBottomright() {return new int[]{fromLeft + width, fromTop + height};}
}
