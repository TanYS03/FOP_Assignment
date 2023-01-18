import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.AbstractMap.SimpleEntry;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders.FieldBorder;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class SortErrorsByMonths {
    
    public static final String DEFAULT =  "C:\\Users\\User\\Downloads\\extracted_log";
    public static final String DEFAULT1 = "C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\errors.txt";
    public static final String DEFAULT2 = "C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\usernames.txt";
    public static final String DEFAULT3 = "C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\errorsByDate.txt";
 
    public static void main(String[] args) {
        // getDateOfErrors();
       while(true) {
        String cmd = JOptionPane.showInputDialog("1 -> Number of jobs.\n" +
                                                 "2 -> Partitions.\n" +
                                                 "3 -> Errors.\n" +
                                                 "4 -> Average time.\n" + 
                                                 "Q -> Quit.");
            if(cmd.equals("1")) {

            }

            else if(cmd.equals("2")) {

            }

            else if(cmd.equals("3")) {
                while(true) {
                    ImageIcon wrong = new ImageIcon("C:\\Users\\User\\Desktop\\WIX 1002 (FOP)\\err2.png");
                    ImageIcon exit = new ImageIcon("C:\\Users\\User\\Desktop\\WIX 1002 (FOP)\\off1.png");
                    String in = JOptionPane.showInputDialog (
                     "1 -> Check total errors caused by users.\n" + 
                     "2 -> Display a table with usernames, time, association number and types of errors.\n" + 
                     "3 -> Show a username list and number of errors caused by each user.\n" + 
                     "4 -> Count errors in a given range of months.\n" + 
                     "5 -> Find errors from in a given range.\n" +
                     "Q -> Back to main menu." ) ;
            
                    HashSet<String> months = sortErrorsByMonths();
                    inputErrorsByMonths(months);
                    ArrayList<String> month = inputErrorsByMonths(months);
                    getAssociationNumber(month);
                    HashSet<String> users = findAndInputUsernames();
                    HashSet<String> dates = getDateOfErrors();
        
            
                        if(in.equals("1")) {
                            int errors = countErrors();
                            JOptionPane.showMessageDialog(null, "Total number of errors caused by users : " + errors, "Total errors", JOptionPane.INFORMATION_MESSAGE);
                        }
                        
                        else if(in.equals("2")) {
                            while(true) {
                                String inp = JOptionPane.showInputDialog(null, "A => Sort By Alphabet\nB => Sort By Months\nQ => Back To Main Menu", "Table display", JOptionPane.INFORMATION_MESSAGE);                    
                                    if(inp.equalsIgnoreCase("A")) {
                                        JOptionPane.showMessageDialog(null, "Table will be displayed in the terminal", "Table Display", JOptionPane.INFORMATION_MESSAGE);
                                        displayInfoInTable(users);
                                    } else if (inp.equalsIgnoreCase("B")) {
                                        JOptionPane.showMessageDialog(null, "Table will be displayed in the terminal", "Table Display", JOptionPane.INFORMATION_MESSAGE);
                                        displayInfoInTable(month);
                                    } else if (inp.equalsIgnoreCase("Q")) {
                                        // JOptionPane.showMessageDialog(null, "Back to main menu", "Back to main menu", JOptionPane.INFORMATION_MESSAGE);
                                        break;
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Not a valid command. Please enter a proper command", "Invalid Command", JOptionPane.INFORMATION_MESSAGE, wrong);
                                    }                          
                                }                
                            }
                           
                        else if(in.equals("3")) {
                            JOptionPane.showMessageDialog(null, "List will be displayed in the terminal.", "Total errors are displayed at the terminal.", JOptionPane.INFORMATION_MESSAGE);
                            countErrorsCausedByUsers(users);
                        }
            
                        else if(in.equals("4")) {
                            
                            while(true) {
                                String a = JOptionPane.showInputDialog("Please enter the start of the month(enter q to quit): ");   
                                if(a.equalsIgnoreCase("q")) {
                                    break;
                                }             
                                String b = JOptionPane.showInputDialog("Please enter the end of the month(enter q to quit): ");
                                if(b.equalsIgnoreCase("q")) {
                                    break;
                                }
                               
                                if(checkIfInputIsInteger(a, b)) {
                                    int x = Integer.parseInt(a);
                                    int y = Integer.parseInt(b);
                                    int sum= countErrorsInAGivenRange(x, y);
                                    JOptionPane.showMessageDialog(null, "Total number of errors from " + a + " to " + b + " are : " + sum, "Number of errors in a given range", JOptionPane.INFORMATION_MESSAGE);
                                    break;
                                } else {                    
                                    JOptionPane.showMessageDialog(null, "Month doesn't exist. Please enter proper month !", "Invalid Command", JOptionPane.INFORMATION_MESSAGE,wrong);
                                } 
                            }
                           
                        } 

                        else if(in.equals("5")) {
                            while(true) {
                                String a = JOptionPane.showInputDialog("Enter the start of the date(yyyy-mm-dd) (Enter q to quit): ");
                                    if(a.equalsIgnoreCase("Q")) {
                                        break;
                                    }

                                String b = JOptionPane.showInputDialog("Enter the end of the date(yyyy-mm-dd) (Enter q to quit): "); 
                                    if(b.equalsIgnoreCase("Q")) {
                                        break;
                                    }
                                countErrorsBetween(a, b);
                            }
                            
                        }
                        
                        else if(in.equalsIgnoreCase("q")){
                            // JOptionPane.showMessageDialog(null, "System closed ! Thanks for using ! ", "System Closed", JOptionPane.INFORMATION_MESSAGE,exit);
                            break;
                        }
            
                        else{
                            JOptionPane.showMessageDialog(null, "Not a valid command. Please enter a proper command", "Invalid Command", JOptionPane.INFORMATION_MESSAGE,wrong);
                        }  
                        System.out.println("\n*".repeat(5));          
                    }
            }

            else if(cmd.equals("4")) {
                while(true) {

                    String cmd4 = JOptionPane.showInputDialog("1 -> Display a table with execution time of allocated job based on month.\n" +
                                                              "2 -> Show June average time.\n" + 
                                                              "3 -> Show July average time.\n" +
                                                              "4 -> Show August average time.\n" + 
                                                              "5 -> Show September average time.\n" +
                                                              "6 -> Show October average time.\n" +
                                                              "7 -> Show November average time.\n" +
                                                              "8 -> Show December average time.\n" + 
                                                              "9 -> Total.\n" + 
                                                              "Q -> Back to main menu.");
                    try {
         
                        BufferedReader reader1 = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\FOP_Assignment\\jobdone.txt"));
                        BufferedReader reader2 = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\FOP_Assignment\\jobcreated.txt"));
                        
                        int totalcounter = 7530;
                        int fminit;
                        int fhour;
                        int fday;
                        int fmonth;
                        double fsecond;
                            
                        int totalmonth = 0;
                        int totalday = 0;
                        int totalhour = 0;
                        int totalminit = 0;
                        double totalsec = 0;
                        
                        
                        double maxtotal = 10;  
                        String maxid = "0";
                        
                        double junemaxtotal = 10;        
                        String junemaxid = "0";
                        
                        double julymaxtotal = 10;
                        String julymaxid = "0";
                        
                        double augmaxtotal = 10;
                        String augmaxid = "0";
                        
                        double sepmaxtotal = 10;
                        String sepmaxid = "0";
                        
                        double octmaxtotal = 10;
                        String octmaxid = "0";
                        
                        double novmaxtotal = 10;
                        String novmaxid = "0";
                        
                        double decmaxtotal = 10;
                        String decmaxid = "0";
                        
                        
                        double mintotal = 10;        
                        String minid = "0";
                        
                        double junemintotal = 100;        
                        String juneminid = "0";
                        
                        double julymintotal = 100;
                        String julyminid = "0";
                        
                        double augmintotal = 10;
                        String augminid = "0";
                        
                        double sepmintotal = 100;
                        String sepminid =" 0";
                        
                        double octmintotal = 10;
                        String octminid = "0";
                        
                        double novmintotal = 10;
                        String novminid = "0";
                        
                        double decmintotal = 100;
                        String decminid = "0";
                        
                        int junecounter = 0;
                        int julycounter = 0;
                        int augcounter  = 0;
                        int sepcounter  = 0;
                        int octcounter  = 0;
                        int novcounter  = 0;
                        int deccounter  = 0;
                        
                        int junetotal = 0;
                        int julytotal = 0;
                        int augtotal  = 0;
                        int septotal  = 0;
                        int octtotal  = 0;
                        int novtotal  = 0;
                        int dectotal  = 0;
                        
                        int junemon = 0;
                        int julymon = 0;
                        int augmon  = 0;
                        int sepmon  = 0;
                        int octmon  = 0;
                        int novmon  = 0;
                        int decmon  = 0;
                        
                        int juned = 0;
                        int julyd = 0;
                        int augd  = 0;
                        int sepd  = 0;
                        int octd  = 0;
                        int novd  = 0;
                        int decd  = 0;
                        
                        int juneh = 0;
                        int julyh = 0;
                        int augh  = 0;
                        int seph  = 0;
                        int octh  = 0;
                        int novh  = 0;
                        int dech  = 0;
                        
                        double junes = 0;
                        double julys = 0;
                        double augs  = 0;
                        double seps  = 0;
                        double octs  = 0;
                        double novs  = 0;
                        double decs  = 0;
                        
                        int junem = 0;
                        int julym = 0;
                        int augm  = 0;
                        int sepm  = 0;
                        int octm  = 0;
                        int novm  = 0;
                        int decm  = 0;
                        
                        
                        for (int i = 0; i < 7530; i++) {
    
                            String [] sepcre=reader2.readLine().split(" ");
                            String [] sepdon=reader1.readLine().split(" ");                
                            String [] stimecre=sepcre[0].split("T");
                            String [] datecre=stimecre[0].split("-"); //moth
                            String [] timecre=stimecre[1].split(":");
                            String [] secondcre=timecre[2].split("]");

                            int hourcre=Integer.parseInt(timecre[0]);
                            int minutes=Integer.parseInt(timecre[1]);
                            double second=Double.parseDouble(secondcre[0]);
                            int daycre=Integer.parseInt(datecre[2]);
                            int monthcre=Integer.parseInt(datecre[1]);
                                                    
                            String []stimedon=sepdon[0].split("T");
                            String [] datedon=stimedon[0].split("-"); //month
                            String [] timedon=stimedon[1].split(":");
                            String [] seconddon=timedon[2].split("]"); 
                            int hourdon=Integer.parseInt(timedon[0]);
                            int minutesdon=Integer.parseInt(timedon[1]);
                            double seconddonn=Double.parseDouble(seconddon[0]);
                            int daydon=Integer.parseInt(datedon[2]);
                            int monthdon=Integer.parseInt(datedon[1]);
                                
                            fsecond=getsecond(second,seconddonn);
                            fminit=getminutes(minutes,minutesdon);
                            fhour=gethour(hourcre,hourdon,daydon-daycre);
                            fday=getday(daycre,daydon,monthcre,monthdon,monthdon-monthcre);
                            fmonth=getmonth(monthcre,monthdon);
                            totalmonth=totalmonth+fmonth;
                            totalday=totalday+fday;
                            totalhour=totalhour+fhour;
                            totalminit=totalminit+fminit;
                            totalsec=totalsec+fsecond;
                           
                      //    getmin(fday, fhour, fminit, fsecond, minid, mind, minm, minsec,minh,sepdon);
                     //    getmax(fday, fhour, fminit, fsecond, maxid, maxd, maxm, maxsec, maxh, sepdon);
                           
                             
                                if(gettotal(fday, fhour, fminit, fsecond) < mintotal) {
                                   mintotal = gettotal(fday, fhour, fminit, fsecond);
                                   minid = sepdon[2];
                                 }

                                if(gettotal(fday, fhour, fminit, fsecond) > maxtotal) {
                                   maxtotal = gettotal(fday, fhour, fminit, fsecond);
                                   maxid = sepdon[2];
                               }
                                
                            
                        //   junetotal(juned,juneh,junem,junes,junecounter, monthcre, datecre , fday, fhour, fminit,fsecond);
                            if (monthcre == 6) {
                                junemon = junemon + fmonth;       
                                juned = juned + fday;
                                juneh = juneh + fhour;
                                junem = junem + fminit;
                                junes = junes + fsecond;
                                junecounter = junecounter + 1;
                        
                            if(gettotal(fday,fhour,fminit,fsecond) < junemintotal) {
                                junemintotal = gettotal(fday, fhour, fminit, fsecond);
                                juneminid = sepdon[2];
                            }

                            if(gettotal(fday,fhour,fminit,fsecond) > junemaxtotal) {
                                junemaxtotal = gettotal(fday, fhour, fminit, fsecond);
                                junemaxid = sepdon[2];
                            }
                        }
                         // getavg(junecounter,junes,juned,juneh,junem);
                            if (monthcre == 7) {
                                julymon = julymon + fmonth;  
                                julyd = julyd + fday;
                                julyh = julyh + fhour;
                                julym = julym + fminit;
                                julys = julys + fsecond;
                                julycounter = julycounter + 1;
                        
                            if(gettotal(fday, fhour, fminit, fsecond) < julymintotal) {
                                julymintotal = gettotal(fday, fhour, fminit, fsecond);
                                julyminid = sepdon[2];
                            }

                            if(gettotal(fday, fhour, fminit, fsecond) > julymaxtotal) {
                                julymaxtotal = gettotal(fday, fhour, fminit, fsecond);
                                julymaxid = sepdon[2];
                            }
                            
                    
                    }
                          //  getavg(julycounter,julys,julyd,julyh,julym);
                            if (monthcre == 8) {
                                augmon = augmon + fmonth;  
                                augd = augd + fday;
                                augh = augh + fhour;
                                augm = augm + fminit;
                                augs = augs + fsecond;
                                augcounter=augcounter+1;

                            if(gettotal(fday, fhour, fminit, fsecond) < augmintotal) {
                                augmintotal = gettotal(fday, fhour, fminit, fsecond);
                                augminid = sepdon[2];
                            }

                            if(gettotal(fday, fhour, fminit, fsecond) > augmaxtotal) {
                                augmaxtotal = gettotal(fday, fhour, fminit, fsecond);
                                augmaxid = sepdon[2];
                            }
                    
                    }
                        //   getavg(augcounter,augs,augd,augh,augm);
                            if (monthcre == 9) {
                                sepmon = sepmon + fmonth;  
                                sepd = sepd + fday;
                                seph = seph + fhour;
                                sepm = sepm + fminit;
                                seps = seps + fsecond;
                                sepcounter = sepcounter + 1;
                        
                            if(gettotal(fday, fhour, fminit, fsecond) < sepmintotal) {
                                sepmintotal = gettotal(fday, fhour, fminit, fsecond);
                                sepminid = sepdon[2];
                            }

                            if(gettotal(fday, fhour, fminit, fsecond) > sepmaxtotal) {
                                sepmaxtotal = gettotal(fday, fhour, fminit, fsecond);
                                sepmaxid = sepdon[2];
                            }
                    
                    }
                     //    getavg(sepcounter,seps,sepd,seph,sepm);
                            if (monthcre == 10) {
                                octmon = octmon + fmonth;  
                                octd= octd + fday;
                                octh= octh + fhour;
                                octm= octm + fminit;
                                octs= octs + fsecond;
                                octcounter = octcounter + 1;
                        
                            if(gettotal(fday, fhour, fminit, fsecond) < octmintotal){
                                octmintotal=gettotal(fday, fhour, fminit, fsecond);
                                octminid = sepdon[2];
                            }
                                
                            if(gettotal(fday, fhour, fminit, fsecond) > octmaxtotal){
                                octmaxtotal = gettotal(fday, fhour, fminit, fsecond);
                                octmaxid = sepdon[2];
                            }
                    }
                    //     getavg(octcounter,octs,octd,octh,octm);
                        if (monthcre == 11) {
                        novmon = novmon + fmonth;  
                        novd = novd + fday;
                        novh = novh + fhour;
                        novm = novm + fminit;
                        novs = novs + fsecond;
                        novcounter = novcounter +1 ;

                        if(gettotal(fday, fhour, fminit, fsecond) < novmintotal){
                                novmintotal = gettotal(fday, fhour, fminit, fsecond);
                                novminid = sepdon[2];
                            }

                        if(gettotal(fday, fhour, fminit, fsecond) > novmaxtotal){
                            novmaxtotal = gettotal(fday, fhour, fminit, fsecond);
                            novmaxid = sepdon[2];
                        }
                    }
                   //     getavg(novcounter,novs,novd,novh,novm);
                        if (monthcre == 12) {
                        decmon = decmon + fmonth;  
                        decd = decd + fday;
                        dech = dech + fhour;
                        decm = decm + fminit;
                        decs = decs + fsecond;
                        deccounter = deccounter + 1;    
            
                        if(gettotal(fday, fhour, fminit, fsecond) < decmintotal) {
                                decmintotal = gettotal(fday, fhour, fminit, fsecond);
                                decminid = sepdon[2];
                            }
                            if(gettotal(fday, fhour, fminit, fsecond) > decmaxtotal) {
                                   decmaxtotal = gettotal(fday, fhour, fminit, fsecond);
                                   decmaxid = sepdon[2];
                               }
                    }
                        }
                            if(cmd4.equals("1")) {
                                System.out.println("Execution time of allocated job based on month");
                                System.out.printf("%-10s %-6s %-5s %-8s %-8s %-20s \n","Month","Months","Days","Hours","Minutes","Seconds");
                                System.out.printf("%-10s %-6s %-5s %-8s %-8s %-20.3f \n","June",junemon,juned,juneh,junem,junes);
                                System.out.printf("%-10s %-6s %-5s %-8s %-8s %-20.3f \n","July",julymon,julyd,julyh,julym,julys);
                                System.out.printf("%-10s %-6s %-5s %-8s %-8s %-20.3f \n","August",augmon,augd,augh,augm,augs);
                                System.out.printf("%-10s %-6s %-5s %-8s %-8s %-20.3f \n","September",sepmon,sepd,seph,sepm,seps);
                                System.out.printf("%-10s %-6s %-5s %-8s %-8s %-20.3f \n","October",octmon,octd,octh,octm,octs);
                                System.out.printf("%-10s %-6s %-5s %-8s %-8s %-20.3f \n","Novemeber",novmon,novd,novh,novm,novs);
                                System.out.printf("%-10s %-6s %-5s %-8s %-8s %-20.3f \n\n","December",decmon,decd,dech,decm,decs);
                            }
                            
                            else if(cmd4.equals("2")) {
                                System.out.printf("June average :%.3f seconds or ",getavg(junecounter, junes, juned, juneh, junem)," seconds or ");
                                stos(getavg(junecounter,junes,juned,juneh,junem));
                                System.out.printf("shortest runtime : " + juneminid + " ");
                                stos(junemintotal);
                                System.out.printf("longest runtime  : " + junemaxid + " ");
                                stos(junemaxtotal);
                                System.out.println();
                            }
                            
                            else if(cmd4.equals("3")) {
                                System.out.printf("July average :%.3f seconds or ", getavg(julycounter, julys, julyd, julyh, julym));
                                stos( getavg(julycounter,julys,julyd,julyh,julym));
                                System.out.printf("shortest runtime : " + julyminid + " ");
                                stos(julymintotal);
                                System.out.printf("longest runtime  : " + julymaxid + " ");
                                stos(julymaxtotal);
                                System.out.println();
                            }
                            
                            else if(cmd4.equals("4")) {
                                System.out.printf("August average :%.3f seconds or ", getavg(augcounter, augs, augd, augh, augm)," seconds or ");
                                stos( getavg(augcounter,augs,augd,augh,augm));
                                System.out.printf("shortest runtime : " + augminid + " ");
                                stos(augmintotal);
                                System.out.printf("longest runtime  : " + augmaxid + " ");
                                stos(augmaxtotal);
                                System.out.println();
                            }
                            
                            else if(cmd4.equals("5")) {
                                System.out.printf("September average :%.3f seconds or ",getavg(sepcounter, seps, sepd, seph, sepm)," seconds or ");
                                stos(getavg(sepcounter,seps,sepd,seph,sepm));
                                System.out.printf("shortest runtime : " + sepminid + " ");
                                stos(sepmintotal);
                                System.out.printf("longest runtime  : " + sepmaxid + " ");
                                stos(sepmaxtotal);
                                System.out.println();
                            }
                            
                            else if(cmd4.equals("6")) {
                                System.out.printf("October average :%.3f seconds or ",getavg(octcounter,octs,octd,octh,octm)," seconds or ");
                                stos(getavg(octcounter,octs,octd,octh,octm));
                                System.out.printf("shortest runtime : " + octminid + " ");
                                stos(octmintotal);
                                System.out.printf("longest runtime  : " + octmaxid + " ");
                                stos(octmaxtotal);
                                System.out.println();
                            }
                            
                            else if(cmd4.equals("7")) {
                                System.out.printf("November average :%.3f seconds or ",getavg(novcounter, novs, novd, novh, novm), " seconds or ");
                                stos(getavg(novcounter,novs,novd,novh,novm));
                                System.out.printf("shortest runtime : " + novminid + " ");
                                stos(novmintotal);
                                System.out.printf("longest runtime  : " + novmaxid + " ");
                                stos(novmaxtotal);
                                System.out.println();
                            }
                            
                            else if(cmd4.equals("8")) {
                                System.out.printf("December average :%.3f seconds or ",getavg(deccounter, decs, decd, dech, decm), " seconds or ");
                                stos(getavg(deccounter,decs,decd,dech,decm));
                                System.out.printf("shortest runtime : " + decminid + " ");
                                stos(decmintotal);
                                System.out.printf("longest runtime  : " + decmaxid + " ");
                                stos(decmaxtotal);
                                System.out.println();
                            }
                            
                            else if(cmd4.equals("9")) {
                                System.out.println("total month :" + totalmonth);
                                System.out.println("total day :" + totalday);
                                System.out.println("total  hour :" + totalhour);
                                System.out.println("total minit :" + totalminit);
                                System.out.printf("total second : %.3f \n", totalsec); 
                                System.out.printf("shortest runtime : " + minid + " ");
                                stos(mintotal);
                                
                                System.out.printf("longest runtime  : " + maxid + " ");
                                stos(maxtotal);
                            //  double avg =  (totalsec+secondconvert(totalday,totalhour,totalminit))/7530;
                                    
                                System.out.printf("Average :  %.3f seconds or ",getavg(totalcounter, totalsec, totalday, totalhour, totalminit));
                                stos(getavg(totalcounter, totalsec, totalday, totalhour, totalminit));    
                                
                            }
                        
                            else if(cmd4.equalsIgnoreCase("Q")) {
                                break;
                            }
                            
                            else{
                            JOptionPane.showMessageDialog(null, "Not a valid command. Please enter a proper command", "Invalid Command", JOptionPane.INFORMATION_MESSAGE);
                        }
                        
                    
                    } catch (FileNotFoundException ex) {
                        System.out.println("File not found");
                    } catch (IOException ex) { }
                }
                
            }
            
            else if(cmd.equalsIgnoreCase("Q")) {
                JOptionPane.showMessageDialog(null, "System quited. Thanks for using.", "Sysyem quit", JOptionPane.INFORMATION_MESSAGE);
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Not a valid command. Please enter a proper command", "Invalid Command", JOptionPane.INFORMATION_MESSAGE);
            }

            System.out.println(" ");

            for (int i = 5; i > 0 ; i--) {
                System.out.println("*".repeat(i));
            }
       }
    }

    
// -------------------------------------------------------------------------------------------------
    // Weili: 
    // function: read raw datas, find and count errors caused by users (done)
    public static int countErrors() {
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DEFAULT));
            PrintWriter writer = new PrintWriter(new FileWriter(DEFAULT1));
                
                String line;     
                
                while((line = reader.readLine()) != null)
                {
                    Pattern pattern = Pattern.compile("\\[.*\\].*(error: This association).*");
                    Matcher matcher = pattern.matcher(line);
                        if(matcher.matches()){
                            writer.println(line);
                            count++;
                    }
                }                
                writer.close();
                reader.close();
       }catch (FileNotFoundException ex) {System.out.println("File not found");}
        catch (IOException e){}       
        return count;
}


    // function: write the usernames into a file named "usernames.txt". (done)
    public static HashSet<String> findAndInputUsernames() {
    HashSet<String> users = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DEFAULT));
            PrintWriter writer = new PrintWriter(new FileWriter(DEFAULT2));
                
                String line;     

                while((line = reader.readLine()) != null)
                {
                    Pattern pattern = Pattern.compile("\\[.*\\].*(error: This association).*(user=)'(.*)'(.*)(,).*");
                    Matcher matcher = pattern.matcher(line);
                        if(matcher.matches()) {                            
                            writer.println(matcher.group(3));
                            users.add(matcher.group(3));
                    }                
                }
                System.out.println(" ");
                writer.close();
                reader.close();
        }catch (FileNotFoundException ex) {System.out.println("File not found");}
        catch (IOException e){}
    return users;       
}


    // function: sort errors into different months (done)
    public static HashSet<String> sortErrorsByMonths() {
    HashSet<String> months = new HashSet<>();
        try { BufferedReader reader = new BufferedReader(new FileReader(DEFAULT1));
                String line;     
                            
                while((line = reader.readLine()) != null)
                    {   
                        Pattern pattern = Pattern.compile("\\[.*-(.*)-[0-9]{2}.*\\].*(error: This association).*");
                        Matcher matcher = pattern.matcher(line);
                            if(matcher.matches()) {
                                months.add(matcher.group(1));
                            }
                    }                  
                    reader.close();
                
            } catch (FileNotFoundException ex) {System.out.println("File not found");}
              catch (IOException e) {}    
          return months;     
   }

   
    // function: input error according to different months into its respective file. 
    public static ArrayList<String> inputErrorsByMonths(HashSet<String> months) {
        ArrayList<String> month = new ArrayList<String> ();
        try { 
                BufferedReader reader = null;
                PrintWriter writer = null;            
                String line;   

                for(String set: months)
                    {
                        month.add(set);
                    } 
                        Collections.sort(month);
                        // System.out.println(month);
                                                            
                for (int i = 0; i < month.size(); i++)                                 
                {
                    reader = new BufferedReader(new FileReader(DEFAULT1));
                    writer = new PrintWriter(new FileWriter("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\month" + month.get(i) + ".txt"));

                    while((line = reader.readLine()) != null) {
                        Pattern pattern = Pattern.compile("\\[.*-(" + month.get(i) + ")-[0-9]{2}.*\\].*(error: This association).*");
                        Matcher matcher = pattern.matcher(line);

                        if(matcher.matches()) {
                            // System.out.println(line);
                            writer.println(line);
                        }
                    }
                    writer.close();
                }            
            reader.close();                    
            } catch (FileNotFoundException ex) {System.out.println("File not found");}
            catch (IOException e) {}    
            return month;
    }    


    // function: get association number of each line of error from text files.
    public static void getAssociationNumber(ArrayList<String> months) {
        try{
            BufferedReader reader = null;
            PrintWriter writer = null;
            String line;
            
            for(int i = 0; i < months.size(); i++)
            {
                reader = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\month" + months.get(i) + ".txt"));
                writer = new PrintWriter(new FileWriter("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\Association_num_month" + months.get(i) + ".txt"));
                while((line = reader.readLine()) != null){
                    Pattern pattern = Pattern.compile("\\[.*-(" + months.get(i) + ")-[0-9]{2}.*\\] (error: This association) ([0-9]*).*");
                    Matcher matcher = pattern.matcher(line);
                        if(matcher.matches()){
                            writer.println(matcher.group(3));
                        }
                }writer.close();
            }            
            reader.close();
        } catch(FileNotFoundException e){System.out.println("File not found");}
          catch(IOException ex){}
    }


    // function: display username, time ,association number , and types of errors in a table.(sorted by alphabet)
    public static void displayInfoInTable(HashSet<String> users) {
        ArrayList<String> usersname = new ArrayList<String>();
        
        try{
            BufferedReader reader = null;
            
            for (String sets : users) {
                usersname.add(sets);
            }
            Collections.sort(usersname);

            String line;
            System.out.println(" ");
            System.out.printf("| %-27s | %-30s | %-30s | %-20s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30), "-".repeat(20));
            System.out.printf("| %-27s | %-30s | %-30s | %-20s |\n","           Name","            Time","        Associations num","     Error Types");
            System.out.printf("| %-27s | %-30s | %-30s | %-20s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30), "-".repeat(20));
            for (String name : usersname) {
                reader = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\errors.txt"));
                
                while((line = reader.readLine()) != null){
                    Pattern pattern = Pattern.compile("\\[(.*)\\] (error: This association) ([0-9]*).*(does not have access to )(.*)");
                    Matcher matcher = pattern.matcher(line);
                        if(line.contains(name) && matcher.matches()){
                            System.out.printf("|            %-16s |     %-26s |               %-16s |      %-15s |\n",name, matcher.group(1),matcher.group(3), matcher.group(5));
                        }
                }
            }
            System.out.printf("| %-27s | %-30s | %-30s | %-20s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30), "-".repeat(20));
            reader.close();
        } catch(FileNotFoundException ex){System.out.println("File not found");}
          catch(IOException ex){}
    }


    // function: display username, time ,association number , and types of errors in a table.(sorted by months)
    public static void displayInfoInTable(ArrayList<String> months) {
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DEFAULT1));
            
            String line ;
            System.out.println(" ");
            System.out.printf("| %-27s | %-30s | %-30s | %-20s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30), "-".repeat(20));
            System.out.printf("| %-27s | %-30s | %-30s | %-20s |\n","           Name","            Time","        Associations num","     Error Types");
            System.out.printf("| %-27s | %-30s | %-30s | %-20s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30), "-".repeat(20));
                    
                    while((line = reader.readLine()) != null) {
                    Pattern pattern = Pattern.compile("\\[(.*)\\] (error: This association) ([0-9]*).*(user=)'(.*)'(.*)(,).*(does not have access to )(.*)");
                    Matcher matcher = pattern.matcher(line);

                    if(matcher.matches()) {
                        System.out.printf("|            %-16s |     %-26s |               %-16s |      %-15s |\n",matcher.group(5), matcher.group(1),matcher.group(3), matcher.group(9));
                    }
                }
         
            System.out.printf("| %-27s | %-30s | %-30s | %-20s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30), "-".repeat(20));
            reader.close();
        } catch (FileNotFoundException e) {System.out.println("File not found");} 
          catch (IOException ex) {} 
    }


    // function: display the number of errors caused by each users.
    public static void countErrorsCausedByUsers(HashSet<String> users) {
        
        ArrayList<String> usernames = new ArrayList<>();
        for(String set : users) {
            usernames.add(set);
        }
       
        Collections.sort(usernames);
        System.out.println(" ");
            try {
                BufferedReader reader = null;     
                PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\errorByParticularUser.txt"));           
                String line;

                    for (String name : usernames) {
                        reader = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\usernames.txt"));
                        int count = 0;
                            while((line = reader.readLine()) != null) {
                                if(line.contains(name)) {
                                    count++;
                                }
                        }                         
                        System.out.printf("%s %-17s |   %-1d\n", "Number of errors caused by ===>", name, count );
                        writer.printf("%s %-17s |   %-1d\n", "Number of errors caused by ===>", name, count );
                    } 
                reader.close();
                writer.flush();
            } catch (FileNotFoundException e) {System.out.println("File not found");}
              catch(IOException ex) {};
    }


    // function: count total number of errors in given range of time inputted by user
    public static int countErrorsInAGivenRange(int startMonth, int endMonth) {
        int sum = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(DEFAULT1));
            String line;
            
            while ((line = reader.readLine()) != null){
                Pattern pattern = Pattern.compile("\\[.*-(.*)-.*\\] .*");
                Matcher matcher = pattern.matcher(line);
                matcher.matches();
                
                int month = Integer.parseInt(matcher.group(1));
                
                if (month <= endMonth && month >= startMonth) {
                    sum++;
                    continue;
                }                  

                if (month > endMonth)
                    break;
            }
            reader.close();
        }catch (FileNotFoundException e) {System.out.println("File not found");}
         catch (IOException ex) {}
         return sum;
    }


    // function: to check whether the input of the range of months is an integer or not.
    public static boolean checkIfInputIsInteger(String startMonth, String endMonth) {
        try{
            int x = Integer.parseInt(startMonth);
            int y = Integer.parseInt(endMonth);
            
            if (x > y) {
                JOptionPane.showMessageDialog(null, "The range is from year 2022 June to December.", "Invalid range", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
                

            return (x <= 12 && x > 0) && (y <= 12 && y > 0);
            
        } catch(NumberFormatException e) {
            return false;
        }          
    }


    // funtion: to input the errors into a file with date in the format of(dd/mm/yyyy)
    public static HashSet<String> getDateOfErrors() {
        HashSet<String> dates = new HashSet<>();
        try {
                        
            BufferedReader reader = new BufferedReader(new FileReader(DEFAULT1));
            PrintWriter writer = new PrintWriter (new FileWriter(DEFAULT3));
            String line;

            while((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("\\[(.*-.*-[0-9]{2}).*\\] (error: This association) ([0-9]*).*(user=)'(.*)'(,)(.*)(does not have access to )(.*)");
                Matcher matcher = pattern.matcher(line);
                    if(matcher.matches()) {
                        writer.println(matcher.group(1) + "  " + matcher.group(3) + "  " + matcher.group(5) + "  " + matcher.group(9));
                        dates.add(matcher.group(1) + "-" + matcher.group(2) + "-" + matcher.group(3) + "\n");
                    }
            }
            reader.close();
            writer.flush();
        } catch (FileNotFoundException e) {System.out.println("File not found.");}
          catch (IOException ex) {} ;
          return dates;
    }


    // function: to display the errors in the range of date input by the users
    public static void countErrorsBetween(String start, String end) { 
        
    try(BufferedReader reader = new BufferedReader(new FileReader(DEFAULT3))) {
            String line;
            Scanner sc = new Scanner(System.in);
            int count = 0; 
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");            
            LocalDate startDate = LocalDate.parse(start, formatter);            
            LocalDate endDate = LocalDate.parse(end, formatter);
        
            while((line = reader.readLine()) != null ) {  

                LocalDate dateTime = LocalDate.parse(line.split("  ")[0]);
                if(!(dateTime.isBefore(startDate) || dateTime.isAfter(endDate))) {
                    count ++;
                    System.out.println(line);   
                }
                   
                                     
    }                      
            System.out.println("\nTotal number of errors in the given range are: " + count);
        } catch(FileNotFoundException e) {System.out.println("File not found.");}
          catch(IOException ex) { }
    }
   
// -----------------------------------------------------------------------------------------------------------------------------
    // Adam: 
    public static double gettotal(int day,int hour,int minit,double sec){
        return  (day*86400)+(hour*3600)+(minit*60)+sec;
    }


    public static void stos(double a){
        int day=(int)a/86400;
        int hour=(int)(a-day*86400)/3600;
        int min=(int)(a-hour*3600-day*86400)/60;
        double sec=a-hour*3600-min*60-day*86400;

        System.out.printf(day+" day "+hour+" hours "+min+" minutes %.3f seconds \n",sec);
    }


    public static double getavg(int counter,double second,int a,int b,int c){
        double total=  (a*86400)+(b*3600)+(c*60);
        return (second+total)/counter;
    }


    public static double getsecond(double a,double b){
        double x;
        double y=60-a;  
        x=y+b;
        return x;
        
    }


    public static int getminutes(int a, int b){
        int x;        
        int y=60-a;  
        x=y+b;
            if (x>0) {
                return x-1;
            } 
        return x;        
    }


    public static int gethour(int a,int b,int c){ //c=daydiff
        int x;
        if (c!=0) {
        int y=24-a;  
        x=y+b;
        
    } else{
            x=b-a;
        }
        if (x>0) {
            return x-1;
        }
        return x;
    }


    public static int getday(int a,int b,int c,int d,int e){ //e=month diff c,d month
        int x = 0;
        int y;
        int z=0;
        if(e==1){
            if (c==6||c==9||c==11) {
                y=30-a;
            }
            else{
                y=31-a;
            }

            x=b+y;
        }
    else if(e>=2){
        if (c==6||c==9||c==11) {
            y=30-a;
        }
        else{
            y=31-a;
        }
        for (int i = a+1; i <=b; i++) {
            if (i==6||i==9||i==11) {
                z=30;
                y=y+z;
            }
            else {
                z=31;
                y=y+z;
            }
            x=y+b;  
        }
    }
    else{
        x=b-a;
    }
    if (x>0) {
            return x-1;
        }
    return x;
    }


    public static int getmonth(int a,int b){
        
        if (b-a<=1) {
            return 0;
        }
        else
            return b-a-1;
        
    }


}


