package com.store.app.service;

import com.google.appengine.api.datastore.Entity;
import com.store.app.domain.ToDoList;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Chuntak on 2/12/2017.
 */
public interface ToDoListServiceInterface {
    public boolean saveItemEntity(final int listId, final String category, final String description,
                                   final Date startDate, final Date endDate, final boolean completed, final int positionsInList);
    public boolean saveDoListEntity(final String email, final boolean priv, final String listName, final long iD);
    public ArrayList<ToDoList> getToDoListArrayEntity(ToDoList tDL);
    public ArrayList<ToDoList> getToDoListArrayByEmail(ToDoList tDL);
    public Entity updateDoListEntity(Entity entity);
    public ToDoList getToDoListEntity(ToDoList tDL);

}