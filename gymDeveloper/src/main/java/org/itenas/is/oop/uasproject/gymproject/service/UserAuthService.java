package org.itenas.is.oop.uasproject.gymproject.service;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */


import org.itenas.is.oop.uasproject.gymproject.model.User;
/**
 *
 * @author Hafiz Yazid
 */
public interface UserAuthService {
    public User authenticateUser(User user);
    public User registrasiUser(User user);
}
