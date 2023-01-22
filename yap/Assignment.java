
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.AbstractMap.SimpleEntry;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.*;
import javax.print.event.PrintEvent;
import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders.FieldBorder;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import org.jfree.data.general.DefaultPieDataset;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.util.Rotation;
import java.awt.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Assignment extends JFrame {

    public static final String DEFAULT = "C:\\Users\\User\\Downloads\\extracted_log";
    public static final String DEFAULT1 = "C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\errors.txt";
    public static final String DEFAULT2 = "C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\usernames.txt";
    public static final String DEFAULT3 = "C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\errorsByDate.txt";
    public static final String ERRORS = "C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\NumErrorsEachMonths.txt";
    public static final String D1 = "C:\\Users\\User\\Desktop\\FOP_Assignment\\cheeyan\\return.txt";
    public static final String D2 = "C:\\Users\\User\\Desktop\\FOP_Assignment\\cheeyan\\totalkilljob.txt";
    public static final String JOBDONE = "C:\\Users\\User\\Desktop\\FOP_Assignment\\Adam\\jobdone.txt";
    public static final String JOBCREATED = "C:\\Users\\User\\Desktop\\FOP_Assignment\\Adam\\jobcreated.txt";
    public static final ImageIcon wrongImage = new ImageIcon("C:\\Users\\User\\Desktop\\FOP_Assignment\\ImageForUi\\piffle-error.gif");
    public static final ImageIcon exitImage = new ImageIcon("C:\\Users\\User\\Desktop\\FOP_Assignment\\ImageForUi\\close.gif");
    public static final ImageIcon loading = new ImageIcon("C:\\Users\\User\\Desktop\\FOP_Assignment\\ImageForUi\\loadingbar.gif");
    public static final ImageIcon cny = new ImageIcon("C:\\Users\\User\\Desktop\\FOP_Assignment\\ImageForUi\\cny.gif");

    public static void main(String[] args) {

        while (true) {
            String cmd = JOptionPane.showInputDialog("1 -> Number of jobs.\n"
                    + "2 -> Partitions.\n"
                    + "3 -> Errors.\n"
                    + "4 -> Average time.\n"
                    + "5 -> Kill Jobs.\n"
                    + "Q -> Quit.");

            if (cmd.equals("1")) {
                while (true) {
                    Scanner input = new Scanner(System.in);
                    String find = JOptionPane.showInputDialog("1 -> Number of job created/ended in particular month.\n" //jobrange
                            + "2 -> Number of job created/ended in each month.\n"
                            + "3 -> Number of job created/ended in particular time range.\n"
                            + "Q -> Back to main menu.");
                    if (find.equals("1")) {
                        jobRange();
                    } else if (find.equals("2")) {
                        allJobs();
                    } else if (find.equals("3")) {
                        JobRangeDay();
                    } else if (find.equalsIgnoreCase("Q")) {
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Not a valid command. Please enter a proper command", "Invalid Command", JOptionPane.INFORMATION_MESSAGE, wrongImage);
                    }
                }

            } else if (cmd.equals("2")) {
                partition();
            } else if (cmd.equals("3")) {
                while (true) {

                    String in = JOptionPane.showInputDialog(
                            "1 -> Check total errors caused by users.\n"
                            + "2 -> Display a table with usernames, time, association number and types of errors.\n"
                            + "3 -> Show a username list and number of errors caused by each user.\n"
                            + "4 -> Count errors within two months.\n"
                            + "5 -> Find errors in a given range of date.\n"
                            + "6 -> Display pie chart about number of errors in each month.\n"
                            + "7 -> Display a barchart about errors caused by each user.\n"
                            + "Q -> Back to main menu.");

                    HashSet<String> months = sortErrorsByMonths();
                    inputErrorsByMonths(months);
                    ArrayList<String> month = inputErrorsByMonths(months);
                    getAssociationNumber(month);
                    HashSet<String> users = findAndInputUsernames();
                    FindErrorsInEachMonth(months);
                    if (in.equals("1")) {
                        int errors = countErrors();
                        JOptionPane.showMessageDialog(null, "Total number of errors caused by users : " + errors, "Total errors", JOptionPane.INFORMATION_MESSAGE);
                    } else if (in.equals("2")) {
                        while (true) {

                            String inp = JOptionPane.showInputDialog(null, "A => Sort By Alphabet\nB => Sort By Months\nQ => Back To Main Menu", "Table display", JOptionPane.INFORMATION_MESSAGE);
                            if (inp.equalsIgnoreCase("A")) {
                                JOptionPane.showMessageDialog(null, "Table will be displayed at the terminal", "Table Display", JOptionPane.INFORMATION_MESSAGE, loading);
                                displayInfoInTable(users);

                            } else if (inp.equalsIgnoreCase("B")) {
                                JOptionPane.showMessageDialog(null, "Table will be displayed at the terminal", "Table Display", JOptionPane.INFORMATION_MESSAGE, loading);
                                displayInfoInTable(month);
                            } else if (inp.equalsIgnoreCase("Q")) {
                                break;
                            } else {
                                JOptionPane.showMessageDialog(null, "Not a valid command. Please enter a proper command", "Invalid Command", JOptionPane.INFORMATION_MESSAGE, wrongImage);
                            }
                        }
                    } else if (in.equals("3")) {
                        JOptionPane.showMessageDialog(null, "List will be displayed in the terminal.", "Total errors are displayed at the terminal.", JOptionPane.INFORMATION_MESSAGE, loading);
                        countErrorsCausedByUsers(users);
                    } else if (in.equals("4")) {

                        while (true) {
                            String a = JOptionPane.showInputDialog("Please enter the start of the month(enter q to quit): ");
                            if (a.equalsIgnoreCase("q")) {
                                break;
                            }
                            String b = JOptionPane.showInputDialog("Please enter the end of the month(enter q to quit): ");
                            if (b.equalsIgnoreCase("q")) {
                                break;
                            }

                            if (IsInteger(a, b)) {
                                int x = Integer.parseInt(a);
                                int y = Integer.parseInt(b);
                                int sum = countErrorsWithinMonths(x, y);
                                JOptionPane.showMessageDialog(null, "Total number of errors from " + a + " to " + b + " are : " + sum, "Number of errors in a given range", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            } else {
                                JOptionPane.showMessageDialog(null, "Month doesn't exist or Invalid Command. Please enter proper month !", "Invalid Command", JOptionPane.INFORMATION_MESSAGE, wrongImage);
                            }
                        }

                    } else if (in.equals("5")) {

                        while (true) {

                            String start = JOptionPane.showInputDialog("Enter the start of the date(yyyy-mm-dd) (Enter q to quit): ");
                            boolean validityA = isValidDateFormat(start);
                            if (start.equalsIgnoreCase("Q")) {
                                break;
                            } else if (validityA == false) {
                                JOptionPane.showMessageDialog(null, "Invalid date format. Please enter again.", "Invalid command", JOptionPane.INFORMATION_MESSAGE, wrongImage);
                                continue;
                            }

                            String end = JOptionPane.showInputDialog("Enter the end of the date(yyyy-mm-dd) (Enter q to quit): ");
                            boolean validityB = isValidDateFormat(end);
                            if (end.equalsIgnoreCase("Q")) {
                                break;
                            } else if (validityB == false) {
                                JOptionPane.showMessageDialog(null, "Invalid date format. Please enter again.", "Invalid command", JOptionPane.INFORMATION_MESSAGE, wrongImage);
                                continue;
                            }
                            JOptionPane.showMessageDialog(null, "Result will be displayed at the console.", null, JOptionPane.INFORMATION_MESSAGE, loading);
                            countErrorsBetween(start, end);
                        }

                    } else if (in.equals("6")) {

                        PieChart3D demo = new PieChart3D("Comparison", "Number of Errors In Particular Months");
                        demo.pack();
                        demo.setVisible(true);
                    } else if (in.equals("7")) {
                        EventQueue.invokeLater(() -> {
                            BarChartExample ex = new BarChartExample();
                            ex.setVisible(true);
                        });
                    } else if (in.equalsIgnoreCase("q")) {
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Not a valid command. Please enter a proper command", "Invalid Command", JOptionPane.INFORMATION_MESSAGE, wrongImage);
                    }
                    System.out.println("\n*".repeat(5));
                }

            } else if (cmd.equals("4")) {
                while (true) {

                    String cmd4 = JOptionPane.showInputDialog("1 -> Display a table with execution time of allocated job based on month.\n"
                            + "2 -> Show June average time.\n"
                            + "3 -> Show July average time.\n"
                            + "4 -> Show August average time.\n"
                            + "5 -> Show September average time.\n"
                            + "6 -> Show October average time.\n"
                            + "7 -> Show November average time.\n"
                            + "8 -> Show December average time.\n"
                            + "9 -> Show average time vs month graph.\n"
                            + "10 -> Total.\n"
                            + "Q -> Back to main menu.");
                    try {

                        BufferedReader reader1 = new BufferedReader(new FileReader(JOBDONE));
                        BufferedReader reader2 = new BufferedReader(new FileReader(JOBCREATED));

                        int totalcounter = 8189;
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
                        String sepminid = " 0";

                        double octmintotal = 10;
                        String octminid = "0";

                        double novmintotal = 10;
                        String novminid = "0";

                        double decmintotal = 100;
                        String decminid = "0";

                        int junecounter = 0;
                        int julycounter = 0;
                        int augcounter = 0;
                        int sepcounter = 0;
                        int octcounter = 0;
                        int novcounter = 0;
                        int deccounter = 0;

                        int junetotal = 0;
                        int julytotal = 0;
                        int augtotal = 0;
                        int septotal = 0;
                        int octtotal = 0;
                        int novtotal = 0;
                        int dectotal = 0;

                        int junemon = 0;
                        int julymon = 0;
                        int augmon = 0;
                        int sepmon = 0;
                        int octmon = 0;
                        int novmon = 0;
                        int decmon = 0;

                        int juned = 0;
                        int julyd = 0;
                        int augd = 0;
                        int sepd = 0;
                        int octd = 0;
                        int novd = 0;
                        int decd = 0;

                        int juneh = 0;
                        int julyh = 0;
                        int augh = 0;
                        int seph = 0;
                        int octh = 0;
                        int novh = 0;
                        int dech = 0;

                        double junes = 0;
                        double julys = 0;
                        double augs = 0;
                        double seps = 0;
                        double octs = 0;
                        double novs = 0;
                        double decs = 0;

                        int junem = 0;
                        int julym = 0;
                        int augm = 0;
                        int sepm = 0;
                        int octm = 0;
                        int novm = 0;
                        int decm = 0;

                        for (int i = 0; i < 8189; i++) {

                            String[] sepcre = reader2.readLine().split(" ");
                            String[] sepdon = reader1.readLine().split(" ");
                            String[] stimecre = sepcre[0].split("T");
                            String[] datecre = stimecre[0].split("-"); //moth
                            String[] timecre = stimecre[1].split(":");
                            String[] secondcre = timecre[2].split("]");

                            int hourcre = Integer.parseInt(timecre[0]);
                            int minutes = Integer.parseInt(timecre[1]);
                            double second = Double.parseDouble(secondcre[0]);
                            int daycre = Integer.parseInt(datecre[2]);
                            int monthcre = Integer.parseInt(datecre[1]);

                            String[] stimedon = sepdon[0].split("T");
                            String[] datedon = stimedon[0].split("-"); //month
                            String[] timedon = stimedon[1].split(":");
                            String[] seconddon = timedon[2].split("]");
                            int hourdon = Integer.parseInt(timedon[0]);
                            int minutesdon = Integer.parseInt(timedon[1]);
                            double seconddonn = Double.parseDouble(seconddon[0]);
                            int daydon = Integer.parseInt(datedon[2]);
                            int monthdon = Integer.parseInt(datedon[1]);

                            fsecond = getsecond(second, seconddonn);
                            fminit = getminutes(minutes, minutesdon);
                            fhour = gethour(hourcre, hourdon, daydon - daycre);
                            fday = getday(daycre, daydon, monthcre, monthdon, monthdon - monthcre);
                            fmonth = getmonth(monthcre, monthdon);
                            totalmonth = totalmonth + fmonth;
                            totalday = totalday + fday;
                            totalhour = totalhour + fhour;
                            totalminit = totalminit + fminit;
                            totalsec = totalsec + fsecond;

                            //    getmin(fday, fhour, fminit, fsecond, minid, mind, minm, minsec,minh,sepdon);
                            //    getmax(fday, fhour, fminit, fsecond, maxid, maxd, maxm, maxsec, maxh, sepdon);
                            if (gettotal(fday, fhour, fminit, fsecond) < mintotal) {
                                mintotal = gettotal(fday, fhour, fminit, fsecond);
                                minid = sepdon[2];
                            }

                            if (gettotal(fday, fhour, fminit, fsecond) > maxtotal) {
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

                                if (gettotal(fday, fhour, fminit, fsecond) < junemintotal) {
                                    junemintotal = gettotal(fday, fhour, fminit, fsecond);
                                    juneminid = sepdon[2];
                                }

                                if (gettotal(fday, fhour, fminit, fsecond) > junemaxtotal) {
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

                                if (gettotal(fday, fhour, fminit, fsecond) < julymintotal) {
                                    julymintotal = gettotal(fday, fhour, fminit, fsecond);
                                    julyminid = sepdon[2];
                                }

                                if (gettotal(fday, fhour, fminit, fsecond) > julymaxtotal) {
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
                                augcounter = augcounter + 1;

                                if (gettotal(fday, fhour, fminit, fsecond) < augmintotal) {
                                    augmintotal = gettotal(fday, fhour, fminit, fsecond);
                                    augminid = sepdon[2];
                                }

                                if (gettotal(fday, fhour, fminit, fsecond) > augmaxtotal) {
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

                                if (gettotal(fday, fhour, fminit, fsecond) < sepmintotal) {
                                    sepmintotal = gettotal(fday, fhour, fminit, fsecond);
                                    sepminid = sepdon[2];
                                }

                                if (gettotal(fday, fhour, fminit, fsecond) > sepmaxtotal) {
                                    sepmaxtotal = gettotal(fday, fhour, fminit, fsecond);
                                    sepmaxid = sepdon[2];
                                }

                            }
                            //    getavg(sepcounter,seps,sepd,seph,sepm);
                            if (monthcre == 10) {
                                octmon = octmon + fmonth;
                                octd = octd + fday;
                                octh = octh + fhour;
                                octm = octm + fminit;
                                octs = octs + fsecond;
                                octcounter = octcounter + 1;

                                if (gettotal(fday, fhour, fminit, fsecond) < octmintotal) {
                                    octmintotal = gettotal(fday, fhour, fminit, fsecond);
                                    octminid = sepdon[2];
                                }

                                if (gettotal(fday, fhour, fminit, fsecond) > octmaxtotal) {
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
                                novcounter = novcounter + 1;

                                if (gettotal(fday, fhour, fminit, fsecond) < novmintotal) {
                                    novmintotal = gettotal(fday, fhour, fminit, fsecond);
                                    novminid = sepdon[2];
                                }

                                if (gettotal(fday, fhour, fminit, fsecond) > novmaxtotal) {
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

                                if (gettotal(fday, fhour, fminit, fsecond) < decmintotal) {
                                    decmintotal = gettotal(fday, fhour, fminit, fsecond);
                                    decminid = sepdon[2];
                                }
                                if (gettotal(fday, fhour, fminit, fsecond) > decmaxtotal) {
                                    decmaxtotal = gettotal(fday, fhour, fminit, fsecond);
                                    decmaxid = sepdon[2];
                                }
                            }
                        }
                        if (cmd4.equals("1")) {
                            JOptionPane.showMessageDialog(null, "Result will be displayed at the console.", null, JOptionPane.INFORMATION_MESSAGE, loading);
                            System.out.println("Execution time of the jobs submitted based on month ");
                            System.out.println("------------------------------------------------------------------------");
                            System.out.printf("%-2s %-10s %-2s %-6s %-2s %-5s %-2s %-8s %-2s %-8s %-2s %-10s %-2s \n", "|", "Month", "|", "Months", "|", "Days", "|", "Hours", "|", "Minutes", "|", "Seconds", "|");
                            System.out.println("------------------------------------------------------------------------");
                            System.out.printf("%-2s %-10s %-2s %-6s %-2s %-5s %-2s %-8s %-2s %-8s %-2s %-10.3f %-2s \n", "|", "June", "|", junemon, "|", juned, "|", juneh, "|", junem, "|", junes, "|");
                            System.out.printf("%-2s %-10s %-2s %-6s %-2s %-5s %-2s %-8s %-2s %-8s %-2s %-10.3f %-2s \n", "|", "July", "|", julymon, "|", julyd, "|", julyh, "|", julym, "|", julys, "|");
                            System.out.printf("%-2s %-10s %-2s %-6s %-2s %-5s %-2s %-8s %-2s %-8s %-2s %-10.3f %-2s \n", "|", "August", "|", augmon, "|", augd, "|", augh, "|", augm, "|", augs, "|");
                            System.out.printf("%-2s %-10s %-2s %-6s %-2s %-5s %-2s %-8s %-2s %-8s %-2s %-10.3f %-2s \n", "|", "September", "|", sepmon, "|", sepd, "|", seph, "|", sepm, "|", seps, "|");
                            System.out.printf("%-2s %-10s %-2s %-6s %-2s %-5s %-2s %-8s %-2s %-8s %-2s %-10.3f %-2s \n", "|", "October", "|", octmon, "|", octd, "|", octh, "|", octm, "|", octs, "|");
                            System.out.printf("%-2s %-10s %-2s %-6s %-2s %-5s %-2s %-8s %-2s %-8s %-2s %-10.3f %-2s \n", "|", "Novemeber", "|", novmon, "|", novd, "|", novh, "|", novm, "|", novs, "|");
                            System.out.printf("%-2s %-10s %-2s %-6s %-2s %-5s %-2s %-8s %-2s %-8s %-2s %-10.3f %-2s \n", "|", "December", "|", decmon, "|", decd, "|", dech, "|", decm, "|", decs, "|");
                            System.out.println("------------------------------------------------------------------------");
                        } else if (cmd4.equals("2")) {
                            JOptionPane.showMessageDialog(null, "Result will be displayed at the console.", null, JOptionPane.INFORMATION_MESSAGE, loading);
                            System.out.printf("June average :%.3f seconds or ", getavg(junecounter, junes, juned, juneh, junem), " seconds or ");
                            stos(getavg(junecounter, junes, juned, juneh, junem));
                            System.out.printf("shortest runtime : " + juneminid + " ");
                            stos(junemintotal);
                            System.out.printf("longest runtime  : " + junemaxid + " ");
                            stos(junemaxtotal);
                            System.out.println();
                        } else if (cmd4.equals("3")) {
                            JOptionPane.showMessageDialog(null, "Result will be displayed at the console.", null, JOptionPane.INFORMATION_MESSAGE, loading);
                            System.out.printf("July average :%.3f seconds or ", getavg(julycounter, julys, julyd, julyh, julym));
                            stos(getavg(julycounter, julys, julyd, julyh, julym));
                            System.out.printf("shortest runtime : " + julyminid + " ");
                            stos(julymintotal);
                            System.out.printf("longest runtime  : " + julymaxid + " ");
                            stos(julymaxtotal);
                            System.out.println();
                        } else if (cmd4.equals("4")) {
                            JOptionPane.showMessageDialog(null, "Result will be displayed at the console.", null, JOptionPane.INFORMATION_MESSAGE, loading);
                            System.out.printf("August average :%.3f seconds or ", getavg(augcounter, augs, augd, augh, augm), " seconds or ");
                            stos(getavg(augcounter, augs, augd, augh, augm));
                            System.out.printf("shortest runtime : " + augminid + " ");
                            stos(augmintotal);
                            System.out.printf("longest runtime  : " + augmaxid + " ");
                            stos(augmaxtotal);
                            System.out.println();
                        } else if (cmd4.equals("5")) {
                            JOptionPane.showMessageDialog(null, "Result will be displayed at the console.", null, JOptionPane.INFORMATION_MESSAGE, loading);
                            System.out.printf("September average :%.3f seconds or ", getavg(sepcounter, seps, sepd, seph, sepm), " seconds or ");
                            stos(getavg(sepcounter, seps, sepd, seph, sepm));
                            System.out.printf("shortest runtime : " + sepminid + " ");
                            stos(sepmintotal);
                            System.out.printf("longest runtime  : " + sepmaxid + " ");
                            stos(sepmaxtotal);
                            System.out.println();
                        } else if (cmd4.equals("6")) {
                            JOptionPane.showMessageDialog(null, "Result will be displayed at the console.", null, JOptionPane.INFORMATION_MESSAGE, loading);
                            System.out.printf("October average :%.3f seconds or ", getavg(octcounter, octs, octd, octh, octm), " seconds or ");
                            stos(getavg(octcounter, octs, octd, octh, octm));
                            System.out.printf("shortest runtime : " + octminid + " ");
                            stos(octmintotal);
                            System.out.printf("longest runtime  : " + octmaxid + " ");
                            stos(octmaxtotal);
                            System.out.println();
                        } else if (cmd4.equals("7")) {
                            JOptionPane.showMessageDialog(null, "Result will be displayed at the console.", null, JOptionPane.INFORMATION_MESSAGE, loading);
                            System.out.printf("November average :%.3f seconds or ", getavg(novcounter, novs, novd, novh, novm), " seconds or ");
                            stos(getavg(novcounter, novs, novd, novh, novm));
                            System.out.printf("shortest runtime : " + novminid + " ");
                            stos(novmintotal);
                            System.out.printf("longest runtime  : " + novmaxid + " ");
                            stos(novmaxtotal);
                            System.out.println();
                        } else if (cmd4.equals("8")) {
                            JOptionPane.showMessageDialog(null, "Result will be displayed at the console.", null, JOptionPane.INFORMATION_MESSAGE, loading);
                            System.out.printf("December average :%.3f seconds or ", getavg(deccounter, decs, decd, dech, decm), " seconds or ");
                            stos(getavg(deccounter, decs, decd, dech, decm));
                            System.out.printf("shortest runtime : " + decminid + " ");
                            stos(decmintotal);
                            System.out.printf("longest runtime  : " + decmaxid + " ");
                            stos(decmaxtotal);
                            System.out.println();
                        } else if (cmd4.equals("9")) {
                            Assignment.LineChart_AWT chart = new Assignment.LineChart_AWT(
                                    "Average execution time by month",
                                    "Average execution time by month",
                                    getavg(junecounter, junes, juned, juneh, junem),
                                    getavg(julycounter, julys, julyd, julyh, julym),
                                    getavg(augcounter, augs, augd, augh, augm),
                                    getavg(sepcounter, seps, sepd, seph, sepm),
                                    getavg(octcounter, octs, octd, octh, octm),
                                    getavg(novcounter, novs, novd, novh, novm),
                                    getavg(deccounter, decs, decd, dech, decm));

                            chart.pack();
                            RefineryUtilities.centerFrameOnScreen(chart);
                            chart.setVisible(true);
                        } else if (cmd4.equals("10")) {
                            JOptionPane.showMessageDialog(null, "Result will be displayed at the console.", null, JOptionPane.INFORMATION_MESSAGE, loading);
                            System.out.println("total month :" + totalmonth);
                            System.out.println("total day :" + totalday);
                            System.out.println("total  hour :" + totalhour);
                            System.out.println("total minit :" + totalminit);
                            System.out.printf("total second : %.3f \n", totalsec);
                            System.out.printf("shortest runtime : " + minid + " ");
                            stos(mintotal);

                            System.out.printf("longest runtime  : " + maxid + " ");
                            stos(maxtotal);

                            System.out.printf("Average :  %.3f seconds or ", getavg(totalcounter, totalsec, totalday, totalhour, totalminit));
                            stos(getavg(totalcounter, totalsec, totalday, totalhour, totalminit));

                        } else if (cmd4.equalsIgnoreCase("Q")) {
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Not a valid command. Please enter a proper command", "Invalid Command", JOptionPane.INFORMATION_MESSAGE, wrongImage);
                        }

                    } catch (FileNotFoundException ex) {
                        System.out.println("File not found");
                    } catch (IOException ex) {
                    }
                }

            } else if (cmd.equals("5")) {
                while (true) {
                    String in = JOptionPane.showInputDialog("1 -> Display a graph and a table about total number of jobs being killed each month.\n"
                            + "2 -> Display a table about killed jobs with time, request, jobID and UID.\n"
                            + "3 -> Display a graph and a table about total number of jobs being returned per month\n"
                            + "4 -> Display a table about returned jobs with jobID, time, status and reason.\n"
                            + "Q -> Back to main menu.");
                    if (in.equals("1")) {
                        totalkilljobmonth();
                    } else if (in.equals("2")) {
                        JOptionPane.showMessageDialog(null, "Result will be displayed at the console.", null, JOptionPane.INFORMATION_MESSAGE, loading);
                        totalkilljob();
                    } else if (in.equals("3")) {
                        MonthReturnedJob();
                    } else if (in.equals("4")) {
                        JOptionPane.showMessageDialog(null, "Result will be displayed at the console.", null, JOptionPane.INFORMATION_MESSAGE, loading);
                        displaytReturnedMonthInTable();
                    } else if (in.equalsIgnoreCase("Q")) {
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Not a valid command. Please enter a proper command", "Invalid Command", JOptionPane.INFORMATION_MESSAGE, wrongImage);
                    }

                }

            } else if (cmd.equalsIgnoreCase("Q")) {
                JOptionPane.showMessageDialog(null, "System closed. Thanks for using!", "System Closed", JOptionPane.INFORMATION_MESSAGE, exitImage);
                JOptionPane.showMessageDialog(null, null, "Happy New Year !!", JOptionPane.INFORMATION_MESSAGE, cny);
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Not a valid command. Please enter a proper command", "Invalid Command", JOptionPane.INFORMATION_MESSAGE, wrongImage);
            }

            System.out.println(" ");

            for (int i = 5; i > 0; i--) {
                System.out.println("*".repeat(i));
            }
        }
    }

// -----------------------------------------------------------------------------------------------------------------------------
// Weili: 
    // function: read raw datas, find and count total number errors caused by users (done)
    public static int countErrors() {
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DEFAULT));
            PrintWriter writer = new PrintWriter(new FileWriter(DEFAULT1));

            String line;

            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("\\[.*\\].*(error: This association).*");
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    writer.println(line);
                    count++;
                }
            }
            writer.close();
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException e) {
        }
        return count;
    }

    // function: write the usernames into a file named "usernames.txt". (done)
    public static HashSet<String> findAndInputUsernames() {
        HashSet<String> users = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DEFAULT));
            PrintWriter writer = new PrintWriter(new FileWriter(DEFAULT2));

            String line;

            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("\\[.*\\].*(error: This association).*(user=)'(.*)'(.*)(,).*");
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    writer.println(matcher.group(3));
                    users.add(matcher.group(3));
                }
            }
            System.out.println(" ");
            writer.close();
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException e) {
        }
        return users;
    }

    // function: sort errors into different months (done)
    public static HashSet<String> sortErrorsByMonths() {
        HashSet<String> months = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DEFAULT1));
            String line;

            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("\\[.*-(.*)-[0-9]{2}.*\\].*(error: This association).*");
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    months.add(matcher.group(1));
                }
            }
            reader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException e) {
        }
        return months;
    }

    // function: input error according to different months into its respective file. 
    public static ArrayList<String> inputErrorsByMonths(HashSet<String> months) {
        ArrayList<String> month = new ArrayList<String>();
        try {
            BufferedReader reader = null;
            PrintWriter writer = null;
            String line;

            for (String set : months) {
                month.add(set);
            }
            Collections.sort(month);

            for (int i = 0; i < month.size(); i++) {
                reader = new BufferedReader(new FileReader(DEFAULT1));
                writer = new PrintWriter(new FileWriter("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\month" + month.get(i) + ".txt"));

                while ((line = reader.readLine()) != null) {
                    Pattern pattern = Pattern.compile("\\[.*-(" + month.get(i) + ")-[0-9]{2}.*\\].*(error: This association).*");
                    Matcher matcher = pattern.matcher(line);

                    if (matcher.matches()) {
                        writer.println(line);

                    }
                }
                writer.close();
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException e) {
        }
        return month;
    }

    // function: get association number of each line of error from text files.
    public static void getAssociationNumber(ArrayList<String> months) {
        try {
            BufferedReader reader = null;
            PrintWriter writer = null;
            String line;

            for (int i = 0; i < months.size(); i++) {
                reader = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\month" + months.get(i) + ".txt"));
                writer = new PrintWriter(new FileWriter("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\Association_num_month" + months.get(i) + ".txt"));
                while ((line = reader.readLine()) != null) {
                    Pattern pattern = Pattern.compile("\\[.*-(" + months.get(i) + ")-[0-9]{2}.*\\] (error: This association) ([0-9]*).*");
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.matches()) {
                        writer.println(matcher.group(3));
                    }
                }
                writer.close();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException ex) {
        }
    }

    // function: display username, time ,association number , and types of errors in a table.(sorted by alphabet)
    public static void displayInfoInTable(HashSet<String> users) {
        ArrayList<String> usersname = new ArrayList<String>();

        try {
            BufferedReader reader = null;
            for (String sets : users) {
                usersname.add(sets);
            }
            Collections.sort(usersname);

            String line;
            System.out.println(" ");
            System.out.printf("| %-27s | %-30s | %-30s | %-20s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30), "-".repeat(20));
            System.out.printf("| %-27s | %-30s | %-30s | %-20s |\n", "           Name", "            Time", "        Associations num", "     Error Types");
            System.out.printf("| %-27s | %-30s | %-30s | %-20s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30), "-".repeat(20));
            for (String name : usersname) {
                reader = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\errors.txt"));

                while ((line = reader.readLine()) != null) {
                    Pattern pattern = Pattern.compile("\\[(.*)\\] (error: This association) ([0-9]*).*(does not have access to )(.*)");
                    Matcher matcher = pattern.matcher(line);
                    if (line.contains(name) && matcher.matches()) {
                        System.out.printf("|            %-16s |     %-26s |               %-16s |      %-15s |\n", name, matcher.group(1), matcher.group(3), matcher.group(5));
                    }
                }
            }
            System.out.printf("| %-27s | %-30s | %-30s | %-20s |\n", "-".repeat(27), "-".repeat(30), "-".repeat(30), "-".repeat(20));
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
        }
    }

    // function: display username, time ,association number , and types of errors in a table.(sorted by months)
    public static void displayInfoInTable(ArrayList<String> months) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(DEFAULT1));
            String line;
            System.out.println(" ");
            System.out.printf("| %-50s | %-50s | %-50s | %-40s |\n", "-".repeat(50), "-".repeat(50), "-".repeat(50), "-".repeat(40));
            System.out.printf("| %-50s | %-50s | %-50s | %-40s |\n", "                     Name", "                      Time", "                 Associations num", "              Error Types");
            System.out.printf("| %-50s | %-50s | %-50s | %-40s |\n", "-".repeat(50), "-".repeat(50), "-".repeat(50), "-".repeat(40));

            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("\\[(.*)\\] (error: This association) ([0-9]*).*(user=)'(.*)'(.*)(,).*(does not have access to )(.*)");
                Matcher matcher = pattern.matcher(line);

                if (matcher.matches()) {
                    System.out.printf("|                     %-30s |               %-36s |                         %-26s |              %-27s |\n", matcher.group(5), matcher.group(1), matcher.group(3), matcher.group(9));

                }
            }

            System.out.printf("| %50s | %-50s | %-50s | %-40s |\n", "-".repeat(50), "-".repeat(50), "-".repeat(50), "-".repeat(40));

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException ex) {
        }
    }

    // function: display the number of errors caused by each user.
    public static void countErrorsCausedByUsers(HashSet<String> users) {

        ArrayList<String> usernames = new ArrayList<>();
        for (String set : users) {
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
                while ((line = reader.readLine()) != null) {
                    if (line.contains(name)) {
                        count++;
                    }
                }
                System.out.printf("%s %-17s |   %-1d\n", "Number of errors caused by ===>", name, count);
                writer.println(name + "," + count);
            }
            reader.close();
            writer.flush();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException ex) {
        };
    }

    // function: count total number of errors in given range of time inputted by user
    public static int countErrorsWithinMonths(int startMonth, int endMonth) {
        int sum = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DEFAULT1));
            String line;

            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("\\[.*-(.*)-.*\\] .*");
                Matcher matcher = pattern.matcher(line);
                matcher.matches();

                int month = Integer.parseInt(matcher.group(1));

                if (month <= endMonth && month >= startMonth) {
                    sum++;
                    continue;
                }

                if (month > endMonth) {
                    break;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException ex) {
        }
        return sum;
    }

    // function: to check whether the input of the range of months is an integer or not.
    public static boolean IsInteger(String startMonth, String endMonth) {
        try {
            int x = Integer.parseInt(startMonth);
            int y = Integer.parseInt(endMonth);

            if (x > y || x < 6) {
                JOptionPane.showMessageDialog(null, "Invalid range. The range is from year 2022 June to December.", "Invalid range", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }

            return (x <= 12 && x > 0) && (y <= 12 && y > 0);

        } catch (NumberFormatException e) {
            return false;
        }
    }

    // funtion: to input the errors into a file with date in the format of(dd/mm/yyyy)
    public static HashSet<String> getDateOfErrors() {
        HashSet<String> dates = new HashSet<>();
        try {

            BufferedReader reader = new BufferedReader(new FileReader(DEFAULT1));
            PrintWriter writer = new PrintWriter(new FileWriter(DEFAULT3));
            String line;

            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("\\[(.*-.*-[0-9]{2}).*\\] (error: This association) ([0-9]*).*(user=)'(.*)'(,)(.*)(does not have access to )(.*)");
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    writer.println(matcher.group(1) + "  " + matcher.group(3) + "  " + matcher.group(5) + "  " + matcher.group(9));
                    dates.add(matcher.group(1) + "-" + matcher.group(2) + "-" + matcher.group(3) + "\n");
                }
            }
            reader.close();
            writer.flush();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException ex) {
        };
        return dates;
    }

    // function: to display the errors in the range of date input by the users
    public static void countErrorsBetween(String start, String end) {

        try ( BufferedReader reader = new BufferedReader(new FileReader(DEFAULT3))) {
            String line;
            Scanner sc = new Scanner(System.in);
            int count = 0;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
            LocalDate startDate = LocalDate.parse(start, formatter);
            LocalDate endDate = LocalDate.parse(end, formatter);

            while ((line = reader.readLine()) != null) {

                LocalDate dateTime = LocalDate.parse(line.split("  ")[0]);
                if (!(dateTime.isBefore(startDate) || dateTime.isAfter(endDate))) {
                    count++;
                    System.out.println(line);
                }

            }
            System.out.println("\nTotal number of errors in the given range are: " + count);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException ex) {
        }
    }

    // function : check if the format of date inputted by user are correct or not;
    public static boolean isValidDateFormat(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        try {
            LocalDate.parse(date, formatter);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    //function : find number of errors caused by users in each month
    public static void FindErrorsInEachMonth(HashSet<String> months) {
        ArrayList<String> month = new ArrayList<>();
        try {
            BufferedReader reader = null;
            PrintWriter writer = new PrintWriter(new FileWriter(ERRORS));;
            String line;

            for (String set : months) {
                month.add(set);
            }
            Collections.sort(month);

            for (int i = 0; i < month.size(); i++) {
                int count = 0;
                reader = new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\FOP_Assignment\\yap\\month" + month.get(i) + ".txt"));

                while ((line = reader.readLine()) != null) {
                    Pattern pattern = Pattern.compile("\\[.*-(" + month.get(i) + ")-[0-9]{2}.*\\].*(error: This association).*");
                    Matcher matcher = pattern.matcher(line);

                    if (matcher.matches()) {
                        count++;
                    }
                }
                writer.println(month.get(i) + " " + count);

            }
            writer.flush();
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException e) {
        }
    }

// -----------------------------------------------------------------------------------------------------------------------------
// Adam
    public static double gettotal(int day, int hour, int minit, double sec) {
        return (day * 86400) + (hour * 3600) + (minit * 60) + sec;
    }

    public static void stos(double a) {
        int day = (int) a / 86400;
        int hour = (int) (a - day * 86400) / 3600;
        int min = (int) (a - hour * 3600 - day * 86400) / 60;
        double sec = a - hour * 3600 - min * 60 - day * 86400;

        System.out.printf(day + " day " + hour + " hours " + min + " minutes %.3f seconds \n", sec);
    }

    public static double getavg(int counter, double second, int a, int b, int c) {
        double total = (a * 86400) + (b * 3600) + (c * 60);
        return (second + total) / counter;
    }

    public static double getsecond(double a, double b) {
        double x;
        double y = 60 - a;
        x = y + b;
        return x;

    }

    public static int getminutes(int a, int b) {
        int x;
        int y = 60 - a;
        x = y + b;
        if (x > 0) {
            return x - 1;
        }
        return x;
    }

    public static int gethour(int a, int b, int c) { //c=daydiff
        int x;
        if (c != 0) {
            int y = 24 - a;
            x = y + b;

        } else {
            x = b - a;
        }
        if (x > 0) {
            return x - 1;
        }
        return x;
    }

    public static int getday(int a, int b, int c, int d, int e) { //e=month diff c,d month
        int x = 0;
        int y;
        int z = 0;
        if (e == 1) {
            if (c == 6 || c == 9 || c == 11) {
                y = 30 - a;
            } else {
                y = 31 - a;
            }

            x = b + y;
        } else if (e >= 2) {
            if (c == 6 || c == 9 || c == 11) {
                y = 30 - a;
            } else {
                y = 31 - a;
            }
            for (int i = a + 1; i <= b; i++) {
                if (i == 6 || i == 9 || i == 11) {
                    z = 30;
                    y = y + z;
                } else {
                    z = 31;
                    y = y + z;
                }
                x = y + b;
            }
        } else {
            x = b - a;
        }
        if (x > 0) {
            return x - 1;
        }
        return x;
    }

    public static int getmonth(int a, int b) {

        if (b - a <= 1) {
            return 0;
        } else {
            return b - a - 1;
        }

    }

    public static class LineChart_AWT extends ApplicationFrame {

        public LineChart_AWT(String applicationTitle, String chartTitle, double juneavg, double julyavg, double augavg, double sepavg, double octavg, double novavg, double decavg) {
            super(applicationTitle);
            JFreeChart lineChart = ChartFactory.createLineChart(
                    chartTitle,
                    "Month", "Average Time",
                    createDataset(juneavg, julyavg, augavg, sepavg, octavg, novavg, decavg),
                    PlotOrientation.VERTICAL,
                    true, true, false);

            ChartPanel chartPanel = new ChartPanel(lineChart);
            chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
            setContentPane(chartPanel);
        }

        private DefaultCategoryDataset createDataset(double juneavg, double julyavg, double augavg, double sepavg, double octavg, double novavg, double decavg) {
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(juneavg, "Seconds", "June");
            dataset.addValue(julyavg, "Seconds", "July");
            dataset.addValue(augavg, "Seconds", "August");
            dataset.addValue(sepavg, "Seconds", "September");
            dataset.addValue(octavg, "Seconds", "October");
            dataset.addValue(novavg, "Seconds", "November");
            dataset.addValue(decavg, "Seconds", "Decemebr");
            return dataset;
        }

    }

// -----------------------------------------------------------------------------------------------------------------------------
// Yishan
    public static void allJobs() {
        Scanner input = new Scanner(System.in);
        try {
            BufferedReader inputstream = new BufferedReader(new FileReader(DEFAULT));
            int month = 0;
            String check = "";
            String[] arrayMonth = {"06", "07", "08", "09", "10", "11", "12"};
            String[] Month = {"June", "July", "August", "September", "October", "November", "December"};

            System.out.print("Find job created or ended (C/E): ");
            char choice = input.next().charAt(0);

            if (choice == 'C' || choice == 'c') {
                check = "\\[(.*)] sched: Allocate JobId=(\\d+) NodeList=(.*) #CPUs=(\\d+) Partition=(.*)";//
            } else if (choice == 'E' || choice == 'e') {
                check = "\\[(.*)] _job_complete: JobId=(\\d+) done";
            }

            System.out.println("------------------------------------");
            System.out.printf("| %-15s | %-15s|\n", "Month", "Number of jobs");
            System.out.println("------------------------------------");
            int[] countAmount = new int[7];

            String line;

            for (int i = 0; i < arrayMonth.length; i++) {
                while ((line = inputstream.readLine()) != null) {
                    Pattern patternDate = Pattern.compile("\\[2022-(\\d+)-(.+)] (.*)");
                    Matcher matcherDate = patternDate.matcher(line);
                    Pattern pattern = Pattern.compile(check);
                    Matcher matcher = pattern.matcher(line);
                    matcherDate.find();
                    if (!matcherDate.group(1).equals(arrayMonth[i]) && matcher.matches()) {
                        countAmount[i + 1]++;
                        break;
                    }

                    if (matcher.matches()) {
                        countAmount[i]++;
                    }
                }
                System.out.printf("| %-15s | %5s%-10d|\n", Month[i], " ", countAmount[i]);
            }
            System.out.println("------------------------------------\n");
            if (choice == 'C' || choice == 'c') {
                PieChartforjobcreated(countAmount);
            }
            if (choice == 'E' || choice == 'e') {
                PieChartforjobended(countAmount); //for graph
            }
            inputstream.close();
        } catch (IOException e) {
            System.out.println("Input file problem");
        }

    }

    public static void jobRange() {
        Scanner input = new Scanner(System.in);
        String imonth = "", fmonth = null;

        System.out.print("Find job created or ended (C/E): ");
        char choice = input.next().charAt(0);

        try {
            Scanner inputstream = new Scanner(new FileInputStream(DEFAULT));
            String check = "";
            String[] arrayMonth = {"06", "07", "08", "09", "10", "11", "12"};
            String[] Month = {"June", "July", "August", "September", "October", "November", "December"};

            if (choice == 'C' || choice == 'c') {
                check = "\\[(.*)] sched: Allocate JobId=(\\d+) NodeList=(.*) #CPUs=(\\d+) Partition=(.*)";//
            } else if (choice == 'E' || choice == 'e') {
                check = "\\[(.*)] _job_complete: JobId=(\\d+) done";
            }

            System.out.print("Range of month from (june...december.) : ");//input initial month
            String month1 = input.next();
            System.out.print("To : ");  //input until month
            String month2 = input.next();

            for (int i = 0; i < arrayMonth.length; i++) {
                if (month1.equalsIgnoreCase(Month[i])) {
                    imonth = arrayMonth[i];
                }
                if (month2.equalsIgnoreCase(Month[i])) {
                    fmonth = arrayMonth[i];
                }

            }
            int num = 0;

            while (inputstream.hasNextLine()) {
                String read = inputstream.nextLine(); //read the text word by word
                Pattern pattern = Pattern.compile(check);
                Matcher matcher = pattern.matcher(read);
                Pattern patternDate = Pattern.compile("\\[2022-(\\d+)-(.+)] (.*)");
                Matcher matcherDate = patternDate.matcher(read);

                matcherDate.find();
                if (matcherDate.group(1).compareToIgnoreCase(imonth) >= 0 && matcherDate.group(1).compareToIgnoreCase(fmonth) <= 0) {   //to check the month
                    if (matcher.matches()) {
                        num++; //to compare the word allocate for job created and job_complete for job ended
                    }
                }
                if (matcherDate.group(1).compareTo(fmonth) > 0) {
                    break;  //to break if the program look the line which already pass the needed month
                }
            }

            System.out.printf("%s to %s : %d\n", month1, month2, num);
            inputstream.close();
        } catch (IOException e) {
            System.out.println("Input file problem");
        }

    }

    public static void JobRangeDay() {
        try {
            Scanner inputstream = new Scanner(new FileInputStream(DEFAULT));
            Scanner input = new Scanner(System.in);
            String imonth = "", fmonth = null;
            int checkDouble;
            int checkmonth;

            System.out.print("Find job created or ended (C/E): ");
            char choice = input.next().charAt(0);
            String check = "", date = "";
            String[] arrayMonth = {"06", "07", "08", "09", "10", "11", "12"};
            String[] Month = {"June", "July", "August", "September", "October", "November", "December"};

            if (choice == 'C' || choice == 'c') {
                check = "\\[(.*)] sched: Allocate JobId=(\\d+) NodeList=(.*) #CPUs=(\\d+) Partition=(.*)";
                date = "\\[2022-(\\d+)-(\\d+)T(.+)] (.*)";
            } else if (choice == 'E' || choice == 'e') {
                check = "\\[(.*)] _job_complete: JobId=(\\d+) done";
                date = "\\[2022-(\\d+)-(\\d+)T(.+)] (.*)";
            }

            System.out.print("Range of month(june...december) and day(1-31): ");//input initial month
            String month1 = input.next();
            int day1 = input.nextInt();
            System.out.print("To : ");  //input until month
            String month2 = input.next();
            int day2 = input.nextInt();

            for (int i = 0; i < arrayMonth.length; i++) {
                if (month1.equalsIgnoreCase(Month[i])) {
                    imonth = arrayMonth[i];
                }
                if (month2.equalsIgnoreCase(Month[i])) {
                    fmonth = arrayMonth[i];
                }
            }
            String ini = day1 > 9 ? "" : "0";
            String fin = day2 > 9 ? "" : "0";
            String iday = ini + day1;
            String fday = fin + day2;
            int count = 0;
            String previousDate = iday;
            String checkMonth = imonth;
            String previousDay = iday;

            System.out.println("------------------------------------------------------");
            System.out.printf("| %-15s | %-15s | %-15s|\n", "Month", "Day", "Number");
            System.out.println("------------------------------------------------------");
            while (inputstream.hasNextLine()) {
                String line = inputstream.nextLine();
                Pattern patternDate = Pattern.compile(date);
                Matcher matcherDate = patternDate.matcher(line);
                Pattern pattern = Pattern.compile(check);
                Matcher matcher = pattern.matcher(line);

                matcherDate.find();
                if (matcher.matches()) {   //to check the month
                    if (matcherDate.group(1).compareToIgnoreCase(imonth) >= 0 && matcherDate.group(1).compareToIgnoreCase(fmonth) <= 0) {
                        if (matcherDate.group(2).compareTo(iday) == 0) {
                            count++; //to compare the word allocate for job created and job_complete for job ended
                        } else if (matcherDate.group(2).compareTo(iday) >= 0 && matcherDate.group(1).compareTo(fmonth) < 0) { //need to put more than 0 to prevent the initial day is not 1
                            System.out.printf("|%-15s s| %-15s | %-15d|\n", Integer.parseInt(matcherDate.group(1)), Integer.parseInt(matcherDate.group(2)) - 1, count);
                            count = 1;
                            checkDouble = Integer.parseInt(iday) + 1;
                            iday = checkDouble > 9 ? checkDouble + "" : "0" + checkDouble;
                        } else if (matcherDate.group(2).compareTo(iday) >= 0 && matcherDate.group(2).compareTo(fday) <= 0) {// use during the final month
                            System.out.printf("|%-15s t| %-15d | %-15d|\n", Integer.parseInt(matcherDate.group(1)), Integer.parseInt(previousDate), count);
                            count = 1;
                            checkDouble = Integer.parseInt(iday) + 1;
                            iday = checkDouble > 9 ? checkDouble + "" : "0" + checkDouble;
                        } else if (matcherDate.group(2).compareTo(iday) < 0 && matcherDate.group(1).compareTo(checkMonth) > 0) {// use when the period that change month
                            System.out.printf("|%-15d r| %-15d | %-15d|\n", Integer.parseInt(matcherDate.group(1)) - 1, Integer.parseInt(previousDate), count);
                            count = 1;
                            checkmonth = Integer.parseInt(checkMonth) + 1;
                            checkMonth = checkmonth > 9 ? checkMonth + "" : "0" + checkmonth;
                            iday = "01";
                        } else if (matcherDate.group(1).compareTo(fmonth) == 0 && matcherDate.group(2).compareTo(fday) > 0) {//for the last day
                            System.out.printf("|%-15s a| %-15s | %-15d|\n", Integer.parseInt(matcherDate.group(1)), Integer.parseInt(matcherDate.group(2)) - 1, count);
                            break;
                        }
                        previousDate = matcherDate.group(2);
                    }
                    if (matcherDate.group(1).compareTo(fmonth) > 0) {//use when the last day is the end of the month
                        System.out.printf("|%-15d d| %-15d | %-15d|\n", Integer.parseInt(matcherDate.group(1)) - 1, Integer.parseInt(previousDate), count);
                        break;
                    }
                    int checkNo = Integer.parseInt(iday) + 1;
                    String isCount = checkNo > 9 ? checkNo + "" : "0" + checkNo;
                    if (matcherDate.group(2).compareTo(isCount) >= 0 && count == 1) { //when one day is no change
                        System.out.printf("|%-15s b| %-15s | %-15d|\n", Integer.parseInt(matcherDate.group(1)), Integer.parseInt(matcherDate.group(2)) - 1, 0);
                        checkDouble = Integer.parseInt(iday) + 1;
                        iday = checkDouble > 9 ? checkDouble + "" : "0" + checkDouble;
                    }

                }

            }
            inputstream.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void PieChartforjobcreated(int[] countAmount) {

        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("June\n" + " 24% ", countAmount[0]);
        result.setValue("July\n" + " 14% ", countAmount[1]);
        result.setValue("August\n" + " 13% ", countAmount[2]);
        result.setValue("September\n" + " 14% ", countAmount[3]);
        result.setValue("October\n" + " 19% ", countAmount[4]);
        result.setValue("November\n" + " 10% ", countAmount[5]);
        result.setValue("December\n" + " 6% ", countAmount[6]);

        //Create a chart
        JFreeChart chart = ChartFactory.createPieChart3D("Total Number of Job Created ", result, true, true, false);
        //Create a panel to display the chart
        ChartPanel panel = new ChartPanel(chart);
        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 27));
        chart.getTitle().setPaint(Color.BLACK);
        chart.getLegend().setItemFont(new Font("Arial", Font.ITALIC, 18));
        chart.getLegend().setBackgroundPaint(Color.LIGHT_GRAY);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setSectionPaint(0, Color.magenta);
        plot.setSectionPaint(1, Color.LIGHT_GRAY);
        plot.setSectionPaint(2, Color.CYAN);
        plot.setSectionPaint(3, Color.YELLOW);
        plot.setSectionPaint(4, Color.blue);
        plot.setSectionPaint(5, Color.RED);
        plot.setSectionPaint(6, Color.PINK);
        plot.setLabelFont(new Font("Arial", Font.PLAIN, 14));
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");

        //Add the panel to a frame
        JFrame frame = new JFrame("Bar Chart Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void PieChartforjobended(int[] countAmount) {

        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("June\n" + " 22% ", countAmount[0]);
        result.setValue("July\n" + " 13% ", countAmount[1]);
        result.setValue("August\n" + " 14% ", countAmount[2]);
        result.setValue("September\n" + " 14% ", countAmount[3]);
        result.setValue("October\n" + " 18% ", countAmount[4]);
        result.setValue("November\n" + " 13% ", countAmount[5]);
        result.setValue("December\n" + " 5% ", countAmount[6]);

        //Create a chart
        JFreeChart chart = ChartFactory.createPieChart3D("Total Number of Job Ended ", result, true, true, false);
        //Create a panel to display the chart
        ChartPanel panel = new ChartPanel(chart);
        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 27));
        chart.getTitle().setPaint(Color.BLACK);
        chart.getLegend().setItemFont(new Font("Arial", Font.ITALIC, 18));
        chart.getLegend().setBackgroundPaint(Color.LIGHT_GRAY);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setSectionPaint(0, Color.GREEN);
        plot.setSectionPaint(1, Color.ORANGE);
        plot.setSectionPaint(2, Color.BLUE);
        plot.setSectionPaint(3, Color.magenta);
        plot.setSectionPaint(4, Color.YELLOW);
        plot.setSectionPaint(5, Color.cyan);
        plot.setSectionPaint(6, Color.PINK);
        plot.setLabelFont(new Font("Arial", Font.PLAIN, 14));
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");

        //Add the panel to a frame
        JFrame frame = new JFrame("Bar Chart Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void kill() {
        try {
            Scanner inputstream = new Scanner(new FileInputStream(DEFAULT));

        } catch (IOException e) {
            e.getMessage();
        }

    }

    public static void partition() {
        Scanner input = new Scanner(System.in);
        String imonth = "", fmonth = "";
        try {
            Scanner inputstream = new Scanner(new FileInputStream(DEFAULT));
            String[] arrayMonth = {"06", "07", "08", "09", "10", "11", "12"};
            String[] Month = {"June", "July", "August", "September", "October", "November", "December"};
            String[] arrayPartition = {"gpu-k10", "gpu-k40c", "gpu-v100s", "gpu-titan", "cpu-opteron", "cpu-epyc"};
            int[] numPartition = new int[6];

            System.out.print("Which month do you want to see?(june...december): "); //To ask the range of month or all month
            String ask = input.next();
            System.out.print("To :");
            String month2 = input.next();

            for (int i = 0; i < arrayMonth.length; i++) {
                if (ask.equalsIgnoreCase(Month[i])) {
                    imonth = arrayMonth[i];
                }
                if (month2.equalsIgnoreCase(Month[i])) {
                    fmonth = arrayMonth[i];
                }
            }

            System.out.println("-------------------------------------");
            System.out.printf("| %-20s | %-2s%-9s|\n", "Type of partition", " ", "Amount");
            System.out.println("-------------------------------------");

            while (inputstream.hasNextLine()) {
                String read = inputstream.nextLine();
                Pattern pattern = Pattern.compile("\\[2022-(\\d+)-(.*)T(.*)] sched: Allocate JobId=(\\d+) NodeList=(.*) #CPUs=(\\d+) Partition=(.*)");
                Matcher matcher = pattern.matcher(read);
                Pattern patternDate = Pattern.compile("\\[2022-(\\d+)-(.+)] (.*)");
                Matcher matcherDate = patternDate.matcher(read);

                matcherDate.find();
                if (matcherDate.group(1).compareToIgnoreCase(imonth) >= 0 && matcherDate.group(1).compareToIgnoreCase(fmonth) <= 0) {
                    if (matcher.matches()) {
                        for (int i = 0; i < arrayPartition.length; i++) {
                            if (matcher.group(7).equalsIgnoreCase(arrayPartition[i])) {
                                numPartition[i]++;
                                break;
                            }
                        }
                    }
                }
                if (matcherDate.group(1).compareTo(fmonth) > 0) {
                    break;
                }
            }

            for (int i = 0; i < numPartition.length; i++) {
                System.out.printf("| %-20s | %3s%-8d|\n", arrayPartition[i], " ", numPartition[i]);
            }
            System.out.println("-------------------------------------");

            inputstream.close();
            if (imonth.equals("06") && fmonth.equals("12")) {//only show graph when user want june to december
                PieChartforpartition(numPartition);
            }
        } catch (IOException e) {
            System.out.println("Input file problem");
        }
        System.out.println("Which partition do you want to look");
        String partition = input.next();
        partitionDetail(partition, imonth, fmonth);
    }

    public static void partitionDetail(String partition, String imonth, String fmonth) {
        int count = 0;
        try {
            Scanner inputstream = new Scanner(new FileInputStream(DEFAULT));
            System.out.println("------------------------------------------------------");
            System.out.printf("| %-15s | %-15s | %-15s|\n", "Date", "Time", "Job ID");
            System.out.println("------------------------------------------------------");
            while (inputstream.hasNextLine()) {
                String line = inputstream.nextLine();
                Pattern pattern = Pattern.compile("\\[2022-(\\d+)-(.+)] (.*)");
                Matcher matcher = pattern.matcher(line);
                Pattern patternPrint = Pattern.compile("\\[(.*)T(.*)] sched: Allocate JobId=(\\d+) NodeList=(.*) #CPUs=(\\d+) Partition=(.*)");
                Matcher matcherPrint = patternPrint.matcher(line);

                matcher.find();
                if (matcher.group(1).compareToIgnoreCase(imonth) >= 0 && matcher.group(1).compareToIgnoreCase(fmonth) <= 0) {
                    if (matcherPrint.matches()) {
                        if (matcherPrint.group(6).equalsIgnoreCase(partition)) {
                            System.out.printf("| %-15s | %-15s | %-15s|\n", matcherPrint.group(1), matcherPrint.group(2), matcherPrint.group(3));
                        }
                    }
                }
            }

            inputstream.close();
        } catch (IOException e) {
            e.getMessage();
        }
        System.out.println("------------------------------------------------------\n");
    }

    public static void PieChartforpartition(int[] numPartition) {

        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("gpu-k10\n" + " 5% ", numPartition[0]);
        result.setValue("gpu-k40c\n" + " 3% ", numPartition[1]);
        result.setValue("gpu-v100s\n" + " 6% ", numPartition[2]);
        result.setValue("gpu-titan\n" + " 7% ", numPartition[3]);
        result.setValue("cpu-opteron\n" + " 49%", numPartition[4]);
        result.setValue("cpu-epyc\n" + " 30% ", numPartition[5]);

        //Create a chart
        JFreeChart chart = ChartFactory.createPieChart3D("Type of Partition ", result, true, true, false);
        //Create a panel to display the chart
        ChartPanel panel = new ChartPanel(chart);
        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 27));
        chart.getTitle().setPaint(Color.BLACK);
        chart.getLegend().setItemFont(new Font("Arial", Font.ITALIC, 18));
        chart.getLegend().setBackgroundPaint(Color.LIGHT_GRAY);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setSectionPaint(0, Color.GREEN);
        plot.setSectionPaint(1, Color.ORANGE);
        plot.setSectionPaint(2, Color.BLUE);
        plot.setSectionPaint(3, Color.magenta);
        plot.setSectionPaint(4, Color.RED);
        plot.setSectionPaint(5, Color.cyan);
        plot.setLabelFont(new Font("Arial", Font.PLAIN, 14));
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");

        //Add the panel to a frame
        JFrame frame = new JFrame("Bar Chart Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

// -----------------------------------------------------------------------------------------------------------------------------
//  cheeyan:
    public static void totalkilljobmonth() {

        int[] successcount = new int[7];
        int[] requestcount = new int[7];
        int[] failurecount = new int[7];
        String[] Month = {"June", "July", "August", "September", "October", "November", "December"};

        System.out.println("Total number of job being killed successfully per month: ");
        System.out.println();

        try {

            Scanner scanner = new Scanner(new FileInputStream(DEFAULT));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("_slurm_rpc_kill_job")) {
                    if (line.contains("REQUEST_KILL_JOB")) {
                        checkMonth(line, requestcount);
                    }
                    if (line.contains("job_str_signal()")) {
                        checkMonth(line, failurecount);
                    }
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
        } catch (IOException e) {
            System.out.println("Problem with file input.");
        }

        for (int i = 0; i < successcount.length; i++) {
            successcount[i] = requestcount[i] - failurecount[i];
        }

        System.out.printf("+ %-20s%17s +\n", "-".repeat(23), "-".repeat(30));
        System.out.printf("| %-20s | %17s     |\n", "Month", "     Number Of Job Killed ");
        System.out.printf("| %-20s | %17s |\n", "-".repeat(20), "-".repeat(30));
        for (int i = 0; i < successcount.length - 1; i++) {
            System.out.printf("| %-20s | %17d              |\n", Month[i], successcount[i]);
            System.out.printf("| %-20s | %17s |\n", "-".repeat(20), "-".repeat(30));
        }
        System.out.printf("| %-20s | %17d              |\n", "December", successcount[6]);
        System.out.printf("+ %-20s%17s +\n", "-".repeat(23), "-".repeat(30));
        System.out.println();

        PieChartforsuccessjobkill(successcount);
    }

    public static void checkMonth(String line, int[] monthcount) {
        Pattern pattern = Pattern.compile("\\[2022-(\\d+)-(.+)](.+)");
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        switch (matcher.group(1)) {
            case "06":
                monthcount[0]++;
                break;
            case "07":
                monthcount[1]++;
                break;
            case "08":
                monthcount[2]++;
                break;
            case "09":
                monthcount[3]++;
                break;
            case "10":
                monthcount[4]++;
                break;
            case "11":
                monthcount[5]++;
                break;
            case "12":
                monthcount[6]++;
                break;
        }
    }

    public static void totalkilljob() {

        String[] successDate = new String[2336];
        String[] successRequest = new String[2336];
        String[] successId = new String[2336];
        String[] successUid = new String[2336];
        String[] failDate = new String[44];
        String[] failRequest = new String[44];
        String[] failId = new String[44];
        String[] failUid = new String[44];
        String[] failReason = new String[44];
        int successCount = 0;
        int failCount = 0;
        String id = null;

        System.out.println("Details for job successfully being killed per month: \n");

        try {

            Scanner scanner = new Scanner(new FileInputStream(DEFAULT));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Pattern pattern = Pattern.compile("\\[(2022-\\d+-\\d+T.+)] _slurm_rpc_kill_job: (REQUEST_KILL_JOB) JobId=(\\d+) uid (\\d+)");
                Matcher matcher = pattern.matcher(line);
                Pattern failedpattern = Pattern.compile("\\[(2022-\\d+-\\d+T.+)] _slurm_rpc_kill_job: (job_str_signal(.+)) uid=(\\d+) JobId=(\\d+) sig=9 returned: (.+)");
                Matcher failedmatcher = failedpattern.matcher(line);
                matcher.find();
                failedmatcher.find();

                if (line.contains("job_str_signal()")) {
                    if (failedmatcher.group(5).compareToIgnoreCase(id) == 0) {
                        successDate[successCount - 1] = null;
                        successRequest[successCount - 1] = null;
                        successId[successCount - 1] = null;
                        successUid[successCount - 1] = null;
                        successCount--;
                    }

                    failDate[failCount] = failedmatcher.group(1);
                    failRequest[failCount] = failedmatcher.group(2);
                    failId[failCount] = failedmatcher.group(5);
                    failUid[failCount] = failedmatcher.group(4);
                    failReason[failCount] = failedmatcher.group(6);
                    failCount++;
                }

                if (matcher.matches()) {
                    successDate[successCount] = matcher.group(1);
                    successRequest[successCount] = matcher.group(2);
                    successId[successCount] = matcher.group(3);
                    successUid[successCount] = matcher.group(4);
                    id = matcher.group(3);
                    successCount++;
                }
            }

            scanner.close();

            int counter = 0;

            System.out.printf("+ %-27s%-30s%-30s%-24s +\n", "-".repeat(37), "-".repeat(30), "-".repeat(30), "-".repeat(27));
            System.out.printf("| %-27s | %-30s | %-35s |  %-23s|\n", "           JobID", "            Date", "              Request", "         uid");
            System.out.printf("| %-27s%-30s%-30s%-24s |\n", "-".repeat(37), "-".repeat(30), "-".repeat(30), "-".repeat(27));

            for (int i = 0; i < successDate.length; i++) {
                System.out.printf("|            %-16s |     %-26s |           %-25s |        %10s       | \n", successId[i], successDate[i], successRequest[i], successUid[i]);
                counter++;
            }

            System.out.printf("| %-27s%-30s%-30s%-24s |\n", "-".repeat(37), "-".repeat(30), "-".repeat(30), "-".repeat(27));
            System.out.printf("|" + " ".repeat(11) + " Total number of jobs being killed in all months is: " + counter + " ".repeat(57) + "|\n");
            System.out.printf("+ %-27s%-30s%-30s%-24s +\n", "-".repeat(37), "-".repeat(30), "-".repeat(30), "-".repeat(27));
            System.out.println();

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
        } catch (IOException e) {
            System.out.println("Problem with file input.");
        }
    }

    public static void TotalReturnedJob() {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(DEFAULT));
            PrintWriter writer = new PrintWriter(new FileWriter(D1));

            String line = "";
            int count = 0;

            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile(".*(sig=9 returned).*");
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    writer.println(line);
                    count++;
                }
            }
            System.out.println();

            reader.close();
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
        } catch (IOException e) {
            System.out.println("Problem with file input.");
        }
    }

    public int[] monthCount(String line, int[] count) {

        if (line.contains("2022-06")) {
            count[0] += 1;
        } else if (line.contains("2022-07")) {
            count[1] += 1;
        } else if (line.contains("2022-08")) {
            count[2] += 1;
        } else if (line.contains("2022-09")) {
            count[3] += 1;
        } else if (line.contains("2022-10")) {
            count[4] += 1;
        } else if (line.contains("2022-11")) {
            count[5] += 1;
        } else if (line.contains("2022-12")) {
            count[6] += 1;
        }
        return count;
    }

    public static void MonthReturnedJob() {

        String[] Month = {"June", "July", "August", "September", "October", "November", "December"};
        int[] count = new int[7];
        for (int i = 0; i < 7; i++) {
            count[i] = 0;
        }
        System.out.println("Total number of job being returned per month: ");
        System.out.println();

        try {

            Scanner abc = new Scanner(new FileInputStream(D1));

            while (abc.hasNextLine()) {
                String line = abc.nextLine();

                if (line.contains("2022-06")) {
                    count[0] += 1;
                } else if (line.contains("2022-07")) {
                    count[1] += 1;
                } else if (line.contains("2022-08")) {
                    count[2] += 1;
                } else if (line.contains("2022-09")) {
                    count[3] += 1;
                } else if (line.contains("2022-10")) {
                    count[4] += 1;
                } else if (line.contains("2022-11")) {
                    count[5] += 1;
                } else if (line.contains("2022-12")) {
                    count[6] += 1;
                }
            }

            System.out.printf("+ %-20s%17s +\n", "-".repeat(23), "-".repeat(30));
            System.out.printf("| %-20s | %17s   |\n", "Month", "     Number Of Job Returned ");
            System.out.printf("| %-20s | %17s |\n", "-".repeat(20), "-".repeat(30));
            for (int i = 0; i < count.length - 1; i++) {
                System.out.printf("| %-20s | %17d              |\n", Month[i], count[i]);
                System.out.printf("| %-20s | %17s |\n", "-".repeat(20), "-".repeat(30));
            }
            System.out.printf("| %-20s | %17d              |\n", "December", count[6]);
            System.out.printf("+ %-20s%17s +\n", "-".repeat(23), "-".repeat(30));
            System.out.println();

            PieChartforreturnedjob(count);

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
        } catch (IOException e) {
            System.out.println("Problem with file input.");
        }
    }

    public static void displaytReturnedMonthInTable() {

        try {

            System.out.println();
            System.out.println("Details for job being returned per month: ");

            BufferedReader reader = new BufferedReader(new FileReader(D1));

            int count = 0;
            String line;

            System.out.println(" ");
            System.out.printf("+ %-27s%-30s%-30s%-33s +\n", "-".repeat(37), "-".repeat(30), "-".repeat(30), "-".repeat(36));
            System.out.printf("| %-27s | %-30s | %-30s |  %-33s    |\n", "           JobID", "            Date", "           Status", "               Reason");
            System.out.printf("| %-27s%-30s%-30s%-33s |\n", "-".repeat(37), "-".repeat(30), "-".repeat(30), "-".repeat(36));

            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("\\[(.*)\\] .* (uid=)([0-9]*) (JobId=)([0-9]*) (sig=9 returned:) (.*)");
                Matcher matcher = pattern.matcher(line);

                if (matcher.matches()) {
                    System.out.printf("|            %-16s |     %-26s |           %-20s |        %10s       | \n", matcher.group(5), matcher.group(1), "returned", matcher.group(7));
                    count++;
                }
            }

            System.out.printf("| %-27s%-30s%-16s%-30s |\n", "-".repeat(37), "-".repeat(30), "-".repeat(30), "-".repeat(36));
            System.out.printf("|" + " ".repeat(12) + "Total number of jobs being returned in all months is: " + count + " ".repeat(67) + "|\n");
            System.out.printf("+ %-27s%-30s%-16s%-30s +\n", "-".repeat(37), "-".repeat(30), "-".repeat(30), "-".repeat(36));

            reader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
        }
    }

    public static void PieChartforsuccessjobkill(int[] count) {

        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("June\n" + " 25% ", count[0]);
        result.setValue("July\n" + " 12% ", count[1]);
        result.setValue("August\n" + " 18% ", count[2]);
        result.setValue("September\n" + " 10% ", count[3]);
        result.setValue("October\n" + " 13% ", count[4]);
        result.setValue("November\n" + " 13% ", count[5]);
        result.setValue("December\n" + " 9% ", count[6]);

        //Create a chart
        JFreeChart chart = ChartFactory.createPieChart3D("Total number of job successfully being killed ", result, true, true, false);
        //Create a panel to display the chart
        ChartPanel panel = new ChartPanel(chart);
        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 27));
        chart.getTitle().setPaint(Color.BLACK);
        chart.getLegend().setItemFont(new Font("Arial", Font.ITALIC, 18));
        chart.getLegend().setBackgroundPaint(Color.LIGHT_GRAY);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setSectionPaint(0, Color.GREEN);
        plot.setSectionPaint(1, Color.RED);
        plot.setSectionPaint(2, Color.BLUE);
        plot.setSectionPaint(3, Color.magenta);
        plot.setSectionPaint(4, Color.darkGray);
        plot.setSectionPaint(5, Color.cyan);
        plot.setSectionPaint(6, Color.YELLOW);
        plot.setLabelFont(new Font("Arial", Font.PLAIN, 14));
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");

        //Add the panel to a frame
        JFrame frame = new JFrame("Bar Chart Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void PieChartforreturnedjob(int[] count) {

        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("June\n" + " 14% ", count[0]);
        result.setValue("July\n" + " 57% ", count[1]);
        result.setValue("August\n" + " 7% ", count[2]);
        result.setValue("September\n" + " 5% ", count[3]);
        result.setValue("October\n" + " 5% ", count[4]);
        result.setValue("November\n" + " 14% ", count[5]);
        result.setValue("December\n" + " 0% ", count[6]);

        //Create a chart
        JFreeChart chart = ChartFactory.createPieChart3D("Total number of job being returned ", result, true, true, false);
        //Create a panel to display the chart
        ChartPanel panel = new ChartPanel(chart);
        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 30));
        chart.getTitle().setPaint(Color.BLACK);
        chart.getLegend().setItemFont(new Font("Arial", Font.ITALIC, 18));
        chart.getLegend().setBackgroundPaint(Color.LIGHT_GRAY);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setSectionPaint(0, Color.RED);
        plot.setSectionPaint(1, Color.ORANGE);
        plot.setSectionPaint(2, Color.YELLOW);
        plot.setSectionPaint(3, Color.GREEN);
        plot.setSectionPaint(4, Color.BLUE);
        plot.setSectionPaint(5, Color.cyan);
        plot.setSectionPaint(6, Color.PINK);
        plot.setLabelFont(new Font("Arial", Font.PLAIN, 14));
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");

        //Add the panel to a frame
        JFrame frame = new JFrame("Bar Chart Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
