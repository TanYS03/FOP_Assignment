/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assign;



/**
 *
 * @author adamt
 */
    
 



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adamt
 */
public class averagetime {
    public static void main(String[] args) {
        try {
         
            BufferedReader    bw = new BufferedReader(new FileReader("jobdone.txt"));
            BufferedReader    br = new BufferedReader(new FileReader("jobcreated.txt"));
            
            int totalcounter =7530;
           int fminit;
           int fhour;
           int fday;
           int fmonth;
           double fsecond;
            
            int totalmonth=0;
            int totalday=0;
            int totalhour=0;
            int totalminit=0;
            double totalsec=0;
            
            
            double maxtotal=10;  
            String maxid="0";
            
            double junemaxtotal=10;        
            String junemaxid="0";
            
           double julymaxtotal=10;
            String julymaxid="0";
            
            double augmaxtotal=10;
            String augmaxid="0";
            
            double sepmaxtotal=10;
            String sepmaxid="0";
            
            double octmaxtotal=10;
            String octmaxid="0";
            
           double novmaxtotal=10;
            String novmaxid="0";
            
            double decmaxtotal=10;
            String decmaxid="0";
            
            
             double mintotal=10;        
            String minid="0";
            
            double junemintotal=100;        
            String juneminid="0";
            
          double julymintotal=100;
           String julyminid="0";
            
            double augmintotal=10;
            String augminid="0";
            
           double sepmintotal=100;
            String sepminid="0";
            
            double octmintotal=10;
            String octminid="0";
            
            double novmintotal=10;
            String novminid="0";
            
            double decmintotal=100;
            String decminid="0";
            
            int junecounter=0;
            int julycounter=0;
            int augcounter=0;
            int sepcounter=0;
            int octcounter=0;
            int novcounter=0;
            int deccounter=0;
            
            int junetotal=0;
            int julytotal=0;
            int augtotal=0;
            int septotal=0;
            int octtotal=0;
            int novtotal=0;
            int dectotal=0;
            
            int junemon=0;
            int julymon=0;
            int augmon=0;
            int sepmon=0;
            int octmon=0;
            int novmon=0;
            int decmon=0;
            
            int juned=0;
            int julyd=0;
            int augd=0;
            int sepd=0;
            int octd=0;
            int novd=0;
            int decd=0;
            
            int juneh=0;
            int julyh=0;
            int augh=0;
            int seph=0;
            int octh=0;
            int novh=0;
            int dech=0;
            
            double junes=0;
            double julys=0;
            double augs=0;
            double seps=0;
            double octs=0;
            double novs=0;
            double decs=0;
            
            int junem=0;
            int julym=0;
            int augm=0;
            int sepm=0;
            int octm=0;
            int novm=0;
            int decm=0;
            
            
            for (int i = 0; i < 7530; i++) {
                
            
            String [] sepcre=br.readLine().split(" ");
            String [] sepdon=bw.readLine().split(" ");
  
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
               
                 
                    if(gettotal(fday,fhour,fminit,fsecond)<mintotal){
                       mintotal=gettotal(fday,fhour,fminit,fsecond);
                       minid=sepdon[2];
                   }
                   if(gettotal(fday,fhour,fminit,fsecond)>maxtotal){
                       maxtotal=gettotal(fday,fhour,fminit,fsecond);
                       maxid=sepdon[2];
                   }
                    
                
          //   junetotal(juned,juneh,junem,junes,junecounter, monthcre, datecre , fday, fhour, fminit,fsecond);
            if (monthcre==6) {
            junemon=junemon+fmonth;       
            juned= juned+fday;
            juneh= juneh+fhour;
            junem= junem+fminit;
            junes= junes+fsecond;
            junecounter=junecounter+1;
            
            if(gettotal(fday,fhour,fminit,fsecond)<junemintotal){
                       junemintotal=gettotal(fday,fhour,fminit,fsecond);
                       juneminid=sepdon[2];
                   }
                   if(gettotal(fday,fhour,fminit,fsecond)>junemaxtotal){
                       junemaxtotal=gettotal(fday,fhour,fminit,fsecond);
                       junemaxid=sepdon[2];
                   }
            }
             // getavg(junecounter,junes,juned,juneh,junem);
               if (monthcre==7) {
            julymon=julymon+fmonth;  
            julyd= julyd+fday;
            julyh= julyh+fhour;
            julym= julym+fminit;
            julys= julys+fsecond;
            julycounter=julycounter+1;
            
            if(gettotal(fday,fhour,fminit,fsecond)<julymintotal){
                       julymintotal=gettotal(fday,fhour,fminit,fsecond);
                       julyminid=sepdon[2];
                   }
                   if(gettotal(fday,fhour,fminit,fsecond)>julymaxtotal){
                       julymaxtotal=gettotal(fday,fhour,fminit,fsecond);
                       julymaxid=sepdon[2];
                   }
                
        
        }
              //  getavg(julycounter,julys,julyd,julyh,julym);
            if (monthcre==8) {
            augmon=augmon+fmonth;  
            augd= augd+fday;
            augh= augh+fhour;
            augm= augm+fminit;
            augs= augs+fsecond;
            augcounter=augcounter+1;
                  if(gettotal(fday,fhour,fminit,fsecond)<augmintotal){
                       augmintotal=gettotal(fday,fhour,fminit,fsecond);
                       augminid=sepdon[2];
                   }
                   if(gettotal(fday,fhour,fminit,fsecond)>augmaxtotal){
                       augmaxtotal=gettotal(fday,fhour,fminit,fsecond);
                       augmaxid=sepdon[2];
                   }
        
        }
           //   getavg(augcounter,augs,augd,augh,augm);
            if (monthcre==9) {
            sepmon=sepmon+fmonth;  
            sepd= sepd+fday;
            seph= seph+fhour;
            sepm= sepm+fminit;
            seps= seps+fsecond;
            sepcounter=sepcounter+1;
            
            if(gettotal(fday,fhour,fminit,fsecond)<sepmintotal){
                       sepmintotal=gettotal(fday,fhour,fminit,fsecond);
                       sepminid=sepdon[2];
                   }
                   if(gettotal(fday,fhour,fminit,fsecond)>sepmaxtotal){
                       sepmaxtotal=gettotal(fday,fhour,fminit,fsecond);
                       sepmaxid=sepdon[2];
                   }
        
        }
         //    getavg(sepcounter,seps,sepd,seph,sepm);
            if (monthcre==10) {
            octmon=octmon+fmonth;  
            octd= octd+fday;
            octh= octh+fhour;
            octm= octm+fminit;
            octs= octs+fsecond;
            octcounter=octcounter+1;
            
               if(gettotal(fday,fhour,fminit,fsecond)<octmintotal){
                       octmintotal=gettotal(fday,fhour,fminit,fsecond);
                       octminid=sepdon[2];
                   }
                   if(gettotal(fday,fhour,fminit,fsecond)>octmaxtotal){
                       octmaxtotal=gettotal(fday,fhour,fminit,fsecond);
                       octmaxid=sepdon[2];
                   }
        
        
        }
        //     getavg(octcounter,octs,octd,octh,octm);
            if (monthcre==11) {
            novmon=novmon+fmonth;  
            novd= novd+fday;
            novh= novh+fhour;
            novm= novm+fminit;
            novs= novs+fsecond;
            novcounter=novcounter+1;
              if(gettotal(fday,fhour,fminit,fsecond)<novmintotal){
                       novmintotal=gettotal(fday,fhour,fminit,fsecond);
                       novminid=sepdon[2];
                   }
                   if(gettotal(fday,fhour,fminit,fsecond)>novmaxtotal){
                       novmaxtotal=gettotal(fday,fhour,fminit,fsecond);
                       novmaxid=sepdon[2];
                   }
        
        
        }
       //     getavg(novcounter,novs,novd,novh,novm);
             if (monthcre==12) {
            decmon=decmon+fmonth;  
            decd= decd+fday;
            dech= dech+fhour;
            decm= decm+fminit;
            decs= decs+fsecond;
            deccounter=deccounter+1;    

            if(gettotal(fday,fhour,fminit,fsecond)<decmintotal){
                       decmintotal=gettotal(fday,fhour,fminit,fsecond);
                       decminid=sepdon[2];
                   }
                   if(gettotal(fday,fhour,fminit,fsecond)>decmaxtotal){
                       decmaxtotal=gettotal(fday,fhour,fminit,fsecond);
                       decmaxid=sepdon[2];
                   }
        }
  
            
            }
            System.out.println("Execution time of allocated job based on month");
            System.out.printf("%-10s %-6s %-5s %-8s %-8s %-20s \n","Month","Months","Days","Hours","Minutes","Seconds");
            System.out.printf("%-10s %-6s %-5s %-8s %-8s %-20.3f \n","June",junemon,juned,juneh,junem,junes);
            System.out.printf("%-10s %-6s %-5s %-8s %-8s %-20.3f \n","July",julymon,julyd,julyh,julym,julys);
            System.out.printf("%-10s %-6s %-5s %-8s %-8s %-20.3f \n","August",augmon,augd,augh,augm,augs);
            System.out.printf("%-10s %-6s %-5s %-8s %-8s %-20.3f \n","September",sepmon,sepd,seph,sepm,seps);
            System.out.printf("%-10s %-6s %-5s %-8s %-8s %-20.3f \n","October",octmon,octd,octh,octm,octs);
            System.out.printf("%-10s %-6s %-5s %-8s %-8s %-20.3f \n","Novemeber",novmon,novd,novh,novm,novs);
            System.out.printf("%-10s %-6s %-5s %-8s %-8s %-20.3f \n\n","December",decmon,decd,dech,decm,decs);
            
            System.out.printf("June average :%.3f seconds or ",getavg(junecounter,junes,juned,juneh,junem)," seconds or ");
            stos(getavg(junecounter,junes,juned,juneh,junem));
             System.out.printf("shortest runtime : "+juneminid+" ");
             stos(junemintotal);
            System.out.printf("longest runtime  : "+junemaxid+" ");
            stos(junemaxtotal);
            System.out.println();
            
             System.out.printf("July average :%.3f seconds or ", getavg(julycounter,julys,julyd,julyh,julym));
            stos( getavg(julycounter,julys,julyd,julyh,julym));
             System.out.printf("shortest runtime : "+julyminid+" ");
             stos(julymintotal);
            System.out.printf("longest runtime  : "+julymaxid+" ");
            stos(julymaxtotal);
            System.out.println();
            
             System.out.printf("August average :%.3f seconds or ", getavg(augcounter,augs,augd,augh,augm)," seconds or ");
            stos( getavg(augcounter,augs,augd,augh,augm));
             System.out.printf("shortest runtime : "+augminid+" ");
             stos(augmintotal);
            System.out.printf("longest runtime  : "+augmaxid+" ");
            stos(augmaxtotal);
            System.out.println();
            
             System.out.printf("September average :%.3f seconds or ",getavg(sepcounter,seps,sepd,seph,sepm)," seconds or ");
            stos(getavg(sepcounter,seps,sepd,seph,sepm));
            System.out.printf("shortest runtime : "+sepminid+" ");
             stos(sepmintotal);
            System.out.printf("longest runtime  : "+sepmaxid+" ");
            stos(sepmaxtotal);
            System.out.println();
            
             System.out.printf("October average :%.3f seconds or ",getavg(octcounter,octs,octd,octh,octm)," seconds or ");
            stos(getavg(octcounter,octs,octd,octh,octm));
             System.out.printf("shortest runtime : "+octminid+" ");
             stos(octmintotal);
            System.out.printf("longest runtime  : "+octmaxid+" ");
            stos(octmaxtotal);
            System.out.println();
            
             System.out.printf("November average :%.3f seconds or ",getavg(novcounter,novs,novd,novh,novm)," seconds or ");
            stos(getavg(novcounter,novs,novd,novh,novm));
             System.out.printf("shortest runtime : "+novminid+" ");
             stos(novmintotal);
            System.out.printf("longest runtime  : "+novmaxid+" ");
            stos(novmaxtotal);
            System.out.println();
            
             System.out.printf("December average :%.3f seconds or ",getavg(deccounter,decs,decd,dech,decm)," seconds or ");
            stos(getavg(deccounter,decs,decd,dech,decm));
             System.out.printf("shortest runtime : "+decminid+" ");
             stos(decmintotal);
            System.out.printf("longest runtime  : "+decmaxid+" ");
            stos(decmaxtotal);
            System.out.println();
            
            System.out.println("total month :"+totalmonth);
            System.out.println("total day :"+totalday);
            System.out.println("total  hour :"+totalhour);
            System.out.println("total minit :"+totalminit);
            System.out.printf("total second : %.3f \n",totalsec); 
            System.out.printf("shortest runtime : "+minid+" ");
            stos(mintotal);
            
            System.out.printf("longest runtime  : "+maxid+ " ");
            stos(maxtotal);
          //  double avg =  (totalsec+secondconvert(totalday,totalhour,totalminit))/7530;
                
             System.out.printf("Average :  %.3f seconds or ",getavg(totalcounter,totalsec,totalday,totalhour,totalminit));
             stos(getavg(totalcounter,totalsec,totalday,totalhour,totalminit));    
               
            
            
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(gettime.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(gettime.class.getName()).log(Level.SEVERE, null, ex);
        }
   
         

         
    }
    
    
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
        }
       
        else{
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


