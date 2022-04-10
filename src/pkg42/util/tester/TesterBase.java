/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg42.util.tester;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author zequi
 */
public class TesterBase {

    public static ArrayList<String> execTerminal(String cmd)
    {
        ArrayList<String> list = new ArrayList<>();
        try {
            InputStream in = Runtime.getRuntime().exec(cmd).getInputStream();
            Scanner scan = new Scanner(in);
            while( scan.hasNext() ) {
                list.add(scan.nextLine());
            }
        } catch (IOException e) {
            System.out.println("execTerminal err: " + e.getLocalizedMessage());
        }
        return (list);
    }
    
}
