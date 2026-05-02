package com.aastha.colorassistapp;

import android.graphics.Color;
import java.util.ArrayList;
import java.util.List;

public class ColorNameFinder {

    public static class ColorName {
        public String name;
        public String simpleName;
        public int r, g, b;
        public double L, a, b_lab;

        public ColorName(String name, String simpleName, int r, int g, int b) {
            this.name = name;
            this.simpleName = simpleName;
            this.r = r;
            this.g = g;
            this.b = b;
            
            double[] lab = rgbToLab(r, g, b);
            this.L = lab[0];
            this.a = lab[1];
            this.b_lab = lab[2];
        }
    }

    private static final List<ColorName> COLOR_LIST = initColorList();

    private static List<ColorName> initColorList() {
        List<ColorName> list = new ArrayList<>();
        // Basic Colors
        list.add(new ColorName("Black", "Black", 0, 0, 0));
        list.add(new ColorName("White", "White", 255, 255, 255));
        list.add(new ColorName("Red", "Red", 255, 0, 0));
        list.add(new ColorName("Lime", "Green", 0, 255, 0));
        list.add(new ColorName("Blue", "Blue", 0, 0, 255));
        list.add(new ColorName("Yellow", "Yellow", 255, 255, 0));
        list.add(new ColorName("Cyan", "Blue", 0, 255, 255));
        list.add(new ColorName("Magenta", "Purple", 255, 0, 255));
        list.add(new ColorName("Silver", "Gray", 192, 192, 192));
        list.add(new ColorName("Gray", "Gray", 128, 128, 128));
        list.add(new ColorName("Maroon", "Red", 128, 0, 0));
        list.add(new ColorName("Olive", "Green", 128, 128, 0));
        list.add(new ColorName("Green", "Green", 0, 128, 0));
        list.add(new ColorName("Purple", "Purple", 128, 0, 128));
        list.add(new ColorName("Teal", "Green", 0, 128, 128));
        list.add(new ColorName("Navy", "Blue", 0, 0, 128));

        // Extended Colors (100+)
        list.add(new ColorName("Alice Blue", "Blue", 240, 248, 255));
        list.add(new ColorName("Antique White", "White", 250, 235, 215));
        list.add(new ColorName("Aquamarine", "Blue", 127, 255, 212));
        list.add(new ColorName("Azure", "White", 240, 255, 255));
        list.add(new ColorName("Beige", "White", 245, 245, 220));
        list.add(new ColorName("Bisque", "White", 255, 228, 196));
        list.add(new ColorName("Blanched Almond", "White", 255, 235, 205));
        list.add(new ColorName("Blue Violet", "Purple", 138, 43, 226));
        list.add(new ColorName("Brown", "Brown", 165, 42, 42));
        list.add(new ColorName("Burly Wood", "Brown", 222, 184, 135));
        list.add(new ColorName("Cadet Blue", "Blue", 95, 158, 160));
        list.add(new ColorName("Chartreuse", "Green", 127, 255, 0));
        list.add(new ColorName("Chocolate", "Brown", 210, 105, 30));
        list.add(new ColorName("Coral", "Orange", 255, 127, 80));
        list.add(new ColorName("Cornflower Blue", "Blue", 100, 149, 237));
        list.add(new ColorName("Cornsilk", "White", 255, 248, 220));
        list.add(new ColorName("Crimson", "Red", 220, 20, 60));
        list.add(new ColorName("Dark Blue", "Blue", 0, 0, 139));
        list.add(new ColorName("Dark Cyan", "Blue", 0, 139, 139));
        list.add(new ColorName("Dark Golden Rod", "Yellow", 184, 134, 11));
        list.add(new ColorName("Dark Gray", "Gray", 169, 169, 169));
        list.add(new ColorName("Dark Green", "Green", 0, 100, 0));
        list.add(new ColorName("Dark Khaki", "Yellow", 189, 183, 107));
        list.add(new ColorName("Dark Magenta", "Purple", 139, 0, 139));
        list.add(new ColorName("Dark Olive Green", "Green", 85, 107, 47));
        list.add(new ColorName("Dark Orange", "Orange", 255, 140, 0));
        list.add(new ColorName("Dark Orchid", "Purple", 153, 50, 204));
        list.add(new ColorName("Dark Red", "Red", 139, 0, 0));
        list.add(new ColorName("Dark Salmon", "Orange", 233, 150, 122));
        list.add(new ColorName("Dark Sea Green", "Green", 143, 188, 143));
        list.add(new ColorName("Dark Slate Blue", "Purple", 72, 61, 139));
        list.add(new ColorName("Dark Slate Gray", "Gray", 47, 79, 79));
        list.add(new ColorName("Dark Turquoise", "Blue", 0, 206, 209));
        list.add(new ColorName("Dark Violet", "Purple", 148, 0, 211));
        list.add(new ColorName("Deep Pink", "Pink", 255, 20, 147));
        list.add(new ColorName("Deep Sky Blue", "Blue", 0, 191, 255));
        list.add(new ColorName("Dim Gray", "Gray", 105, 105, 105));
        list.add(new ColorName("Dodger Blue", "Blue", 30, 144, 255));
        list.add(new ColorName("Fire Brick", "Red", 178, 34, 34));
        list.add(new ColorName("Floral White", "White", 255, 250, 240));
        list.add(new ColorName("Forest Green", "Green", 34, 139, 34));
        list.add(new ColorName("Fuchsia", "Purple", 255, 0, 255));
        list.add(new ColorName("Gainsboro", "Gray", 220, 220, 220));
        list.add(new ColorName("Ghost White", "White", 248, 248, 255));
        list.add(new ColorName("Gold", "Yellow", 255, 215, 0));
        list.add(new ColorName("Golden Rod", "Yellow", 218, 165, 32));
        list.add(new ColorName("Green Yellow", "Green", 173, 255, 47));
        list.add(new ColorName("Honey Dew", "White", 240, 255, 240));
        list.add(new ColorName("Hot Pink", "Pink", 255, 105, 180));
        list.add(new ColorName("Indian Red", "Red", 205, 92, 92));
        list.add(new ColorName("Indigo", "Purple", 75, 0, 130));
        list.add(new ColorName("Ivory", "White", 255, 255, 240));
        list.add(new ColorName("Khaki", "Yellow", 240, 230, 140));
        list.add(new ColorName("Lavender", "Purple", 230, 230, 250));
        list.add(new ColorName("Lavender Blush", "White", 255, 240, 245));
        list.add(new ColorName("Lawn Green", "Green", 124, 252, 0));
        list.add(new ColorName("Lemon Chiffon", "Yellow", 255, 250, 205));
        list.add(new ColorName("Light Blue", "Blue", 173, 216, 230));
        list.add(new ColorName("Light Coral", "Red", 240, 128, 128));
        list.add(new ColorName("Light Cyan", "Blue", 224, 255, 255));
        list.add(new ColorName("Light Golden Rod Yellow", "Yellow", 250, 250, 210));
        list.add(new ColorName("Light Gray", "Gray", 211, 211, 211));
        list.add(new ColorName("Light Green", "Green", 144, 238, 144));
        list.add(new ColorName("Light Pink", "Pink", 255, 182, 193));
        list.add(new ColorName("Light Salmon", "Orange", 255, 160, 122));
        list.add(new ColorName("Light Sea Green", "Green", 32, 178, 170));
        list.add(new ColorName("Light Sky Blue", "Blue", 135, 206, 250));
        list.add(new ColorName("Light Slate Gray", "Gray", 119, 136, 153));
        list.add(new ColorName("Light Steel Blue", "Blue", 176, 196, 222));
        list.add(new ColorName("Light Yellow", "Yellow", 255, 255, 224));
        list.add(new ColorName("Lime Green", "Green", 50, 205, 50));
        list.add(new ColorName("Linen", "White", 250, 240, 230));
        list.add(new ColorName("Medium Aqua Marine", "Green", 102, 205, 170));
        list.add(new ColorName("Medium Blue", "Blue", 0, 0, 205));
        list.add(new ColorName("Medium Orchid", "Purple", 186, 85, 211));
        list.add(new ColorName("Medium Purple", "Purple", 147, 112, 219));
        list.add(new ColorName("Medium Sea Green", "Green", 60, 179, 113));
        list.add(new ColorName("Medium Slate Blue", "Purple", 123, 104, 238));
        list.add(new ColorName("Medium Spring Green", "Green", 0, 250, 154));
        list.add(new ColorName("Medium Turquoise", "Blue", 72, 209, 204));
        list.add(new ColorName("Medium Violet Red", "Purple", 199, 21, 133));
        list.add(new ColorName("Midnight Blue", "Blue", 25, 25, 112));
        list.add(new ColorName("Mint Cream", "White", 245, 255, 250));
        list.add(new ColorName("Misty Rose", "White", 255, 228, 225));
        list.add(new ColorName("Moccasin", "Yellow", 255, 228, 181));
        list.add(new ColorName("Navajo White", "White", 255, 222, 173));
        list.add(new ColorName("Old Lace", "White", 253, 245, 230));
        list.add(new ColorName("Olive Drab", "Green", 107, 142, 35));
        list.add(new ColorName("Orange", "Orange", 255, 165, 0));
        list.add(new ColorName("Orange Red", "Orange", 255, 69, 0));
        list.add(new ColorName("Orchid", "Purple", 218, 112, 214));
        list.add(new ColorName("Pale Golden Rod", "Yellow", 238, 232, 170));
        list.add(new ColorName("Pale Green", "Green", 152, 251, 152));
        list.add(new ColorName("Pale Turquoise", "Blue", 175, 238, 238));
        list.add(new ColorName("Pale Violet Red", "Pink", 219, 112, 147));
        list.add(new ColorName("Papaya Whip", "White", 255, 239, 213));
        list.add(new ColorName("Peach Puff", "Yellow", 255, 218, 185));
        list.add(new ColorName("Peru", "Brown", 205, 133, 63));
        list.add(new ColorName("Pink", "Pink", 255, 192, 203));
        list.add(new ColorName("Plum", "Purple", 221, 160, 221));
        list.add(new ColorName("Powder Blue", "Blue", 176, 224, 230));
        list.add(new ColorName("Rosy Brown", "Brown", 188, 143, 143));
        list.add(new ColorName("Royal Blue", "Blue", 65, 105, 225));
        list.add(new ColorName("Saddle Brown", "Brown", 139, 69, 19));
        list.add(new ColorName("Salmon", "Orange", 250, 128, 114));
        list.add(new ColorName("Sandy Brown", "Brown", 244, 164, 96));
        list.add(new ColorName("Sea Green", "Green", 46, 139, 87));
        list.add(new ColorName("Sea Shell", "White", 255, 245, 238));
        list.add(new ColorName("Sienna", "Brown", 160, 82, 45));
        list.add(new ColorName("Sky Blue", "Blue", 135, 206, 235));
        list.add(new ColorName("Slate Blue", "Blue", 106, 90, 205));
        list.add(new ColorName("Slate Gray", "Gray", 112, 128, 144));
        list.add(new ColorName("Snow", "White", 255, 250, 250));
        list.add(new ColorName("Spring Green", "Green", 0, 255, 127));
        list.add(new ColorName("Steel Blue", "Blue", 70, 130, 180));
        list.add(new ColorName("Tan", "Brown", 210, 180, 140));
        list.add(new ColorName("Thistle", "Purple", 216, 191, 216));
        list.add(new ColorName("Tomato", "Red", 255, 99, 71));
        list.add(new ColorName("Turquoise", "Blue", 64, 224, 208));
        list.add(new ColorName("Violet", "Purple", 238, 130, 238));
        list.add(new ColorName("Wheat", "Brown", 245, 222, 179));
        list.add(new ColorName("White Smoke", "White", 245, 245, 245));
        list.add(new ColorName("Yellow Green", "Green", 154, 205, 50));

        // Earth Tones / Browns / Grays for better accuracy
        list.add(new ColorName("Ferro", "Brown", 123, 79, 67)); // #7B4F43 - exact match
        list.add(new ColorName("Coffee", "Brown", 111, 78, 55));
        list.add(new ColorName("Raw Umber", "Brown", 130, 102, 68));
        list.add(new ColorName("Bole", "Brown", 121, 68, 59));
        list.add(new ColorName("Mahogany", "Brown", 192, 64, 0));
        list.add(new ColorName("Deep Brown", "Brown", 101, 67, 33));
        list.add(new ColorName("Taupe", "Brown", 72, 60, 50));
        list.add(new ColorName("Umbra", "Brown", 99, 81, 71));
        list.add(new ColorName("Puce", "Pink", 204, 136, 153));
        list.add(new ColorName("Dusty Rose", "Pink", 194, 133, 141));
        list.add(new ColorName("Rose Vale", "Pink", 171, 78, 82));
        list.add(new ColorName("Old Copper", "Brown", 114, 74, 47));

        return list;
    }

