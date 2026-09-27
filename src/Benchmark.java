import java.util.Random;

public class Benchmark {
    private static final int REPETITIONS = 5;
    private static final int[] N_VALUES = {100, 1000, 10000, 100000};

    public static void main(String[] args) {
        System.out.println("Starting Benchmarks...");

        runWorkload1RandomAccess();
        runWorkload2Search();
        runWorkload3InsertionRemoval();
        runWorkload4PriorityProcessing();

        System.out.println("All benchmarks completed!");
    }

    private static void runWorkload1RandomAccess() {
        System.out.println("\n--- Workload 1: Random Access ---");
        for (int n : N_VALUES) {
            long totalTimeArray = 0;
            long totalTimeList = 0;

            for (int r = 0; r < REPETITIONS; r++) {
                Random rand = new Random(42);
                DynamicArray array = new DynamicArray(n);
                LinkedList list = new LinkedList();

                for (int i = 0; i < n; i++) {
                    int val = rand.nextInt();
                    array.add(val);
                    list.add(val);
                }

                int[] indices = new int[10000];
                for (int i = 0; i < 10000; i++) {
                    indices[i] = rand.nextInt(n);
                }

                long startArray = System.nanoTime();
                for (int idx : indices) {
                    array.get(idx);
                }
                totalTimeArray += (System.nanoTime() - startArray);

                long startList = System.nanoTime();
                for (int idx : indices) {
                    list.get(idx);
                }
                totalTimeList += (System.nanoTime() - startList);
            }

            System.out.printf("n = %d | Array Avg Time: %.2f ms | List Avg Time: %.2f ms%n",
                    n, (totalTimeArray / (double) REPETITIONS) / 1_000_000.0,
                    (totalTimeList / (double) REPETITIONS) / 1_000_000.0);
        }
    }

    private static void runWorkload2Search() {
        System.out.println("\n--- Workload 2: Search ---");
        for (int n : N_VALUES) {
            long totalTimeArray = 0;
            long totalTimeList = 0;

            for (int r = 0; r < REPETITIONS; r++) {
                Random rand = new Random(42);
                DynamicArray array = new DynamicArray(n);
                LinkedList list = new LinkedList();

                for (int i = 0; i < n; i++) {
                    int val = rand.nextInt();
                    array.add(val);
                    list.add(val);
                }

                int[] searchVals = new int[1000];
                for (int i = 0; i < 1000; i++) {
                    searchVals[i] = rand.nextInt();
                }

                long startArray = System.nanoTime();
                for (int val : searchVals) {
                    array.contains(val);
                }
                totalTimeArray += (System.nanoTime() - startArray);

                long startList = System.nanoTime();
                for (int val : searchVals) {
                    list.contains(val);
                }
                totalTimeList += (System.nanoTime() - startList);
            }

            System.out.printf("n = %d | Array Search Time: %.2f ms | List Search Time: %.2f ms%n",
                    n, (totalTimeArray / (double) REPETITIONS) / 1_000_000.0,
                    (totalTimeList / (double) REPETITIONS) / 1_000_000.0);
        }
    }

    private static void runWorkload3InsertionRemoval() {
        System.out.println("\n--- Workload 3: Insertion & Removal (Beginning vs Middle) ---");
        for (int n : N_VALUES) {
            long totalTimeArrayIns0 = 0;
            long totalTimeListIns0 = 0;

            for (int r = 0; r < REPETITIONS; r++) {
                Random rand = new Random(42);
                DynamicArray array = new DynamicArray(n + 1000);
                LinkedList list = new LinkedList();

                for (int i = 0; i < n; i++) {
                    int val = rand.nextInt();
                    array.add(val);
                    list.add(val);
                }

                long startArray = System.nanoTime();
                for (int i = 0; i < 1000; i++) {
                    array.add(0, i);
                }
                totalTimeArrayIns0 += (System.nanoTime() - startArray);

                long startList = System.nanoTime();
                for (int i = 0; i < 1000; i++) {
                    list.add(0, i);
                }
                totalTimeListIns0 += (System.nanoTime() - startList);
            }

            System.out.printf("n = %d | Array Ins(0) Time: %.2f ms | List Ins(0) Time: %.2f ms%n",
                    n, (totalTimeArrayIns0 / (double) REPETITIONS) / 1_000_000.0,
                    (totalTimeListIns0 / (double) REPETITIONS) / 1_000_000.0);
        }
    }

    private static void runWorkload4PriorityProcessing() {
        System.out.println("\n--- Workload 4: Priority Processing (Min-Heap) ---");
        for (int n : N_VALUES) {
            long totalTimeInsert = 0;
            long totalTimeExtract = 0;

            for (int r = 0; r < REPETITIONS; r++) {
                Random rand = new Random(42);
                int[] data = new int[n];
                for (int i = 0; i < n; i++) {
                    data[i] = rand.nextInt();
                }

                MinHeap heap = new MinHeap(n);

                long startIns = System.nanoTime();
                for (int val : data) {
                    heap.insert(val);
                }
                totalTimeInsert += (System.nanoTime() - startIns);

                long startExt = System.nanoTime();
                int prev = Integer.MIN_VALUE;
                for (int i = 0; i < n; i++) {
                    int current = heap.extractMin();
                    if (current < prev) {
                        throw new IllegalStateException("Heap order violated!");
                    }
                    prev = current;
                }
                totalTimeExtract += (System.nanoTime() - startExt);
            }

            System.out.printf("n = %d | Heap Insert Time: %.2f ms | Heap Extract Time: %.2f ms%n",
                    n, (totalTimeInsert / (double) REPETITIONS) / 1_000_000.0,
                    (totalTimeExtract / (double) REPETITIONS) / 1_000_000.0);
        }
    }
}