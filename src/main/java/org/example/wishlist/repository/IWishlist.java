package org.example.wishlist.repository;

import java.util.ArrayList;
import java.util.List;

public interface IWishlist<T> {
    public void create(T t);
    public List<T> readAll(); // kan også hedde findAll()
    public T read(int id); // kan også hedde find()
    public void update (T t);
    public void delete (int id);
}
