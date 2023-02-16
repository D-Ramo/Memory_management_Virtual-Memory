
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Memory {

    public int totalFrames;
    public int TLBHits;
    public int pageFaults;
    public int totalAddresses;
    public int pageTable[];
    public byte physicalMemory[];
    public Map<Integer, Integer> TLB = null;
    ArrayList<Address> listAddresses = new ArrayList<>();
    private String BACKING_STORE_FILENAME;
    private RandomAccessFile backingStore;
    private int physicalMemoryAccess;

    public Memory(int SIZE) {
        pageTable = new int[SIZE];
        physicalMemory = new byte[SIZE * SIZE];
        totalFrames = 0;
        TLBHits = 0;
        pageFaults = 0;
        totalAddresses = 0;
        BACKING_STORE_FILENAME = "BACKING_STORE.bin";
        backingStore = null;
    }

    public void initPageTableAndTLB() {
        TLB = new HashMap<>();
        for (int i = 0; i < this.pageTable.length; i++) {
            this.pageTable[i] = -1;
        }

    }

    public void checkAndUpdateTLB(int page, int frameNumber) {
        if (TLB.size() <= 16) {
            if (TLB.containsKey(page)) {
                TLB.remove(page);
                TLB.put(page, frameNumber);
            }
            TLB.put(page, frameNumber);
        } else {
            TLB.remove(page);
        }
    }

    public void incrTLBHits() {
        TLBHits = TLBHits + 1;
    }

    public void handlePageFault(int pageNumber, int frameNumber) {
        pageFaults++;

        String fileName = BACKING_STORE_FILENAME;

        try {
            backingStore = new RandomAccessFile(fileName, "r");

            backingStore.seek(pageNumber * pageTable.length);
            backingStore.read(physicalMemory, physicalMemoryAccess, pageTable.length);

            physicalMemoryAccess += pageTable.length;
            pageTable[pageNumber] = totalFrames * pageTable.length;
            totalFrames++;
            frameNumber = pageTable[pageNumber];

            backingStore.close();

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (Exception e) {
        }
    }

    public void addAddress(int frameNumber, int offset) {
        totalAddresses++;
        int physicalMemoryAddress = frameNumber | offset;
        listAddresses.add(new Address(physicalMemory[physicalMemoryAddress], physicalMemoryAddress, offset));
    }
    public void printOutPut() {
        for (int i = 0; i < listAddresses.size(); i++) {
            System.out.printf("Virtual address: %3d  Physical adress: %5d \tValue: %3d \n" , listAddresses.get(i).getLogicalAddress(),listAddresses.get(i).getPhysicalMemoryAddress(),listAddresses.get(i).getSignedValue());
    
        }
        double pageFaultRate = (double) pageFaults / totalAddresses;
        double TLBHitRate = (double) TLBHits / totalAddresses;

        System.out.println("Page Fault Rate : " + pageFaultRate);
        System.out.println("TLB Hit Rate : " + TLBHitRate);

    }


}
