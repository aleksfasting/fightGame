package model.field;

/**
 * Represents a section of the field.
 * 
 * The class is analogous to a node in a graph, where each section has an edge
 * to all the neighboring sections on the screen.
 */
public class Section {
    /**
     * The neighbors of the section.
     * Sequence: tl, t, tr, l, r, bl, b, br
     */
    private Section[] neighbors;
    private int xPosition;
    private int yPosition;
    public Section(int x, int y) {
        xPosition = x;
        yPosition = y;
        neighbors = new Section[8];
    }

    public void setNeighbor(int index, Section neighbor) {
        neighbors[index] = neighbor;
    }

    public Section[] getNeighbors() {
        return neighbors.clone();
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }
}