package com.store.app.service;

import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Date;

public interface TreeServiceInterface {
    public ArrayList<ModelMap> getTree();
    public ModelMap            save(String name);
    public ModelMap            updateTree(ModelMap map);
    public ModelMap            deleteCategory(ModelMap map);
    public ModelMap            editCategory(ModelMap map);
    public ModelMap saveItemEntity(final int listId, final String category, final String description,
                                   final Date startDate, final Date endDate, final boolean completed, final int positionsInList);
    public ModelMap saveDoListEntity(final String email, final boolean priv, final String listName);

}
