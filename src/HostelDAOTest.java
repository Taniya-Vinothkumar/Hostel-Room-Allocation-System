import java.util.List;

import dao.HostelDAO;
import model.Hostel;

public class HostelDAOTest {

    public static void main(String[] args) {

        HostelDAO dao = new HostelDAO();

        // INSERT
        Hostel hostel = new Hostel(
                5,
                "Test Hostel",
                "MEN",
                "C"
        );

        boolean inserted = dao.addHostel(hostel);

        if (inserted) {
            System.out.println("Hostel inserted successfully!");
        } else {
            System.out.println("Hostel insertion failed!");
        }

        // READ
        System.out.println("\nHostel Records:");

        List<Hostel> hostels = dao.getAllHostels();

        for (Hostel h : hostels) {

            System.out.println(
                    h.getHostelId() + " | " +
                    h.getHostelName() + " | " +
                    h.getHostelType() + " | " +
                    h.getBlock()
            );
        }

        // UPDATE
        hostel.setHostelName("Test Hostel Updated");

        boolean updated = dao.updateHostel(hostel);

        if (updated) {
            System.out.println("\nHostel updated successfully!");
        } else {
            System.out.println("\nHostel update failed!");
        }

        // DELETE
        boolean deleted = dao.deleteHostel(5);

        if (deleted) {
            System.out.println("Hostel deleted successfully!");
        } else {
            System.out.println("Hostel deletion failed!");
        }
    }
}