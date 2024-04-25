package com.example.application.Adapters.Service;

import com.example.application.models.Villa;
import org.bson.Document;

public class VillaServiceAdapter extends abstractServiceAdapter<Villa>{
    @Override
    public Villa toObject(Document doc) {
        return new Villa(
                doc.getString("villaName"),
                doc.getString("address"),
                doc.getInteger("numberOfRooms"),
                doc.getString("hasPrivateBathroom"),
                doc.getString("hasKitchenFacilities"),
                doc.getDouble("pricePerNight")
        );
    }
}
