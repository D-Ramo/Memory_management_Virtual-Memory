
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class main {

    private final int SIZE = 256;
    private Memory memory;
    private final String LOGICAL_ADDRESS_FILENAME = "addresses.txt";

    public static void main(String[] args) {
        new main().init();
    }

    public void init() {
        int frameNumber = 0;
        memory = new Memory(SIZE);
        memory.initPageTableAndTLB();
        BufferedReader br = null;
        String fileName = LOGICAL_ADDRESS_FILENAME;
        int logicalAddress = 0;
        File file = new File(fileName);
        FileReader fr;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            while ((logicalAddress = Integer.parseInt(br.readLine())) > 0) {
                int pageNumber = logicalAddress >> 8;
                int offset = logicalAddress & 0x00FF;
                if (memory.TLB.containsKey(pageNumber)) {
                    memory.incrTLBHits();
                    frameNumber = memory.TLB.get(pageNumber);
                } else if (memory.pageTable[pageNumber] > 0) {
                    frameNumber = memory.pageTable[pageNumber];
                } else {
                    memory.handlePageFault(pageNumber, frameNumber);
                }

                memory.addAddress(frameNumber, offset);
                memory.checkAndUpdateTLB(pageNumber, frameNumber);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException | NumberFormatException e) {
        }
        memory.printOutPut();
    }

}
