package Parentheses;

import java.util.Scanner;
import java.util.Stack;

class Solution {
    static int []Answer;
    
    static char []a;    //input
    
    static int maxParentheses() {
        int smax = 0;
        int mEH = 0;     //mEH = maxEndHere = độ dài dãy con lớn nhất mà chứa well-formed parentheis String và "dãy con đó kết thúc tại a[i]"
        int imax = 0;
        int i;
        
        for (i = 0; i < a.length; i++) {
            mEH = getMEH(i);
            
            if(mEH > smax) {
                smax = mEH;
                imax = i;
            }
        }
        
        return smax;
    }
    
    private static int getMEH(int viTriGetMEH) {
        StackListGeneric<Character> sl = new StackListGeneric<Character>();
        int meh = 0;
        int i;
        for (i = 0; i <= viTriGetMEH; i++) {
            switch (a[i]) {
                case '(':
                case '[':
                case '{': 
                    sl.push(a[i]);
                    //meh++;
                    break;
                case ')':
                    if(sl.isEmpty()) {
                        meh = 0;
                    } else if(sl.peek() == '(') {
                        sl.pop();
                        meh += 2;
                    } else {
                        sl.clearStack();
                        meh = 0;
                    }
                    break;
                case ']':
                    if(sl.isEmpty()) {
                        meh = 0;
                    } else if(sl.peek() == '[') {
                        sl.pop();
                        meh += 2;
                    } else {
                        sl.clearStack();
                        meh = 0;
                    }
                    break;
                case '}':
                    if(sl.isEmpty()) {
                        meh = 0;
                    } else if(sl.peek() == '{') {
                        sl.pop();
                        meh += 2;
                    } else {
                        sl.clearStack();
                        meh = 0;
                    }
                    break;
                    
                default:
                    //System.out.println("Default: "+a[i]);
                    break;
            }
            //System.out.println("meh["+i+"] = "+meh);
        }
        return meh;
    }
    
    static int maxParentheses2(char []a) {
        int smax = 0;
        int mEH = 0;     //mEH = maxEndHere = độ dài dãy con lớn nhất mà chứa well-formed parentheis String và "dãy con đó kết thúc tại a[i]"
        int imax = 0;
        int i;
        StackListGeneric<Character> sl = new StackListGeneric<Character>();
        
        for (i = 0; i < a.length; i++) {
            //Tính mEH[i]:
            switch (a[i]) {
                case '(':
                case '[':
                case '{': 
                    sl.push(a[i]);
                    //mEH++;
                    break;
                case ')':
                    if(sl.isEmpty()) {
                        mEH = 0;
                    } else if(sl.peek() == '(') {
                        sl.pop();
                        mEH += 2;
                    } else {
                        sl.clearStack();
                        mEH = 0;
                    }
                    break;
                case ']':
                    if(sl.isEmpty()) {
                        mEH = 0;
                    } else if(sl.peek() == '[') {
                        sl.pop();
                        mEH += 2;
                    } else {
                        sl.clearStack();
                        mEH = 0;
                    }
                    break;
                case '}':
                    if(sl.isEmpty()) {
                        mEH = 0;
                    } else if(sl.peek() == '{') {
                        sl.pop();
                        mEH += 2;
                    } else {
                        sl.clearStack();
                        mEH = 0;
                    }
                    break;
                    
                default:
                    //System.out.println("Default: "+a[i]);
                    break;
            }
            
            //sau đó cập nhập smax:
            if(mEH > smax) {
                smax = mEH;
                imax = i;
            }
        }
        
        return smax;
    }

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        String str;

        //System.out.println("Enter number of test_case:");
        int T = sc.nextInt();
        Answer = new int[T];
        
        for (int test_case = 0; test_case < T; test_case++) {
            Scanner sc2 = new Scanner(System.in);
            //System.out.println("Enter a string:");
            str = sc2.nextLine();
            
            a = new char[str.length()];
            a = str.toCharArray();
            
            Answer[test_case] = Solution.maxParentheses2(a);
        }
        
        // Print the answer to standard output(screen):
        for (int i = 0; i < T; i++) {
            System.out.println("Case #" + (i + 1));
            System.out.println(Answer[i]);
        }
    }
}

class StackListGeneric<T> {
    int size;
    private Node<T> top = null;
    
    public void push(T v) {
        Node p1 = new Node(v);
        if(top == null) top = p1;
        else {
            p1.next = top;
            top = p1;
        }
        size++;
    }
    
    public T pop() {
        if(top == null) {
            System.out.println("stack is emtpy, can't pop!");
            return null;
        }
        else {
            T result = top.value;
            top = top.next;
            if(size!=0)size--;
            return result;
        }
    }
    
    public T peek() {
        return top.value;
    }
    
    public boolean isEmpty() {
        return top == null;
    }

    public void clearStack() {
        top = null;
    }
    
    public void printStack() {
        if(top == null) {
            System.out.println("Stack is empty now. Can't print!");
            return;
        }
        Node p = top;
        while(p != null) {
            System.out.print(p.value+" ");
            p = p.next;
        }
        System.out.println();
    }
    
    ///inner class Node:
    private class Node<T> {  //để cho đơn giản thì mỗi node chỉ gồm data là 1 số nguyên, và có 1 con trỏ trỏ tới phần tử tiếp theo (và 1 con trỏ tới phần tử trước đó nếu là danh sách đôi)
        public T value;
        public Node next;

        public Node(T value) {
            this.value = value;
            this.next = null;
        }
    }
}
//(){}][()()]))[])()
//anhtu()()[]
//anhtu()()[]((){]}}
/*
()()]({[]}())
(){[]({})}

2
))((
(){}][()()]))[])()

10
))((
()
[][}}[
[][][[]]
())))
[]]]
{}{}
]]]]
[[[[
(){}][()()]))[])()

Runtime : 0.31148 sec
Exception in thread "main" java.util.NoSuchElementException: No line found
at java.util.Scanner.nextLine(Scanner.java:1540)
at Solution.main(Solution.java:157)
*/