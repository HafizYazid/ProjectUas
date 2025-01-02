/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itenas.is.oop.uasproject.gymproject.service;

import java.util.List;

/**
 *
 * @author Hafiz Yazid
 */
public interface CRUDService<T> {
    void create(T object);
    T findOne(String id);
    List<T> findAll();
    void update(String id, T object);
    boolean delete(int id);
}
