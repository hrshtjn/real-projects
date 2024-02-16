import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class GenerationalGarbageCollector {

    // Young Generation space
    private List<WeakReference<Object>> youngGen;

    // Old Generation space
    private List<WeakReference<Object>> oldGen;

    // Threshold for promotion from young to old generation
    private final int promotionThreshold;

    public GenerationalGarbageCollector(int threshold) {
        youngGen = new ArrayList<>();
        oldGen = new ArrayList<>();
        promotionThreshold = threshold;
    }

    // Function to simulate object allocation
    private void allocate(Object obj) {
        youngGen.add(new WeakReference<>(obj));
    }

    // Function to simulate minor GC in the Young Generation
    private void minorGC() {
        List<WeakReference<Object>> survivors = new ArrayList<>();
        for (WeakReference<Object> ref : youngGen) {
            if (ref.get() != null) {
                survivors.add(ref);
            }
        }
        youngGen.clear();
        youngGen.addAll(survivors);
    }

    // Function to simulate promotion of objects to the Old Generation
    private void promoteToOldGen() {
        for (int i = 0; i < youngGen.size(); i++) {
            if (i >= promotionThreshold) {
                oldGen.add(youngGen.get(i));
                youngGen.remove(i--);
            }
        }
    }

    // Function to simulate major GC in the Old Generation
    private void majorGC() {
        List<WeakReference<Object>> survivors = new ArrayList<>();
        for (WeakReference<Object> ref : oldGen) {
            if (ref.get() != null) {
                survivors.add(ref);
            }
        }
        oldGen.clear();
        oldGen.addAll(survivors);
    }

    // Function to simulate the GC process
    public void garbageCollect() {
        System.out.println("Minor GC in Young Generation...");
        minorGC();

        promoteToOldGen();
        System.out.println("Promoting objects to Old Generation...");

        System.out.println("Major GC in Old Generation...");
        majorGC();
    }

    // Example usage
    public static void main(String[] args) {
        GenerationalGarbageCollector gc = new GenerationalGarbageCollector(2);

        // Simulate object allocation
        for (int i = 0; i < 10; i++) {
            gc.allocate(new Object());
        }

        System.out.println(gc.youngGen.toString());

        // Perform garbage collection
        gc.garbageCollect();
        System.out.println(gc.youngGen.toString());
    }
}
