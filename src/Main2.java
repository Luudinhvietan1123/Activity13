import java.util.Random;

public class Main2 extends Thread{

    public static String rdnb;

    public synchronized void setAndCheck() throws InterruptedException {
        Random random = new Random();
        if(this.getName() == "set") {
            if (rdnb == null) {
                int randomNumber = random.nextInt((9999 - 1000) + 1) + 1000;
                System.out.print("Random year (" + this.getName() + ") " + randomNumber);
                rdnb = String.valueOf(randomNumber);
                Thread.sleep(2000);
            }
        }
        if(this.getName() == "check") {
            if (rdnb != null) {
                int randomNumber = Integer.valueOf(rdnb);
                if (randomNumber % 4 == 0 && randomNumber % 100 != 0) {
                    System.out.print(" is leap year (" + this.getName() + ")");
                } else {
                    System.out.print(" is not leap year (" + this.getName() + ")");
                }
                rdnb = null;
                System.out.println();
            }
        }
    }

    @Override
    public void run(){
        while(true){
            try{
                setAndCheck();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args ){
        Thread thread1 = new Main2();
        thread1.setName("set");
        Thread thread2 = new Main2();
        thread2.setName("check");
        thread1.start();
        thread2.start();
        // must be start() not run()
    }

    // Test case 2: blocking queue
}
