// Creating a thread by extending Thread class
class MyThread extends Thread {

    private String threadName;

    // Constructor
    MyThread(String threadName) {
        this.threadName = threadName;
    }

    // Overriding run() method
    public void run() {
        for (int i = 1; i <= 5; i++) {

            System.out.println(threadName + " : " + i);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(threadName + " interrupted");
            }
        }
    }
}

// Main class
class Main2 {
    public static void main(String[] args) {

        MyThread t1 = new MyThread("Thread-1");
        MyThread t2 = new MyThread("Thread-2");

        t1.start();
        t2.start();
    }
}