    public static String getColorName(int color) {
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);

        double[] targetLab = rgbToLab(r, g, b);
        
        ColorName closestMatch = null;
        double minDistance = Double.MAX_VALUE;

        for (ColorName colorName : COLOR_LIST) {
            // Using CIEDE2000 for industry-standard accuracy
            double distance = calculateDeltaE2000(targetLab, new double[]{colorName.L, colorName.a, colorName.b_lab});

            if (distance < minDistance) {
                minDistance = distance;
                closestMatch = colorName;
            }
        }

        if (closestMatch != null) {
            return closestMatch.name + " (" + closestMatch.simpleName + ")";
        }

        return "Unknown";
    }

    private static double calculateDeltaE2000(double[] lab1, double[] lab2) {
        double L1 = lab1[0], a1 = lab1[1], b1 = lab1[2];
        double L2 = lab2[0], a2 = lab2[1], b2 = lab2[2];

        double avgL = (L1 + L2) / 2.0;
        double C1 = Math.sqrt(a1 * a1 + b1 * b1);
        double C2 = Math.sqrt(a2 * a2 + b2 * b2);
        double avgC = (C1 + C2) / 2.0;

        double G = 0.5 * (1 - Math.sqrt(Math.pow(avgC, 7) / (Math.pow(avgC, 7) + Math.pow(25, 7))));
        double a1p = (1 + G) * a1;
        double a2p = (1 + G) * a2;

        double C1p = Math.sqrt(a1p * a1p + b1 * b1);
        double C2p = Math.sqrt(a2p * a2p + b2 * b2);
        double avgCp = (C1p + C2p) / 2.0;

        double h1p = Math.toDegrees(Math.atan2(b1, a1p));
        if (h1p < 0) h1p += 360;
        double h2p = Math.toDegrees(Math.atan2(b2, a2p));
        if (h2p < 0) h2p += 360;

        double avgHp = (Math.abs(h1p - h2p) > 180) ? (h1p + h2p + 360) / 2.0 : (h1p + h2p) / 2.0;

        double T = 1 - 0.17 * Math.cos(Math.toRadians(avgHp - 30)) +
                0.24 * Math.cos(Math.toRadians(2 * avgHp)) +
                0.32 * Math.cos(Math.toRadians(3 * avgHp + 6)) -
                0.20 * Math.cos(Math.toRadians(4 * avgHp - 63));

        double dhp = h2p - h1p;
        if (Math.abs(dhp) > 180) {
            if (h2p <= h1p) dhp += 360;
            else dhp -= 360;
        }

        double dLp = L2 - L1;
        double dCp = C2p - C1p;
        double dHp = 2 * Math.sqrt(C1p * C2p) * Math.sin(Math.toRadians(dhp / 2.0));

        double Sl = 1 + (0.015 * Math.pow(avgL - 50, 2)) / Math.sqrt(20 + Math.pow(avgL - 50, 2));
        double Sc = 1 + 0.045 * avgCp;
        double Sh = 1 + 0.015 * avgCp * T;

        double delRo = 30 * Math.exp(-Math.pow((avgHp - 275) / 25, 2));
        double Rc = 2 * Math.sqrt(Math.pow(avgCp, 7) / (Math.pow(avgCp, 7) + Math.pow(25, 7)));
        double Rt = -Rc * Math.sin(Math.toRadians(2 * delRo));

        return Math.sqrt(Math.pow(dLp / Sl, 2) + Math.pow(dCp / Sc, 2) + Math.pow(dHp / Sh, 2) + Rt * (dCp / Sc) * (dHp / Sh));
    }

    private static double[] rgbToLab(int r, int g, int b) {
        double rf = r / 255.0;
        double gf = g / 255.0;
        double bf = b / 255.0;

        rf = (rf > 0.04045) ? Math.pow((rf + 0.055) / 1.055, 2.4) : rf / 12.92;
        gf = (gf > 0.04045) ? Math.pow((gf + 0.055) / 1.055, 2.4) : gf / 12.92;
        bf = (bf > 0.04045) ? Math.pow((bf + 0.055) / 1.055, 2.4) : bf / 12.92;

        rf *= 100; gf *= 100; bf *= 100;

        double x = rf * 0.4124 + gf * 0.3576 + bf * 0.1805;
        double y = rf * 0.2126 + gf * 0.7152 + bf * 0.0722;
        double z = rf * 0.0193 + gf * 0.1192 + bf * 0.9505;

        double xn = 95.047, yn = 100.0, zn = 108.883;
        x /= xn; y /= yn; z /= zn;

        x = (x > 0.008856) ? Math.pow(x, 1.0 / 3.0) : (7.787 * x) + (16.0 / 116.0);
        y = (y > 0.008856) ? Math.pow(y, 1.0 / 3.0) : (7.787 * y) + (16.0 / 116.0);
        z = (z > 0.008856) ? Math.pow(z, 1.0 / 3.0) : (7.787 * z) + (16.0 / 116.0);

        return new double[]{(116 * y) - 16, 500 * (x - y), 200 * (y - z)};
    }
}
