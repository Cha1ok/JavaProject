package area_triangle;

public class Pair<X,Y> {
    private X xpoint;
    private Y ypoint;
    Pair(X x,Y y){
        this.xpoint=x;
        this.ypoint=y;
    }

    public X getXpoint() {
        return xpoint;
    }

    public Y getYpoint() {
        return ypoint;
    }

    public void setXpoint(X xpoint) {
        this.xpoint = xpoint;
    }

    public void setYpoint(Y ypoint) {
        this.ypoint = ypoint;
    }
}
