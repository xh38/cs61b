import edu.princeton.cs.algs4.Picture;
import java.awt.Color;


public class SeamCarver {
    private Picture pic;
    private int width, height;
    public SeamCarver(Picture picture) {
        pic = picture;
        width = picture.width();
        height = picture.height();
    }

    public Picture picture() {
        return new Picture(pic);
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public double energy(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException();
        }
        Color left, right, up, down;
        if (x == 0) {
            left = pic.get(width - 1, y);
        } else {
            left = pic.get(x - 1, y);
        }

        if (x == width - 1) {
            right = pic.get(0, y);
        } else {
            right = pic.get(x + 1, y);
        }

        if (y == 0) {
            up = pic.get(x, height - 1);
        } else {
            up = pic.get(x, y - 1);
        }

        if (y == height - 1) {
            down = pic.get(x, 0);
        } else {
            down = pic.get(x, y + 1);
        }

        int rx = right.getRed() - left.getRed();
        int gx = right.getGreen() - left.getGreen();
        int bx = right.getBlue() - left.getBlue();

        int ry = down.getRed() - up.getRed();
        int gy = down.getGreen() - up.getGreen();
        int by = down.getBlue() - up.getBlue();

        return rx * rx + gx * gx + bx * bx + ry * ry + gy * gy + by * by;
    }

    public int[] findHorizontalSeam() {
        Picture newpic = new Picture(height, width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                newpic.set(i, j, pic.get(j, i));
            }
        }
        SeamCarver sc = new SeamCarver(newpic);
        int[] hseam = sc.findVerticalSeam();
        return hseam;
    }

    public int[] findVerticalSeam() {
//        int[][] path = new int[width][height];
//        int[] sumenergy = new int[width];
//        for (int i = 0; i < width; i++) {
//            path[i][height - 1] = i;
//            sumenergy[i] += energy(i, height - 1);
//        }
//        for (int i = height - 2; i > -1; i--) {
//            for (int j = 0; j < width; j++) {
//                double min1 = Double.MAX_VALUE;
//                for (int k = path[j][i + 1] - 1; k <= path[j][i + 1] + 1; k++) {
//                    if (k > -1 && k < width) {
//                        if (min1 > energy(k, i)) {
//                            min1 = energy(k, i);
//                            path[j][i] = k;
//                        }
//                    }
//                }
//                sumenergy[j] += min1;
//            }
//        }
//        int min_index = 0;
//        double min2 = Double.MAX_VALUE;
//        for (int i = 0; i < width; i++) {
//            if (min2 > sumenergy[i]) {
//                min_index = i;
//                min2 = sumenergy[i];
//            }
//        }
//
//        return path[min_index];

        double[][] sum_energy = new double[width][height];

        for (int i = 0; i < width; i++) {
            sum_energy[i][0] = energy(i, 0);
        }

        for (int i = 1; i < height; i++) {
            for (int j = 0; j < width; j++) {
                double min1 = Double.MAX_VALUE;
                for (int k = j - 1; k <= j + 1; k++) {
                    if (k > -1 && k < width) {
                        if (min1 > sum_energy[k][i - 1]) {
                            min1 = sum_energy[k][i - 1];
                        }
                    }
                    sum_energy[j][i] = min1 + energy(j, i);
                }
            }
        }
        int index = 0;
        double min2 = Double.MAX_VALUE;
        for (int i = 0; i < width; i++) {
            if (sum_energy[i][height - 1] < min2) {
                min2 = sum_energy[i][height - 1];
                index = i;
            }
        }
        int[] vseam = new int[height];
        vseam[height - 1] = index;
        for (int i = height - 2; i > -1; i--) {
            double min3 = Double.MAX_VALUE;
            int temp = index;
            for (int j = index - 1; j <= index + 1; j++) {
                if (min3 > sum_energy[j][i]) {
                    min3 = sum_energy[j][i];
                    temp = j;
                }
            }
            index = temp;
            vseam[i] = index;
        }
        return vseam;
    }

    public void removeHorizontalSeam(int[] seam) {
        if (seam.length != width) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < seam.length - 1; i++) {
            if ((seam[i + 1] - seam[i]) * (seam[i + 1] - seam[i]) > 1) {
                throw new IllegalArgumentException();
            }
        }
        Picture temp = new Picture(width, height - 1);
        for (int i = 0; i < width; i++) {
            int conut = 0;
            for (int j = 0; j < height; j++) {
                if (j != seam[i]) {
                    temp.set(i, conut, pic.get(i, j));
                    conut++;
                }
            }
        }
        pic = temp;
        height--;
    }

    public void removeVerticalSeam(int[] seam) {
        if (seam.length != height) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < seam.length - 1; i++) {
            if ((seam[i + 1] - seam[i]) * (seam[i + 1] - seam[i]) > 1) {
                throw new IllegalArgumentException();
            }
        }
        Picture temp = new Picture(width - 1, height);
        for (int i = 0; i < height; i++) {
            int conut = 0;
            for (int j = 0; j < width; j++) {
                if (j != seam[i]) {
                    temp.set(conut, i, pic.get(j, i));
                    conut++;
                }
            }
        }
        pic = temp;
        width--;
    }

}
