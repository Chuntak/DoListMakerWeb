package com.store.app.controller;

import com.google.appengine.api.datastore.Entity;
import com.store.app.domain.Item;
import com.store.app.domain.ToDoList;
import com.store.app.service.ToDoListServiceInterface;
import com.store.app.service.TreeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class HomeController {

    ToDoListServiceInterface toDoListService;

    @Autowired
    public HomeController(ToDoListServiceInterface todoListService){
        this.toDoListService = todoListService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "Home/index";
    }

//    @RequestMapping(value = "/userModel", method = RequestMethod.GET)
//    public ModelAndView userModel() {
//        return new ModelAndView("userModel", "command", new UserModel());
//    }

    /*
    * Fake Mapping to test controller from AngularJS http request
    * */
    @RequestMapping(value = "/getMessages", method = RequestMethod.GET)
    public @ResponseBody ArrayList<ToDoList> getMessages(@ModelAttribute("ClansWebApp") ToDoList tDL){
        return toDoListService.getToDoListArrayEntity(tDL);
    }


    /*
    * Get all the lists that the owner created
    * */
    @RequestMapping(value = "/getMyLists", method = RequestMethod.GET)
    public @ResponseBody ArrayList<ToDoList> getMyLists(@ModelAttribute("TodoListModel") ToDoList user){
        String email = user.getEmail();
        ArrayList<ToDoList> lists = new ArrayList<ToDoList>();
        return lists;
    }

    /*
    * Get all the lists that the owner created and all public lists
    * */
    @RequestMapping(value = "/getAllLists", method = RequestMethod.GET)
    public @ResponseBody ArrayList<ToDoList> getAllLists(@ModelAttribute("TodoListModel") ToDoList user){
        String email = user.getEmail();
        ArrayList<ToDoList> lists = new ArrayList<ToDoList>();
        return lists;
    }

    /*
    * Get all the items based on the listId
    * */
    @RequestMapping(value = "/getItems", method = RequestMethod.GET)
    public @ResponseBody ArrayList<Item> getItems(@ModelAttribute("TodoListModel") ToDoList list){
        //int listId = list.();
        ArrayList<Item> lists = new ArrayList<Item>();
        return lists;
    }

    /*
    * Add a new list
    * */
    @RequestMapping(value = "/addList", method = RequestMethod.GET)
    public @ResponseBody boolean addList(@ModelAttribute("TodoListModel") ToDoList list){
        return toDoListService.saveDoListEntity(list.getEmail(), list.getPrivate(), list.getListName(), list.getID());
    }

    /*
    * Add a new item
    * */
    @RequestMapping(value = "/addItem", method = RequestMethod.GET)
    public @ResponseBody boolean addItem(@ModelAttribute("ItemModel") Item item){
        return false;
    }

    /*
    * Update an existing list with new data
    * */
    @RequestMapping(value = "/updateList", method = RequestMethod.GET)
    public @ResponseBody Entity updateList(@ModelAttribute("TodoListModel") ToDoList list){
    //    list = new ToDoList("email", false, "name");
        return toDoListService.updateDoListEntity(list.getEntity());
    }

    /*
    * Update an existing item with new data
    * */
    @RequestMapping(value = "/updateItem", method = RequestMethod.GET)
    public @ResponseBody boolean addList(@ModelAttribute("ItemModel") Item item){
        return false;
    }

    /*
    * Delete an existing list (NOT IN SPECS. COMPLETE LATER)
    * */
    @RequestMapping(value = "/deleteList", method = RequestMethod.GET)
    public @ResponseBody boolean deleteList(@ModelAttribute("TodoListModel") ToDoList list){
        return false;
    }

    /*
    * Delete an existing item
    * */
    @RequestMapping(value = "/deleteItem", method = RequestMethod.GET)
    public @ResponseBody boolean deleteItem(@ModelAttribute("ItemModel") Item item){
        return false;
    }

/*  //////////////////////////////////////////////////////////////////////////////////////////  */
/*  //////////////////////////////////////////////////////////////////////////////////////////  */
/*  //////////////////////////////////////////////////////////////////////////////////////////  */
/*         BELOW IS CODE FROM PREV PROJ. KEEP ONLY TO OBSERVE INTERACTION WITH DATA STORE        */
/*  //////////////////////////////////////////////////////////////////////////////////////////  */
/*  //////////////////////////////////////////////////////////////////////////////////////////  */
/*  //////////////////////////////////////////////////////////////////////////////////////////  */


//
//    @RequestMapping(value = "get-tree", method=RequestMethod.POST, headers = "Accept=application/json")
//    @ResponseBody
//    public ArrayList<ModelMap> getTree() {
//
//        return treeService.getTree();
//
//    }
//
//    @RequestMapping(value = "new-category", method=RequestMethod.POST, headers = "Accept=application/json")
//    @ResponseBody
//    public ModelMap newCategory(@RequestBody ModelMap map) {
//
//        String name = map.get("name").toString();
//        return  treeService.save(name);
//
//    }
//
//    @RequestMapping(value = "delete-category", method=RequestMethod.POST, headers = "Accept=application/json")
//    @ResponseBody
//    public ModelMap deleteCategory(@RequestBody ModelMap map) {
//
//        return  treeService.deleteCategory(map);
//
//    }
//
//    @RequestMapping(value = "update-tree", method=RequestMethod.POST, headers = "Accept=application/json")
//    @ResponseBody
//    public ModelMap updateTree(@RequestBody ModelMap map) {
//        return  treeService.updateTree(map);
//    }
//
//    @RequestMapping(value = "edit-category", method=RequestMethod.POST, headers = "Accept=application/json")
//    @ResponseBody
//    public ModelMap editCategory(@RequestBody ModelMap map) {
//
//        return  treeService.editCategory(map);
//
//    }


}
