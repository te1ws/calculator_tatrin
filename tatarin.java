import java.util.Stack;
import java.util.Scanner;
public class tatarin{
    public static void main(String a[]) {
        Scanner in = new Scanner(System.in);
        String Expr = in.next();
        System.out.println(new tatarin().decision(Expr));
    }
    public double decision(String Expr){
        String rpn= Expression(Expr);
        return Answer(rpn);

    }

    private String Expression(String Expr){
        String current = " ";
        Stack<Character> stack = new Stack<>();
        int priority;
        for(int i =0;i<Expr.length();i++){
            priority = getP(Expr.charAt(i));

            if(priority==0)current+=Expr.charAt(i);
            if(priority==1)stack.push(Expr.charAt(i));

            if(priority>1){
                current+=' ';

                while(!stack.empty()) {
                    if (getP(stack.peek()) >= priority) current += stack.pop();
                    else break;
                }
                stack.push(Expr.charAt(i));
            }
            if(priority==-1){
                current+=' ' ;
                while(getP(stack.peek())!=1)current+=stack.pop();

                stack.pop();
            }
        }
        while(!stack.empty())current+=stack.pop();

        return current;
    }

    private double Answer(String rpn){
        String operand= new String();
        Stack<Double> stack = new Stack<>();

        for(int i = 0; i<rpn.length();i++){
            if(rpn.charAt(i) == ' ')continue;

            if(getP(rpn.charAt(i)) == 0){
                while(rpn.charAt(i)!= ' '&& getP(rpn.charAt(i))==0){
                    operand+=rpn.charAt(i++);
                    if(i==rpn.length())break;}
                stack.push(Double.parseDouble(operand));
                operand= new String();
            }

            if(getP(rpn.charAt(i))> 1){
                double a = stack.pop(),b=stack.pop();

                if(rpn.charAt(i) == '+')stack.push(b+a);
                if(rpn.charAt(i) == '-')stack.push(b-a);
                if(rpn.charAt(i) == '*')stack.push(b*a);
                if(rpn.charAt(i) == '/')stack.push(b/a);




            }
        }
        return stack.pop();
    }

    private int getP(char znak){
        if(znak=='*' || znak == '/')      return 3;
        else if(znak=='+' || znak == '-') return 2;
        else if(znak=='(')                 return 1;
        else if(znak==')')                 return -1;
        else return 0;

    }
}