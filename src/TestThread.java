public class TestThread extends Thread {
    int threads;
    int rounds;
    Barrier bar;
    int[] log;
    int index;
    public TestThread(int index, int threads, int rounds, Barrier bar, int[] log) {
      this.threads = threads;
      this.rounds = rounds;
      this.bar = bar;
      this.log = log;
      this.index = index;
    }
    public void run() {
      ThreadID.set(index);
      int sense = 0;
      for (int round = 0; round < rounds; round++) {
        log[index] = round;
        bar.await();  // let writers finish
        for (int i = 0; i < threads; i++) {
          if (log[i] != round) {
            System.out.format("%d\tError expected %d found %d at %d\n", index, round, log[i], i);
          }
        }
        bar.await();  // let readers finish
        sense = 1 - sense;
      }
    }
  }