package GetSysInfo;

public class MemInfo {
    private static String path = "/proc/meminfo";

    public static String getMemFree() {

        try {
            String rawMemFree = ProcReader.readData(path, "MemFree");
            return rawMemFree;
        } catch (NumberFormatException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }


    public static String getMemAvailable() {
        try {
            String rawMemAvailable = ProcReader.readData(path, "MemAvailable");
            return rawMemAvailable;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getBuffers() {
        try {
            String rawBuffers = ProcReader.readData(path, "Buffers");
            return rawBuffers;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getCached() {
        try {
            String rawCached = ProcReader.readData(path, "Cached");
            return rawCached;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getSwapCached() {
        try {
            String rawSwapCached = ProcReader.readData(path, "SwapCached");
            return rawSwapCached;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getActive() {
        try {
            String rawActive = ProcReader.readData(path, "Active");
            return rawActive;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getInactive() {
        try {
            String rawInactive = ProcReader.readData(path, "Inactive");
            return rawInactive;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getActiveanon() {
        try {
            String rawActiveanon = ProcReader.readData(path, "Active anon");
            return rawActiveanon;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getInactiveanon() {
        try {
            String rawInactiveanon = ProcReader.readData(path, "Inactive anon");
            return rawInactiveanon;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getActivefile() {
        try {
            String rawActivefile = ProcReader.readData(path, "Active file");
            return rawActivefile;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getInactivefile() {
        try {
            String rawInactivefile = ProcReader.readData(path, "Inactive file");
            return rawInactivefile;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUnevictable() {
        try {
            String rawUnevictable = ProcReader.readData(path, "Unevictable");
            return rawUnevictable;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getMlocked() {
        try {
            String rawMlocked = ProcReader.readData(path, "Mlocked");
            return rawMlocked;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getSwapTotal() {
        try {
            String rawSwapTotal = ProcReader.readData(path, "Swap Total");
            return rawSwapTotal;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getSwapFree() {
        try {
            String rawSwapFree = ProcReader.readData(path, "Swap Free");
            return rawSwapFree;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getDirty() {
        try {
            String rawDirty = ProcReader.readData(path, "Dirty");
            return rawDirty;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getWriteback() {
        try {
            String rawWriteback = ProcReader.readData(path, "Writeback");
            return rawWriteback;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getAnonPages() {
        try {
            String rawAnonPages = ProcReader.readData(path, "Anon Pages");
            return rawAnonPages;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getMapped() {
        try {
            String rawMapped = ProcReader.readData(path, "Mapped");
            return rawMapped;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getShmem() {
        try {
            String rawShmem = ProcReader.readData(path, "Shmem");
            return rawShmem;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getKReclaimable() {
        try {
            String rawKReclaimable = ProcReader.readData(path, "KReclaimable");
            return rawKReclaimable;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getSlab() {
        try {
            String rawSlab = ProcReader.readData(path, "Slab");
            return rawSlab;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getSReclaimable() {
        try {
            String rawSReclaimable = ProcReader.readData(path, "SReclaimable");
            return rawSReclaimable;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getSUnreclaim() {
        try {
            String rawSUnreclaim = ProcReader.readData(path, "SUnreclaim");
            return rawSUnreclaim;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getKernelStack() {
        try {
            String rawKernelStack = ProcReader.readData(path, "KernelStack");
            return rawKernelStack;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPageTables() {
        try {
            String rawPageTables = ProcReader.readData(path, "PageTables");
            return rawPageTables;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getNFS_Unstable() {
        try {
            String rawNFS_Unstable = ProcReader.readData(path, "NFS_Unstable");
            return rawNFS_Unstable;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getBounce() {
        try {
            String rawBounce = ProcReader.readData(path, "Bounce");
            return rawBounce;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getWritebackTmp() {
        try {
            String rawWritebackTmp = ProcReader.readData(path, "WritebackTmp");
            return rawWritebackTmp;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getCommitLimit() {
        try {
            String rawCommitLimit = ProcReader.readData(path, "CommitLimit");
            return rawCommitLimit;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getCommitted_AS() {
        try {
            String rawCommitted_AS = ProcReader.readData(path, "Committed_AS");
            return rawCommitted_AS;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getVmallocTotal() {
        try {
            String rawVmallocTotal = ProcReader.readData(path, "VmallocTotal");
            return rawVmallocTotal;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getVmallocUsed() {
        try {
            String rawVmallocUsed = ProcReader.readData(path, "VmallocUsed");
            return rawVmallocUsed;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getVmallocChunk() {
        try {
            String rawVmallocChunk = ProcReader.readData(path, "VmallocChunk");
            return rawVmallocChunk;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPercpu() {
        try {
            String rawPercpu = ProcReader.readData(path, "Percpu");
            return rawPercpu;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getAnonHugePages() {
        try {
            String rawAnonHugePages = ProcReader.readData(path, "AnonHugePages");
            return rawAnonHugePages;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}