package engineer.trustmeimansoftware.algtheory.week08;

import java.util.ArrayList;
import java.util.Arrays;

public class DLX {
    public int cnt = 0;
    private DLXNode[] columnHeaders;
    private DLXNode h;

    public DLX(DLXNode h) {
        this.h = h;
    }

    public DLX(int size, ArrayList<int[]> subsets) {
        int offset = 1; //numbers in subset have to be offset by 1 because numbers start at 1, but array index at 0

        this.h = new DLXNode();
        // build column headers
        columnHeaders = newCircularDLXNodeRow(size);

        // first node refs h to the left and vice versa
        columnHeaders[0].L = this.h;
        this.h.R = columnHeaders[0];
        // last node refs h to the right and vice versa
        columnHeaders[size-1].R  = this.h;
        this.h.L = columnHeaders[size-1];

        // add each subset to dlx array
        for(int[] subset : subsets) {
            DLXNode[] row = newCircularDLXNodeRow(subset.length);
            // for each node, insert it at the correct column index to dlx matrix
            for(int i = 0; i < subset.length; i++) insertNode(subset[i] - offset, row[i]);
        }
    }

    public void insertNode(int columnIndex, DLXNode node) {
        DLXNode columnHeader = columnHeaders[columnIndex];
        node.C = columnHeader;
        // the last element is the one "up" to column header (aren't circular lists great?)
        // last element now refs inserted node
        columnHeader.U.D = node;
        // inserted node refs last element upwards
        node.U = columnHeader.U;

        // inserted Node now refs columnHeader downwards
        node.D = columnHeader;
        // columnHeader refs inserted Node upwards (this makes inserted Node the "new" last Node)
        columnHeader.U = node;
    }

    public static DLXNode[] newCircularDLXNodeRow(int size) {
        DLXNode[] row = new DLXNode[size];
        for(int i = 0; i < row.length; i++) row[i] = new DLXNode();
        DLXNode last = row[size-1];
        for(int i = 0; i < size; i++) {
            last.R = row[i]; // last node refs this node on the right side
            last.R.L = last; // this node (aka last.R) refs last node on the left
            last = last.R; // new last node is last node's left neighbor
        }
        return row;
    }


/**
 * Class DLXNode
 *   represents a matrix element of the cover matrix with value 1
 *   links go to up down left right neigbors, and column header
 *   can also be used as colm header or root of column headers
 *   matrix is sparsely coded
 *   try to do all operations very efficiently
 *   see:
 *      http://en.wikipedia.org/wiki/Dancing_Links
 *      http://arxiv.org/abs/cs/0011047
 */
static class DLXNode {       // represents 1 element or header
    DLXNode C;           // reference to column-header
    DLXNode L, R, U, D;  // left, right, up, down references
    DLXNode(){ C=L=R=U=D=this; } // supports circular lists
}

    /**
     * search tries to find and count all complete coverings of the DLX matrix.
     *        Is a recursive, depth-first, backtracking algorithm that finds
     *        all solutions to the exact cover problem encoded in the DLX matrix.
     *        each time all columns are covered, static long cnt is increased
     * @param k: number of level
     *
     */
    public void search (int k){ // finds & counts solutions
        if (h.R == h) {cnt++; return;}     // if empty: count & done
        DLXNode c = h.R;                   // choose next column c
        cover(c);                          // remove c from columns
        for (DLXNode r=c.D; r!=c; r=r.D){  // forall rows with 1 in c
            for (DLXNode j=r.R; j!=r; j=j.R) // forall 1-elements in row
                cover(j.C);                    // remove column
            search(k+1);                    // recursion
            for (DLXNode j=r.L; j!=r; j=j.L) // forall 1-elements in row
                uncover(j.C);                  // backtrack: un-remove
        }
        uncover(c);                        // un-remove c to columns
    }

    /**
     * cover "covers" a column c of the DLX matrix
     *       column c will no longer be found in the column list
     *       rows i with 1 element in column c will no longer be found
     *       in other column lists than c
     *       so column c and rows i are invisible after execution of cover
     * @param c: header element of column that has to be covered
     *
     */
    public void cover (DLXNode c){ // remove column c
        c.R.L = c.L ;                         // remove header
        c.L.R = c.R ;                         // .. from row list
        for (DLXNode i=c.D; i!=c; i=i.D)      // forall rows with 1
            for (DLXNode j=i.R; i!=j; j=j.R){   // forall elem in row
                j.D.U = j.U ;                     // remove row element
                j.U.D = j.D ;                     // .. from column list
            }
    }

    /**
     * uncover "uncovers" a column c of the DLX matrix
     *       all operations of cover are undone
     *       so column c and rows i are visible again after execution of uncover
     * @param c: header element of column that has to be uncovered
     *
     */
    public void uncover (DLXNode c){//undo remove col c
        for (DLXNode i=c.U; i!=c; i=i.U)      // forall rows with 1
            for (DLXNode j=i.L; i!=j; j=j.L){   // forall elem in row
                j.D.U = j ;                       // un-remove row elem
                j.U.D = j ;                       // .. to column list
            }
        c.R.L = c ;                           // un-remove header
        c.L.R = c ;                           // .. to row list
    }


}