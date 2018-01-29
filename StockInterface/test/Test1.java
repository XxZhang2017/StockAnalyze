/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
/**
 *
 * @author d
 */
public class Test1 {
    public static void main(String[] args) throws IOException{
         BufferedReader bf = null; 
         bf = new BufferedReader(new InputStreamReader(System.in));
         String[] st=bf.readLine().split(",");
         for(int i=0;i<st.length;i++){
             System.out.println(st[i]);
         }
    }
}
