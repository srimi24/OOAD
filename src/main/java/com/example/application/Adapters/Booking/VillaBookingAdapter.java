package com.example.application.Adapters.Booking;

import com.example.application.Adapters.Booking.abstractBookingAdapter;
import com.example.application.models.VillaBooking;
import org.bson.Document;

public class VillaBookingAdapter extends abstractBookingAdapter {
    private String villaName;
    private String address;

    public String getVillaName() {
        return villaName;
    }

    public void setVillaName(String villaName) {
        this.villaName = villaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public VillaBooking toBooking(Document doc) {
        return new VillaBooking(
                doc.getObjectId("_id").toString(),
                doc.getString("username"),
                doc.getString("villa_name"),
                doc.getString("address"),
                doc.getString("total_price"),
                doc.getString("date_booked"),
                doc.getString("paid")
        );
    }
}
