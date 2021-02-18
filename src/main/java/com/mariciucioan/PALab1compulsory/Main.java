package com.mariciucioan.PALab1compulsory;

public class Main {

    public static void main(String[] args) {
        String[] languages = { "C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java" };
        String binaryValue = "10101";
        String hexadecimalValue = "FF";

        int n = (int) (Math.random() * 1000000);

        n*=3;
        n+= Integer.parseInt(binaryValue, 2);
        n+= Integer.parseInt(hexadecimalValue, 16);
        n*=6;

        System.out.println(n);
        System.out.println("Willy-nilly, this semester I will learn " + languages[Numbers.digitSum(n)]);
    }
}
