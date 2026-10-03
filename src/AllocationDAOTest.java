import java.sql.Date;
import java.util.List;

import dao.AllocationDAO;
import model.Allocation;

public class AllocationDAOTest {

    public static void main(String[] args) {

        AllocationDAO dao = new AllocationDAO();

        // INSERT
        Allocation allocation = new Allocation(
                0,
                101,
                1,
                Date.valueOf("2026-10-03")
        );

        boolean inserted =
                dao.addAllocation(allocation);

        if (inserted) {
            System.out.println(
                    "Allocation inserted successfully!"
            );
        } else {
            System.out.println(
                    "Allocation insertion failed!"
            );
        }

        // READ
        System.out.println("\nAllocation Records:");

        List<Allocation> allocations =
                dao.getAllAllocations();

        for (Allocation a : allocations) {

            System.out.println(
                    a.getAllocationId() + " | " +
                    a.getStudentId() + " | " +
                    a.getRoomId() + " | " +
                    a.getAllocationDate()
            );
        }

        // DELETE
        if (!allocations.isEmpty()) {

            int allocationId =
                    allocations.get(
                            allocations.size() - 1
                    ).getAllocationId();

            boolean deleted =
                    dao.deleteAllocation(
                            allocationId
                    );

            if (deleted) {
                System.out.println(
                        "\nAllocation deleted successfully!"
                );
            } else {
                System.out.println(
                        "\nAllocation deletion failed!"
                );
            }
        }
    }
}