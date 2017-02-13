package com.store.app.service;

import com.google.appengine.api.datastore.*;
import com.store.app.domain.Item;
import com.store.app.domain.ToDoList;
import com.store.app.domain.TreeBranch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Date;

import static com.store.app.domain.ToDoList.*;

/**
 * Created by Chuntak on 2/12/2017.
 */
@Service
public class ToDoListServiceImplementation implements ToDoListServiceInterface {
    DatastoreService datastoreService;
    @Autowired
    public ToDoListServiceImplementation(DatastoreServiceFactoryInterface datastoreServiceFactory) {
        this.datastoreService = datastoreServiceFactory.getDatastoreService();
    }

    public boolean saveItemEntity(final int listId, final String category, final String description,
                                  final Date startDate, final Date endDate, final boolean completed, final int positionInList) {
        Item it = new Item(listId, category, description, startDate, endDate, completed, positionInList);
        Entity itEntity = it.getEntity();
        return SaveTransactions(itEntity);
    }

    public boolean saveDoListEntity(final String email, final boolean priv, final String listName, final long id){
        ToDoList tdl = new ToDoList(email, priv, listName, id);
        Entity toDoEntity = tdl.getEntity();
        return SaveTransactions(toDoEntity);
    }

    public Entity updateDoListEntity(Entity entity) {
       // ToDoList tdl = new ToDoList( (String) entity.getProperty(EMAIL),(Boolean) entity.getProperty(PRIVATE),(String) entity.getProperty(LIST_NAME));
        Query query = new Query(TO_DO_LIST_ENTITY);
   //     Query.Filter keyFilter = new Query.FilterPredicate(ID, Query.FilterOperator.EQUAL, entity.getProperty(ID));
   //     query.setFilter(keyFilter);
        Entity ent = null;
        for (Entity e : datastoreService.prepare(query).asIterable()){
            if(e.getProperty(ID) == entity.getProperty(ID)){
                ent = e;
                break;
            }
        }
        if(ent != null) {
            if (entity.hasProperty(EMAIL)) ent.setProperty(EMAIL, entity.getProperty(EMAIL));
            if (entity.hasProperty(PRIVATE)) ent.setProperty(PRIVATE, entity.getProperty(PRIVATE));
            if (entity.hasProperty(LIST_NAME)) ent.setProperty(LIST_NAME, entity.getProperty(LIST_NAME));
            SaveTransactions(ent);
            return ent;
        }
        return entity;
    }

    private boolean SaveTransactions(Entity entity){
        saveTransactions(entity);
        entity.setProperty(ID,entity.getKey().getId());
        return saveTransactions(entity);

    }

    private boolean saveTransactions(Entity entity){
        boolean saved = false;
        Transaction transaction = datastoreService.beginTransaction();
        try {
            // save entity in dataStore
            Key key = datastoreService.put(transaction, entity);
            transaction.commit();
            saved = true;
        } catch(Exception e) {
            saved = false;
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
                saved = false;
            }
        }
        return saved;
    }

    @Override
    public ArrayList<ToDoList> getToDoListArrayEntity(ToDoList tDL) {
        ArrayList<ToDoList> tDLArray = new ArrayList<ToDoList>();
        Query query = new Query(TO_DO_LIST_ENTITY);
        for (Entity entity : datastoreService.prepare(query).asIterable()) {
            ToDoList model = new ToDoList(entity);
            tDLArray.add(model);
        }
        return tDLArray;
    }

    public ArrayList<ToDoList> getToDoListArrayByEmail(ToDoList tDL) {
        ArrayList<ToDoList> tDLArray = new ArrayList<ToDoList>();
        Query query = new Query(TO_DO_LIST_ENTITY);
        Query.Filter keyFilter = new Query.FilterPredicate(EMAIL, Query.FilterOperator.EQUAL, tDL.getEmail());
        for (Entity entity : datastoreService.prepare(query).asIterable()) {
            ToDoList model = new ToDoList(entity);
            tDLArray.add(model);
        }
        return tDLArray;
    }

    @Override
    public ToDoList getToDoListEntity(ToDoList tDL) {
        Query query = new Query(TO_DO_LIST_ENTITY);
        Query.Filter keyFilter =
                new Query.FilterPredicate(Entity.KEY_RESERVED_PROPERTY, Query.FilterOperator.EQUAL, tDL.getEntity().getKey());
        query.setFilter(keyFilter);
        ToDoList model = new ToDoList(datastoreService.prepare(query).asSingleEntity());
        return model;
    }


}
