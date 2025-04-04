package chunkloaders.pkg4450.pkgfinal.project;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Julianne Ong
 */
public class TerrainGenerator {
    public static int[][] getRandomHeightMap(long seed, int width, int height, int baseHeight){
        int[][] heightMap = new int[height][width];
        double frequency = 0.015;
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {      
                double nx = x/width - 0.5, ny = y/height - 0.5;
                double e = OpenSimplex2S.noise2(seed, nx * frequency, ny * frequency);
                e = OpenSimplex2S.noise2(seed, x * frequency, y * frequency)
                    +  0.5 * OpenSimplex2S.noise2(seed, 2 * nx, 2 * ny)
                    + 0.25 * OpenSimplex2S.noise2(seed, 4 * nx, 4 * ny);
                
                e = e / (1 + 0.5 + 0.25);
                //e = Math.pow(e, 4.00);
                e = e*10;
                e = e+baseHeight;
                heightMap[y][x] = (int) (e);
                System.out.print(heightMap[y][x] + ", ");
            }
            System.out.println();
        }
        
        return heightMap;
    }
}
