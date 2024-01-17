import java.util.Scanner;

public class zennode {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // For Product A
        System.out.println("How many Product A : $20?");
        int countA = sc.nextInt();
        boolean wrapA = false, wrapB = false, wrapC = false;
        if (countA > 0) {
            System.out.println("Do u want tp wrap Product A?(y/n)");
            char ch = sc.next().charAt(0);
            if (ch == 'y' || ch == 'Y') {
                wrapA = true;
            } else if (ch == 'n' || ch == 'N') {
                wrapA = false;
            }
        }
        int price_A = 20 * countA;
        // For Product B
        System.out.println("How many Product B : $40?");
        int countB = sc.nextInt();
        if (countB > 0) {
            System.out.println("Do u want tp wrap Product B?(y/n)");
            char ch = sc.next().charAt(0);
            if (ch == 'y' || ch == 'Y') {
                wrapB = true;
            } else if (ch == 'n' || ch == 'N') {
                wrapB = false;
            }
        }
        int price_B = 40 * countB;
        // For Product C
        System.out.println("How many Product C : $50?");
        int countC = sc.nextInt();
        if (countC > 0) {
            System.out.println("Do u want tp wrap Product C?(y/n)");
            char ch = sc.next().charAt(0);
            if (ch == 'y' || ch == 'Y') {
                wrapC = true;
            } else if (ch == 'n' || ch == 'N') {
                wrapC = false;
            }
        }
        int price_C = 50 * countC;
        // Calculating sub total
        int subtotal = price_A + price_B + price_C;

        System.out.println("Product Name    Quantity    Total_Amount");
        System.out.println("Product A       " + countA + "          " + price_A);
        System.out.println("Product B       " + countB + "          " + price_B);
        System.out.println("Product C       " + countC + "          " + price_C);
        System.out.println("Subtotal : " + subtotal);
        if (countA + countB + countC > 0) {
            double disc_info[] = discount_amount(subtotal, countA, countB, countC);
            String disc_name = "";
            if (disc_info[0] == 1) {
                disc_name = "flat_10_discount";
            } else if (disc_info[0] == 2) {
                disc_name = "bulk_5_discount";
            } else if (disc_info[0] == 3) {
                disc_name = "bulk_10_discount";
            } else if (disc_info[0] == 4) {
                disc_name = "tiered_50_discount";
            } else {
                disc_name = "Not Applicable";
            }
            System.out.println("Discount Name   Discount Amount");
            System.out.println(disc_name + "  " + disc_info[4]);
            int wrapFee = 0;
            if (wrapA) {
                wrapFee += countA;
            }
            if (wrapB) {
                wrapFee += countB;
            }
            if (wrapC) {
                wrapFee += countC;
            }
            System.out.println("Wrap Fee : " + wrapFee);
            int totalBox = ((countA + countB + countC) / 10) + 1;
            int shippingFee = totalBox * 5;
            System.out.println("Shipping Fee : " + shippingFee);
            double finalAmount = (subtotal - disc_info[4]) + wrapFee + shippingFee;
            System.out.println("Total : " + finalAmount);
        } else {
            System.out.println("Enter Quantity grater than 0");
        }
    }

    public static double[] discount_amount(int subtotal, int countA, int countB, int countC) {
        double[] result = { 0, 0, 0, 0, 0 };
        double discountAmount = 0;
        double discA = 0;
        double discB = 0;
        double discC = 0;
        // flat_10_discount
        if (subtotal > 200) {
            discountAmount = 10;
            result[0] = 1;
            result[4] = discountAmount;
        }

        // bulk_5_discount
        if (countA > 10) {
            discA = (countA * 20) * 0.05;
        }
        if (countB > 10) {
            discB = (countB * 40) * 0.05;
        }
        if (countC > 10) {
            discC = (countC * 50) * 0.05;
        }
        if (discA + discB + discC > discountAmount) {
            discountAmount = discA + discB + discC;
            result[0] = 2;
            result[1] = discA;
            result[2] = discB;
            result[3] = discC;
            result[4] = discountAmount;
        }

        // bulk_10_discount
        if (countA + countB + countC > 20) {
            double discount_cart = ((countA * 20) + (countB * 40) + (countC * 50)) * 0.1;
            if (discount_cart > discountAmount) {
                discountAmount = discount_cart;
                result[0] = 3;
                result[4] = discountAmount;
            }
        }

        // tiered_50_discount
        if (countA + countB + countC > 30 && (countA > 15 || countB > 15 || countC > 15)) {
            if (countA > 15) {
                int extra = countA - 15;
                int extra_cost = extra * 20;
                discA = extra_cost * 0.5;
            }
            if (countB > 15) {
                int extra = countB - 15;
                int extra_cost = extra * 40;
                discB = extra_cost * 0.5;
            }
            if (countC > 15) {
                int extra = countC - 15;
                int extra_cost = extra * 50;
                discC = extra_cost * 0.5;
            }
            if (discA + discB + discC > discountAmount) {
                discountAmount = discA + discB + discC;
                result[0] = 4;
                result[1] = discA;
                result[2] = discB;
                result[3] = discC;
                result[4] = discountAmount;
            }
        }
        return result;
    }
}
