/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assign;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author adamt
 */
public class jobgetter {
    public static void main(String[] args) {
         
        try {
        BufferedReader    br = new BufferedReader(new FileReader("extracted_log.txt"));
       BufferedWriter bw =new BufferedWriter(new FileWriter("jobdone.txt"));
          BufferedWriter bw2 =new BufferedWriter(new FileWriter("jobcreated.txt"));
             ArrayList<String> rawdata = new ArrayList<>();
             Scanner sc=new Scanner(System.in);
                   int i=0;       
                  rawdata.add(i,br.readLine());
                  while(rawdata.get(i)!=null){
                      
                      i++;
                      rawdata.add(i,br.readLine());                   
                  } 
                 int jobc=0;
                 int total=0;
                  for (int j = 0; j <rawdata.size()-1; j++) {
                        Pattern pattern = Pattern.compile("\\[.*\\].*(Allocate JobId).*");
                       Pattern pattern2 = Pattern.compile("\\[.*\\].*(sched/backfill:).*");              
              
                       Matcher matcher = pattern.matcher(rawdata.get(j)); 
                        Matcher matcher2 = pattern2.matcher(rawdata.get(j)); 
                         Matcher matcher3 = pattern2.matcher(rawdata.get(j));
                        
                       if (matcher.matches()) {
                          String [] jobreq= rawdata.get(j).split(" ");
                          String [] id=jobreq[3].split("=");
                          int idn=Integer.parseInt(id[1]);
                          int idn1=0;
                         
                          a:  for (int k = j; k <rawdata.size()-1; k++) {
                              Pattern pattern1 = Pattern.compile("\\[.*\\].*(_job_complete:).*");
                              Matcher matcher1 = pattern1.matcher(rawdata.get(k));   
                              
                               if (matcher1.matches()) {                                   
                                 String [] idget=rawdata.get(k).split(" ");
                                String [] id1=idget[2].split("=");
                                
                                 for(String a:id1){
                                     if (a.equals(id[1])&&"done".equals(idget[3])) {
                                         total++;
                                         bw2.write(rawdata.get(j));
                                         bw2.newLine();
                                         bw.write(rawdata.get(k));
                                        
                                       bw.newLine();
                                        break a;
                                     }
                                 }
                                 
                               }
                              
                           }              
                      }
                        if (matcher2.matches()) {
                          String [] jobreq= rawdata.get(j).split(" ");
                          String [] id=jobreq[4].split("=");
                          int idn=Integer.parseInt(id[1]);
                          int idn1=0;
                         
                          a:  for (int k = j; k <rawdata.size()-1; k++) {
                              Pattern pattern1 = Pattern.compile("\\[.*\\].*(_job_complete:).*");
                              Matcher matcher1 = pattern1.matcher(rawdata.get(k));   
                              
                               if (matcher1.matches()) {                                   
                                 String [] idget=rawdata.get(k).split(" ");
                                String [] id1=idget[2].split("=");
                                
                                 for(String a:id1){
                                     if (a.equals(id[1])&&"done".equals(idget[3])) {
                                         total++;
                                         bw2.write(rawdata.get(j));
                                         bw2.newLine();
                                         bw.write(rawdata.get(k));
                                        
                                       bw.newLine();
                                        break a;
                                     }
                                 }
                                 
                               }
                              
                           }              
                      }
                     
                          }          
              
                       
                  
                  br.close();
                  bw.close();
                  bw2.close();
                  
                  
                  System.out.println("Total jobs extracted : "+total);
                  
                  
        } catch (FileNotFoundException ex) {
            Logger.getLogger(jobgetter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(jobgetter.class.getName()).log(Level.SEVERE, null, ex);
        }
                         
                  }                   
    }
