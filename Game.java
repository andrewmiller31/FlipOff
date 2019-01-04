/*
  By Andrew Miller

  This class is used to define how a given tile game will start and end. It makes more sense in the
  context of the app but I decided to include it in this upload anyway because it may be helpful.
 */
import java.io.Serializable;

public class Game implements Serializable {
    private final boolean[] goalState;
    private final boolean[] startState;
    private final int par;
    private boolean solved;
    private String grade;

    /*
       NOTE - start is the array of buttons which are already pressed, not what
       the board looks like.

       It made sense to do it this way because of the way I was designing the puzzles but now I
       realize it can be confusing to think of it this way at first for an outsider.
      */
    public Game(boolean[] start, boolean[] goal, int par){
        startState = start;
        goalState = goal;
        this.par = par;
        grade = "?";
    }

    public boolean isSolved() {
        return solved;
    }

    public String getGrade() {
        return grade;
    }

    public boolean[] getStartState(){
        return startState;
    }

    public boolean[] getGoalState(){
        return goalState;
    }

    public String solve(int moves){
        int score;
        if(moves < par) score = 0;
        else score = moves - par;
        String newGrade = calcGrade(score);
        if (solved){
            if (compareGrades(newGrade)) grade = newGrade;
        }
        else {
            solved = true;
            grade = calcGrade(score);
        }

        return newGrade;
    }

    private static String calcGrade(int s){
        if (s == 0) return "A+";
        else if (isBetween(s,1,2)) return "A";
        else if (isBetween(s,3,4)) return "A-";
        else if (isBetween(s,5,6)) return "B+";
        else if (isBetween(s,7,10)) return "B";
        else if (isBetween(s,11,15)) return "B-";
        else if (isBetween(s,16,20)) return "C+";
        else if (isBetween(s,21,25)) return "C";
        else if (isBetween(s,26,30)) return "C-";
        else if (isBetween(s,31,40)) return "D+";
        else if (isBetween(s,41,50)) return "D";
        else return "D-";
    }

    private boolean compareGrades(String newGrade){
        return gradeValue(newGrade) > gradeValue(grade);
    }

    public int gradeValue(String g){
        switch (g) {
            case "A+":
                return 10;
            case "A":
                return 9;
            case "A-":
                return 8;
            case "B+":
                return 7;
            case "B":
                return 6;
            case "B-":
                return 5;
            case "C+":
                return 4;
            case "C":
                return 3;
            case "C-":
                return 2;
            case "D+":
                return 1;
            case "D":
                return 0;
        }
        return -1;
    }

    public static String calcWeightedGrade(int s){
        switch (s){
            case 10: return "A+";
            case 9: return "A";
            case 8: return "A-";
            case 7: return "B+";
            case 6: return "B";
            case 5: return "B-";
            case 4: return "C+";
            case 3: return "C";
            case 2: return "C-";
            case 1: return "D+";
            case 0: return "D";
            default: return "?";
        }
    }

    private static boolean isBetween(int x, int low, int high){
        return x >= low && x <= high;
    }
}
