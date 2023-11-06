/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Intefaces;

import java.util.List;

public interface GenericDao<T, PK> {
    boolean create(T entity);
    T read(PK id);
    boolean update(T entity);
    boolean delete(T entity);
    List<T> getAll();
    String recuperarPk(String Pk, String tabla, String Formato);
}

