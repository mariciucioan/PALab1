package com.mariciucioan.PALab1compulsory;

/*
    ~~ Made by Mariciuc Ioan ~~
    ~~ Numbers class has the scope to shelter methods that work with numbers ~~
 */

class Numbers {
    // returns the sum of every digit of a number recursively until the resulting number will have only 1 digit left
    static int digitSum(int n) {
        int result = n;
        while(result>=10) {
            n=result;
            result = 0;
            while(n>0) {
                result += n%10;
                n/=10;
            }

            System.out.println(result);
        }

        return result;
    }
}
