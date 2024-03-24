package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : UniquePathsII
 * Creator : Edward
 * Date : Nov, 2017
 * Description : 63. Unique Paths II
 */
public class UniquePathsII {
    /**
     * Follow up for "Unique Paths":

     Now consider if some obstacles are added to the grids. How many unique paths would there be?

     An obstacle and empty space is marked as 1 and 0 respectively in the grid.

     For example,
     There is one obstacle in the middle of a 3x3 grid as illustrated below.

     [
     [0,0,0],
     [0,1,0],
     [0,0,0]
     ]
     The total number of unique paths is 2.

     time : O(m * n)
     space : O(n)

     * @param obstacleGrid
     * @return
     */





************************************************************************************

public int uniquePathsWithObstacles(int[][] obstacleGrid) {
	int m = obstacleGrid.length, n = obstacleGrid[0].length;
	int[][] path = new int[m][n];

	for (int i = 0; i < m; i++) {
		if (obstacleGrid[i][0] == 1)  {
			path[i][0] = 0;
			//on the first column, if there is an obstacle, the rest are blocked. 
			//no need to continue.
			break;  
		} else
			path[i][0] = 1;
	}
	
	for (int j = 0; j < n; j++) {
		if (obstacleGrid[0][j] == 1)  {
			path[0][j] = 0;
			//First row, once obstacle found, the rest are blocked.
			break; 
		} else
			path[0][j] = 1;
	}
	
	for (int i = 1; i < m; i++) {
		for (int j = 1; j < n; j++) {
			if (obstacleGrid[i][j] == 1) 
				path[i][j] = 0;
			else
				path[i][j] = path[i-1][j] + path[i][j-1];
		}
	}
	return path[m-1][n-1];
}


************************************************************************************




    
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int length = obstacleGrid[0].length;
        int[] res = new int[length];
        res[0] = 1;
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    res[j] = 0;
                } else if (j > 0) {
                    res[j] += res[j - 1];
                }
            }
        }
        return res[length - 1];
    }
}
