package com.apps.andrewmiller.tiles.TilesGame;
/*
  By Andrew Miller

  The board. The game is designed to have the tiles in a square formation and it should work for any
  dimensions.
 */
public class Board {
    private final Tile[] tiles;
    private final boolean[] goalState;

    public Board(boolean[] start, boolean[] goal, int width, int height){
        tiles = new Tile[start.length];
        goalState = goal;

        for (int i = 0; i < tiles.length; i++){
            tiles[i] = new Tile(false);
        }

        initConnections(width,height);

        for (int i = 0; i < start.length; i++){
            if (start[i]) flip(i);
        }
    }

    public boolean flip(int t){
        tiles[t].flipAction();
        return checkWin();
    }

    public boolean[] getState(){
        boolean state[] = new boolean[tiles.length];
        for (int i = 0; i < tiles.length; i++){
            state[i] = tiles[i].getState();
        }
        return state;
    }

    public boolean[] findClicked() {
        boolean[] clicked = new boolean[tiles.length];
        for (int i = 0; i < tiles.length; i++){
            clicked[i] = tiles[i].isClicked();
        }
        return clicked;
    }

    private void initConnections(int w,int h){
        Tile t, b, l, r, dtl, dtr, dbl, dbr;
        for (int i = 0; i < tiles.length; i++){
            if (i < w) t = null;
            else t = tiles[i-w];

            if (i >= w*(h-1)) b = null;
            else b = tiles[i+w];

            if (i % w == 0) l = null;
            else l = tiles[i-1];

            if ((i+1) % w == 0) r = null;
            else r = tiles[i+1];

            if (l != null && t != null) dtl = tiles[i-1-w];
            else dtl = null;

            if (r != null && t != null) dtr = tiles[i+1-w];
            else dtr = null;

            if (l != null && b != null) dbl = tiles[i-1+w];
            else dbl = null;

            if (r != null && b != null) dbr = tiles[i+1+w];
            else dbr = null;

            tiles[i].setConnections(t,b,l,r,dtl,dtr,dbl,dbr);
        }
    }

    private boolean checkWin(){
        for (int i = 0; i < tiles.length; i++){
            if (tiles[i].getState() != goalState[i]) return false;
        }
        return true;
    }
}
