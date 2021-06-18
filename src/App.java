public class App {
    //static final int THREADS = 13;   // number threads to test
    static final int ROUNDS = 2;    // how many rounds to test
    //static final int THREADS3 = 27;
    // Sense (8/8), CombiningTree (8/8/2 , 27/8/3[X])
    // TournamentTree (8/8) , DisseminationBarrier (8/8)
    // Static Barrier (7/1/2, 13/1,3)

    public static void main(String[] args) throws Exception {
      
        //System.out.println("Hello, World!");
        //System.out.format("Testing %d threads, %d rounds\n", THREADS, ROUNDS);

        int[] thread_list = {8, 16, 32, };// 64};

        for (int t_index = 0; t_index< thread_list.length; t_index++) {
          int THREADS = thread_list[t_index];

          SenseBarrier instance = new SenseBarrier(THREADS);
          //CombiningTreeBarrier instance = new CombiningTreeBarrier(THREADS3, 3);
          //TournamentTreeBarrier instance = new TournamentTreeBarrier(THREADS);
          //DisseminationBarrier instance = new DisseminationBarrier(THREADS);
          //StaticTreeBarrier instance = new StaticTreeBarrier(THREADS,3);

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

          System.out.format("[%d Threads] Lapsed Time : %d m-seconds\n",THREADS, (finishTime-beginTime));
        }
    }
}

