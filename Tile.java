/*
  By Andrew Miller

   A tile. A tile can be either in an 'on' or 'off' state. The state is switched each time the tile,
   or a surrounding tile, is 'flipped'. Each tile contains each of the surrounding tiles so they can
   be flipped when selected.
 */
class Tile {
    private Tile top;
    private Tile bottom;
    private Tile left;
    private Tile right;
    private Tile diTopLeft;
    private Tile diTopRight;
    private Tile diBottomLeft;
    private Tile getDiBottomRight;
    private boolean state;
    private boolean clicked;

    public Tile(boolean initialState){
        state = initialState;
    }

    public boolean getState(){
        return state;
    }

    public void setConnections(Tile t, Tile b, Tile l, Tile r, Tile dtl, Tile dtr, Tile dbl, Tile dbr){
        top = t;
        bottom = b;
        left = l;
        right = r;
        diTopLeft = dtl;
        diTopRight = dtr;
        diBottomLeft = dbl;
        getDiBottomRight = dbr;
    }

    public void flipAction(){
        clicked = !clicked;
        flip();
        if (top != null) top.flip();
        if (bottom != null) bottom.flip();
        if (left != null) left.flip();
        if (right != null) right.flip();
        if (diTopLeft != null) diTopLeft.flip();
        if (diTopRight != null) diTopRight.flip();
        if (diBottomLeft != null) diBottomLeft.flip();
        if (getDiBottomRight != null) getDiBottomRight.flip();
    }

    private void flip(){
        state = !state;
    }

    public boolean isClicked(){
        return clicked;
    }

}
