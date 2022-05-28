# Lab02-Sudoku-Solver
The goal of this assignment is to demonstrate your mastery of the backtracking algorithm,  an algorithm used to solve problems incrementally. In this case you will be using it to solve a 9x9 sudoku puzzle.

# Example:
<pre><code> 
Input: board = {{"5","3",".",".","7",".",".",".","."},
                {"6",".",".","1","9","5",".",".","."},
                {".","9","8",".",".",".",".","6","."},
                {"8",".",".",".","6",".",".",".","3"},
                {"4",".",".","8",".","3",".",".","1"},
                {"7",".",".",".","2",".",".",".","6"},
                {".","6",".",".",".",".","2","8","."},
                {".",".",".","4","1","9",".",".","5"},
                {".",".",".",".","8",".",".","7","9"}}
                
Output: solution = {{"5","3","4","6","7","8","9","1","2"},
                    {"6","7","2","1","9","5","3","4","8"},
                    {"1","9","8","3","4","2","5","6","7"},
                    {"8","5","9","7","6","1","4","2","3"},
                    {"4","2","6","8","5","3","7","9","1"},
                    {"7","1","3","9","2","4","8","5","6"},
                    {"9","6","1","5","3","7","2","8","4"},
                    {"2","8","7","4","1","9","6","3","5"},
                    {"3","4","5","2","8","6","1","7","9"}}
</code></pre>

notes:
- inputBoard() accepts a char[][] array, so its entries must be char inputs and not String as in the above example input

example board hard code coordinates:

    board[0][0] = 5;
    board[0][1] = 3;
    board[0][4] = 7;

    board[1][0] = 6;
    board[1][3] = 1;
    board[1][4] = 9;
    board[1][5] = 5;

    board[2][1] = 9;
    board[2][2] = 8;
    board[2][7] = 6;

    board[3][0] = 8;
    board[3][4] = 6;
    board[3][8] = 3;

    board[4][0] = 4;
    board[4][3] = 8;
    board[4][5] = 3;
    board[4][8] = 1;

    board[5][0] = 7;
    board[5][4] = 2;
    board[5][8] = 6;

    board[6][1] = 6;
    board[6][6] = 2;
    board[6][7] = 8;

    board[7][3] = 4;
    board[7][4] = 1;
    board[7][5] = 9;
    board[7][8] = 5;

    board[8][4] = 8;
    board[8][7] = 7;
    board[8][8] = 9;


other example board hard code coordinates:

    board[0][1] = 6;
    board[0][7] = 3;

    board[1][1] = 2;
    board[1][2] = 7;
    board[1][3] = 1;

    board[2][0] = 5;
    board[2][2] = 4;
    board[2][3] = 6;
    board[2][8] = 9;

    board[3][2] = 8;
    board[3][4] = 9;

    board[4][0] = 9;
    board[4][3] = 4;
    board[4][4] = 5;
    board[4][5] = 6;
    board[4][8] = 2;

    board[5][4] = 1;
    board[5][6] = 3;

    board[6][0] = 4;
    board[6][5] = 7;
    board[6][6] = 9;
    board[6][8] = 3;

    board[7][5] = 4;
    board[7][6] = 8;
    board[7][7] = 1;

    board[8][1] = 7;
    board[8][7] = 2;