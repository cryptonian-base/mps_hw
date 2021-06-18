public class App {
    static final int THREADS = 2000;   // number threads to test
    static final int ROUNDS = 1;    // how many rounds to test

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        System.out.format("Testing %d threads, %d rounds\n", THREADS, ROUNDS); 
        SenseBarrier instance = new SenseBarrier(THREADS);
        //CombiningTreeBarrier instance = new CombiningTreeBarrier(THREADS, 2);
        //TournamentTreeBarrier instance = new TournamentTreeBarrier(THREADS);
        //DisseminationBarrier instance = new DisseminationBarrier(THREADS);
        //StaticTreeBarrier instance = new StaticTreeBarrier(THREADS,2);

        Thread[] thread = new Thread[THREADS];
        int[] log = new int[THREADS];
        for (int i = 0; i < THREADS; i++)
          log[i] = 2005;
        for (int j = 0; j < THREADS; j++) {
          thread[j] = new TestThread(j, THREADS, ROUNDS, instance, log);
        }

        long beginTime = System.currentTimeMillis();
        for (int j = 0; j < THREADS; j++) {
          thread[j].start();
        }
        try {
          for (int j = 0; j < THREADS; j++) {
            thread[j].join();
          }
        } catch (InterruptedException e) {
          //fail("interrupted exception");
          System.out.println("");
        }
        long finishTime = System.currentTimeMillis();

        System.out.format("Lapsed Time : %d m-seconds\n", (finishTime-beginTime));

    }
}

