package org.example;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            BufferedReader br= new BufferedReader(new FileReader("extracted_log.txt"));
            ArrayList<String> rawdata = new ArrayList<>();
            Scanner sc=new Scanner(System.in);

            int i=0;       //extarct all
            rawdata.add(i,br.readLine());
            while(rawdata.get(i)!=null){

                i++;
                rawdata.add(i,br.readLine());
            }
            System.out.print("Range of month from (june...december.) : "); //input
            String month1=sc.nextLine();
            System.out.print("To : ");
            String month2=sc.nextLine();

            int mon1=0;
            if ("june".equalsIgnoreCase(month1)) {    //convert string to int
                mon1=6;
            }
            else if ("july".equalsIgnoreCase(month1)) {
                mon1=7;
            }
            else if ("august".equalsIgnoreCase(month1)) {
                mon1=8;
            }
            else if ("september".equalsIgnoreCase(month1)) {
                mon1=9;
            }
            else  if ("october".equalsIgnoreCase(month1)) {
                mon1=10;
            }
            else  if ("novemeber".equalsIgnoreCase(month1)) {
                mon1=11;
            }
            else  if ("december".equalsIgnoreCase(month1)) {
                mon1=12;
            }

            int mon2=0;
            if ("june".equalsIgnoreCase(month2)) {     //convert string to int
                mon2=6;
            }
            else if ("july".equalsIgnoreCase(month2)) {
                mon2=7;
            }
            else if ("august".equalsIgnoreCase(month2)) {
                mon2=8;
            }
            else if ("september".equalsIgnoreCase(month2)) {
                mon2=9;
            }
            else  if ("october".equalsIgnoreCase(month2)) {
                mon2=10;
            }
            else  if ("novemeber".equalsIgnoreCase(month2)) {
                mon2=11;
            }
            else  if ("december".equalsIgnoreCase(month2)) {
                mon2=12;
            }

            int jobreqcounter=0;
            int monjune=0;

            if (mon1>mon2) {
                int hold =mon1;         //if mon1 start from bigger month, change with mon2
                mon1=mon2;
                mon2=hold;
            }



            for (int x = mon1; x <= mon2; x++) {

                if(x==6){
                    for (int j = 0;j<rawdata.size()-1; j++) {          //split line by space
                        String [] jobreq= rawdata.get(j).split(" ");

                        for (String a:jobreq) {
                            if ("Allocate".equals(a)) {     //find "Allocate"

                                String [] getmonth=jobreq[0].split("-");   //then split [2022-06-01T04:05:04.586]

                                for (String b: getmonth) {
                                    if ("06".equals(b)) {    //find 06  june
                                        monjune++;
                                    }
                                }
                            }
                        }
                    }
                }

                if(x==7){
                    for (int j = 0;j<rawdata.size()-1; j++) {
                        String [] jobreq= rawdata.get(j).split(" ");    //split line by space

                        for (String a:jobreq) {
                            if ("Allocate".equals(a)) {

                                String [] getmonth=jobreq[0].split("-");

                                for (String b: getmonth) {
                                    if ("07".equals(b)) {
                                        monjune++;
                                    }
                                }
                            }

                        }
                    }
                }

                if(x==8){

                    for (int j = 0;j<rawdata.size()-1; j++) {
                        String [] jobreq= rawdata.get(j).split(" ");

                        for (String a:jobreq) {
                            if ("Allocate".equals(a)) {

                                String [] getmonth=jobreq[0].split("-");

                                for (String b: getmonth) {
                                    if ("08".equals(b)) {
                                        monjune++;
                                    }
                                }
                            }

                        }
                    }
                }


                if(x==9){

                    for (int j = 0;j<rawdata.size()-1; j++) {
                        String [] jobreq= rawdata.get(j).split(" ");

                        for (String a:jobreq) {
                            if ("Allocate".equals(a)) {

                                String [] getmonth=jobreq[0].split("-");

                                for (String b: getmonth) {
                                    if ("09".equals(b)) {
                                        monjune++;
                                    }
                                }
                            }

                        }
                    }
                }


                if(x==10){

                    for (int j = 0;j<rawdata.size()-1; j++) {
                        String [] jobreq= rawdata.get(j).split(" ");

                        for (String a:jobreq) {
                            if ("Allocate".equals(a)) {

                                String [] getmonth=jobreq[0].split("-");

                                for (String b: getmonth) {
                                    if ("10".equals(b)) {
                                        monjune++;
                                    }
                                }
                            }

                        }
                    }

                }

                if(x==11){
                    for (int j = 0;j<rawdata.size()-1; j++) {
                        String [] jobreq= rawdata.get(j).split(" ");

                        for (String a:jobreq) {
                            if ("Allocate".equals(a)) {

                                String [] getmonth=jobreq[0].split("-");

                                for (String b: getmonth) {
                                    if ("11".equals(b)) {
                                        monjune++;
                                    }
                                }
                            }

                        }
                    }

                }

                if(x==12){

                    for (int j = 0;j<rawdata.size()-1; j++) {
                        String [] jobreq= rawdata.get(j).split(" ");

                        for (String a:jobreq) {
                            if ("Allocate".equals(a)) {

                                String [] getmonth=jobreq[0].split("-");

                                for (String b: getmonth) {
                                    if ("12".equals(b)) {
                                        monjune++;
                                    }
                                }
                            }

                        }
                    }
                }
            }

            System.out.println("Number of jobs from "+month1+" to "+month2+" = "+monjune);
        }catch (FileNotFoundException ex) {
            Logger.getLogger(monthallocatecounter.class.getName()).log(Level.SEVERE, null, ex);




        }

    }
}
