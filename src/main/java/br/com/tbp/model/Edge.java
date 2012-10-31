package br.com.tbp.model;

public class Edge {
    private Node src;
    private Node dest;
    private double distance;

    public Edge(Node src, Node dest, double distance) {
        super();
        this.src = src;
        this.dest = dest;
        this.distance = distance;
    }

    public Edge() {
    }

    public Node getSrc() {
        return src;
    }

    public void setSrc(Node src) {
        this.src = src;
    }

    public Node getDest() {
        return dest;
    }

    public void setDest(Node dest) {
        this.dest = dest;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dest == null) ? 0 : dest.hashCode());
        result = prime * result + ((src == null) ? 0 : src.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edge other = (Edge) obj;
        if (dest == null) {
            if (other.dest != null)
                return false;
        } else if (!dest.equals(other.dest))
            return false;
        if (src == null) {
            if (other.src != null)
                return false;
        } else if (!src.equals(other.src))
            return false;
        return true;
    }
}
