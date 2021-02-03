package memory;

public class Memory {
    public static void mem() {
        Runtime rt = Runtime.getRuntime();
        rt.gc();
        System.err.println("Allocated memory : " + (rt.totalMemory() - rt.freeMemory()) / 1000000 + " Mb");
        System.err.flush();
    }

    public static void heap() {
        long heap_size = Runtime.getRuntime().totalMemory();
        System.out.println("heap size is :: " + heap_size);
    }
}
