package com.saraad.leetcode.dailycode;

/**
 * All rights Reserved, Designed By SARAAD
 *
 * @version V1.0
 * @Title: IslandPerimeter
 * @Package:com.saraad.leetcode.dailycode
 * @Description:
 * @author: saraad
 * @date: 2020/10/30 0:26
 * @Copyright: 2020  Inc. All rights reserved.
 * PROJECT FOR PRACTICE
 */
public class IslandPerimeter {

    public static void main(String[] args) {
        int[][] grid = {{1,1}};
        System.out.println(islandPerimeter(grid));
    }

    public static int islandPerimeter(int[][] grid) {
        if (grid.length==0){
            return 0;
        }
        int sum = 0;
        for (int i=0; i<grid.length; i++){
            for (int j=0; j<grid[i].length;j++){
                if (grid[i][j]==1) {
                    sum+=4;
                    //上
                    if ((i-1)>=0 && grid[i-1][j]==1) {
                        sum-=1;
                    }
                    //下
                    if ((i+1)<grid.length &&  grid[i+1][j]==1 ){
                        sum-=1;
                    }
                    //左
                    if ((j-1)>=0 && grid[i][j-1]==1) {
                        sum-=1;
                    }
                    //右
                    if ((j+1)<grid[i].length && grid[i][j+1]==1 ){
                        sum-=1;
                    }
                }
            }
        }
        return sum;
    }

}
