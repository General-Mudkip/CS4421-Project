import java.awt.*;

public class FetchData {
    public static void subMenu(String userInput, int subChoice){
        System.out.println(userInput + subChoice);
        cpuInfo CpuInfo = new cpuInfo();
        diskInfo DiskInfo = new diskInfo();
        pciInfo PciInfo = new pciInfo();


        if (userInput.equals("CPU_Info")){
            if (subChoice == 1){
                System.out.print(CpuInfo.getModel());
            } else if (subChoice == 2) {
                System.out.print(CpuInfo.getIdleTime(1));
            } else{
                try {
                    TheProject.menu();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }
        if (userInput.equals("Disk_Info")){
            if (subChoice == 1){
                DiskInfo.diskCount();
            } else if (subChoice == 2) {
                DiskInfo.getModel();
            } else if (subChoice == 3) {
                try {
                    TheProject.menu();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }
        if (userInput.equals("CPU_Info")){
            if (subChoice == 1){
                System.out.print(PciInfo.deviceCount(1));
            } else if (subChoice ==2) {
                PciInfo.busCount();
            } else if (subChoice ==3) {
                try {
                    TheProject.menu();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (userInput.equals("CPU_Info")) {
            if (subChoice == 1) {
                CpuInfo.coresPerSocket();
            } else if (subChoice == 2) {
                CpuInfo.socketCount();
            } else if (subChoice == 3) {
                try {
                    TheProject.menu();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            }
        if (userInput. equals("CPU_Info")){
            if (subChoice == 1){
                CpuInfo.l1dCacheSize();
            }
            if (subChoice == 2){
                CpuInfo.l1iCacheSize();
            }
        }

    }
}
