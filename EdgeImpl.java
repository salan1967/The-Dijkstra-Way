package a6;

public class EdgeImpl implements Edge {
    /* You will include the implementations for any edge methods you need in this file. */

    /*Hint: Make sure you update the Edge interface in Edge.java when you add a new method implementation
    in EdgeImpl.java, and vice-versa.  getName() in Node.java and NodeImpl.java is an example.  Also, files in
    previous homeworks (e.g., BST.java and BSTImpl.java in homework 3) are good examples of
    interfaces and their implementations.
     */

    /*Also, any edge fields you want to add for the object should go in this file.  */
    private double wt;
    private String destin;
    private double dist;


    public EdgeImpl (double ww, String des){
        wt = ww;
        destin = des;
        dist = ww;
    }


    @Override
    public Double getww() {
        return wt;
    }

    @Override
    public String getdest() {

        return destin;
    }

    @Override
    public Double getdist() {
        return dist;
    }

    @Override
    public void changedist(double nmbr) {
        dist += nmbr;

    }


}
