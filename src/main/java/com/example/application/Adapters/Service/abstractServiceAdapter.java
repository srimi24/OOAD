package com.example.application.Adapters.Service;

import org.bson.Document;

public abstract class abstractServiceAdapter<T> {
    abstract T toObject(Document doc);
}
