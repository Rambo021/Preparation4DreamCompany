package com.flexport.coding;

public class Test {
    public int test(int i, Integer j){
        return i + j;
    }

    public int test(Integer i, int j){
        return  i + j;
    }

    public int test(int i, int j){
        return  i + j;
    }

    public int test(Integer i, Integer j){
        return  i + j;
    }

    public static void main(String[] args) {
        Test t = new Test();

        System.out.printf("%s,%s,%s,%s", t.test(1, 2), t.test(1, new Integer(3)), t.test(new Integer(1), 4), t.test(new Integer(1), new Integer(5)));
    }
}
