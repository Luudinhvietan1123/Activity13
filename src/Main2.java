import java.util.Random;

public class Main2 implements Runnable{

    public static String rdnb;

    public Main2(){
        rdnb = null;
    }

    public synchronized void setAndCheck() throws InterruptedException {
        Random random = new Random();
        if(rdnb == null){
            int randomNumber = random.nextInt((9999 - 1000) + 1) + 1000;
            System.out.print("Random year " + randomNumber);
            rdnb = String.valueOf(randomNumber);
            Thread.sleep(2000);
        }else{
            int randomNumber = Integer.valueOf(rdnb);
            if(randomNumber%4==0 && randomNumber%100!=0){
                System.out.print(" is leap year");
            }else{
                System.out.print(" is not leap year");
            }
            rdnb = null;
            System.out.println();
        }
    }

    public void run(){
        try{
            setAndCheck();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Main2 thread1 = new Main2();
        Main2 thread2 = new Main2();
        while(true){
            thread1.run();
            thread2.run();
        }
    }
}
