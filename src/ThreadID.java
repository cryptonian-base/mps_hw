public class ThreadID {
    /**
     * The next thread ID to be assigned
     **/
    private static volatile int nextID = 0;
    /**
     * My thread-local ID.
     **/
    private static ThreadLocalID threadID = new ThreadLocalID();
    public static int get() {
      return threadID.get();
    }
    public static void set(int index) {
      threadID.set(index);
    }
    public static void reset() {
      nextID = 0;
    }
    private static class ThreadLocalID extends ThreadLocal<Integer> {
      protected synchronized Integer initialValue() {
        return nextID++;
      }
    }
  }