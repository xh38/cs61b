import edu.princeton.cs.algs4.Picture;
import java.awt.Color;
import java.lang.IllegalArgumentException;


public class SeamCarver {
    private Picture pic;
    private int width, height;
    public SeamCarver(Picture picture) {
        pic = picture;
        width = picture.width();
        height = picture.height();
    }

    public Picture picture() {
        return pic;
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
        int[] Hseam = sc.findVerticalSeam();
        return Hseam;
    }

    public int[] findVerticalSeam() {
        int[] Vseam = new int[height];
        double min1 = Double.MAX_VALUE;

        for (int i = 0; i < width; i++) {
            if (energy(i, 0) < min1) {
                Vseam[0] = i;
                min1 = energy(i, 0);
            }
        }

        for (int i = 1; i < height; i++) {
            int j = Vseam[i - 1];
            double min2 = Double.MAX_VALUE;
            for (int k = j - 1; k <= j + 1; k++) {
                if(k > -1 && k < width) {
                    if (energy(k, i) < min2) {
                        Vseam[i] = k;
                        min2 = energy(k, i);
                    }
                }
            }
        }
        return Vseam;
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