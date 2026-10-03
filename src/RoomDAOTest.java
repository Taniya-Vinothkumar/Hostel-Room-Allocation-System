import java.util.List;

import dao.RoomDAO;
import model.Room;

public class RoomDAOTest {

    public static void main(String[] args) {

        RoomDAO dao = new RoomDAO();

        // INSERT
        Room room = new Room(
                11,
                1,
                3,
                "A301",
                "Double",
                2,
                0
        );

        boolean inserted = dao.addRoom(room);

        if (inserted) {
            System.out.println("Room inserted successfully!");
        } else {
            System.out.println("Room insertion failed!");
        }

        // READ
        System.out.println("\nRoom Records:");

        List<Room> rooms = dao.getAllRooms();

        for (Room r : rooms) {

            System.out.println(
                    r.getRoomId() + " | " +
                    r.getHostelId() + " | " +
                    r.getFloor() + " | " +
                    r.getRoomNumber() + " | " +
                    r.getRoomType() + " | " +
                    r.getCapacity() + " | " +
                    r.getOccupied()
            );
        }

        // UPDATE
        room.setRoomType("Triple");
        room.setCapacity(3);

        boolean updated = dao.updateRoom(room);

        if (updated) {
            System.out.println("\nRoom updated successfully!");
        } else {
            System.out.println("\nRoom update failed!");
        }

        // DELETE
        boolean deleted = dao.deleteRoom(11);

        if (deleted) {
            System.out.println("Room deleted successfully!");
        } else {
            System.out.println("Room deletion failed!");
        }
    }
}