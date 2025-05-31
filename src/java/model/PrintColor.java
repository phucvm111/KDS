/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
//public class PrintColor {
public class PrintColor {

    public void show(Object line) {
        System.out.println(line);
    }

    public String red(String s) {
        return "\u001b[31m" + s + "\u001b[0m";
    }

    public String green(String s) {
        return "\u001b[32;2m" + s + "\u001b[0m";
    }

    public String blue(String s) {
        return "\u001b[34m" + s + "\u001b[0m";
    }

    public String magenta(String s) {
        return "\u001b[35m" + s + "\u001b[0m";
    }

    public String cyan(String s) {
        return "\u001b[36m" + s + "\u001b[0m";
    }

    // purple: 35
    public String test(String s) {
        return "\u001b[35;5;4m" + s + "\u001b[0m";
    }

    public String grayground(String s) {
        return "\u001b[47m" + s + "\u001b[0m";
    }

    public String graygroundbright(String s) {
        return "\u001b[47;1m" + s + "\u001b[0m";
    }
    // Reset: \u001b[0m
    // https://www.lihaoyi.com/post/BuildyourownCommandLinewithANSIescapecodes.html
    
    public static void main(String[] args) {
//        System.out.println("");
    }
}
