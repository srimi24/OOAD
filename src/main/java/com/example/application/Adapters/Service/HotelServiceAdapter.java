package com.example.application.Adapters.Service;

import com.example.application.models.Hotel;
import org.bson.Document;

public class HotelServiceAdapter extends abstractServiceAdapter<Hotel> {
    @Override
    public Hotel toObject(Document doc) {
        return new Hotel(
                doc.getString("name"),
                doc.getString("location"),
                doc.getInteger("numberOfDeluxeRooms"),
                doc.getDouble("deluxeRoomPricePerNight"),
                doc.getInteger("numberOfStandardRooms"),
                doc.getDouble("standardRoomPricePerNight")
        );
    }
}
