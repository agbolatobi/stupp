package com.example.mcproject.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mcproject.R;
import com.example.mcproject.database.checklist.Checklist;
import com.example.mcproject.database.checklist.ChecklistDAO;
import com.example.mcproject.database.events.event;
import com.example.mcproject.database.events.eventDAO;
import com.example.mcproject.database.places.Places;
import com.example.mcproject.database.places.placesDAO;
import com.example.mcproject.database.places_images.PlacesImages;
import com.example.mcproject.database.places_images.PlacesImagesDAO;
import com.example.mcproject.database.toDoList.to_do_list;
import com.example.mcproject.database.toDoList.to_do_listDAO;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {Checklist.class, event.class, Places.class, PlacesImages.class, to_do_list.class}, version = 28, exportSchema = false)
public abstract class database extends RoomDatabase {
    // Declaration of variables and DAOS
    private static database INSTANCE;
    public abstract ChecklistDAO checklistDAO();
    public abstract eventDAO eventDAO();
    public abstract placesDAO placeDAO();
    public abstract PlacesImagesDAO place_imageDAO();
    public abstract to_do_listDAO to_do_listDAO();

    public static database getAppDatabase(Context context) {
        if (INSTANCE == null) {
            //Setting of Database instance
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), database.class, "database").allowMainThreadQueries().fallbackToDestructiveMigration().build();
            if(isCheckListEmpty(INSTANCE)){
                populateDatabase(INSTANCE);
            }

        }
        return INSTANCE;
    }

    //This Methods Adds Events to the Database
    private static void addEvent(event input,final database db){
        db.eventDAO().insertAll(input);
    }

    //This Method add Checklist objects to the database
    private static void addCheckList(Checklist input,final database db){
        db.checklistDAO().insertAll(input);
    }

    //This method adds place objects to the database
    private static void addPlace(Places input,final database db){
        db.placeDAO().insertAll(input);
    }

    //This MEthod adds To Do List Objects to the Database
    private static void addTo_do_list(to_do_list input,final database db){
        db.to_do_listDAO().insertAll(input);
    }

    //This Method Adds Place Images to the database
    private static void addAPlace_image(PlacesImages input,final database db){
        db.place_imageDAO().insertAll(input);
    }

    //This method Populate the database with default values if it is empty
    private static void populateDatabase(final database db){
       for(Checklist item : getChecklistData()){
           addCheckList(item,db);
       }

       for(to_do_list item : getToDoListData()){
           addTo_do_list(item,db);
       }

        for(Places item : getPlacesData()){
            addPlace(item,db);
        }

        for(PlacesImages item : getPlacesImageData()){
            addAPlace_image(item,db);
        }

        for(event item : getEventsData()){
            addEvent(item,db);
        }

    }

    //This Method returns a list of checklist objects to populate the database
    private static List<Checklist> getChecklistData(){
        List<Checklist> CheckListItems= new ArrayList<Checklist>();

        Checklist item1 = new Checklist();
        item1.setCheck_list_item("Get Student ID Card");
        item1.setCheck_list_location("Howe Hall 6230 Coburg Rd");
        item1.setCheck_list_status("Not Done");
        CheckListItems.add(item1);

        Checklist item2 = new Checklist();
        item2.setCheck_list_item("Get Your Bus Pass");
        item2.setCheck_list_location("Howe Hall 6230 Coburg Rd");
        item2.setCheck_list_status("Not Done");
        CheckListItems.add(item2);

        Checklist item3 = new Checklist();
        item3.setCheck_list_item("Register For Your Courses");
        item3.setCheck_list_location("cas.dal.ca");
        item3.setCheck_list_status("Not Done");
        CheckListItems.add(item3);

        Checklist item4 = new Checklist();
        item4.setCheck_list_item("Dalhousie Orientation Week");
        item4.setCheck_list_location("Student Union Building");
        item4.setCheck_list_status("Not Done");
        CheckListItems.add(item4);

        Checklist item5 = new Checklist();
        item5.setCheck_list_item("Book Residence On Campus");
        item5.setCheck_list_location("dal.ca/residence");
        item5.setCheck_list_status("Not Done");
        CheckListItems.add(item5);

        Checklist item6 = new Checklist();
        item6.setCheck_list_item("Book Residence Off Campus");
        item6.setCheck_list_location("dal.ca/campus_life/residence_housing/off-campus-living.html");
        item6.setCheck_list_status("Not Done");
        CheckListItems.add(item6);

        Checklist item7 = new Checklist();
        item7.setCheck_list_item("Accept or waive your health insurance");
        item7.setCheck_list_location("cas.dal.ca");
        item7.setCheck_list_status("Not Done");
        CheckListItems.add(item7);
        //
        Checklist item8 = new Checklist();
        item8.setCheck_list_item("Pay your tuition & fees");
        item8.setCheck_list_location("cas.dal.ca");
        item8.setCheck_list_status("Not Done");
        CheckListItems.add(item8);
        return CheckListItems;
    }

    //This Method returns a list of event objects to populate the database
    private static List<event> getEventsData(){
        List<event> EventsItems= new ArrayList<event>();

        event item1 = new event();
        item1.setEvent_name("Bric NS Student Seminar Series");
        item1.setEvent_Banner_link(R.drawable.seminar);
        item1.setEvent_date("31 July 2019");
        item1.setEvent_Description("Seminar");
        item1.setEvent_time("6:00 PM");
        item1.setYear(2019);
        item1.setMonth(6);
        item1.setDay(31);
        item1.setHour(12);
        item1.setMin(0);
        item1.setSecond(30);
        item1.setPlace_id(2);
        item1.setAttending(false);
        EventsItems.add(item1);

        event item2 = new event();
        item2.setEvent_name("Faculty Of Science Talk");
        item2.setEvent_Banner_link(R.drawable.seminar);
        item2.setEvent_date("2 August 2019");
        item2.setEvent_Description("Seminar");
        item2.setEvent_time("6:00 PM");
        item2.setYear(2019);
        item2.setMonth(7);
        item2.setDay(2);
        item2.setHour(12);
        item2.setMin(0);
        item2.setSecond(0);
        item2.setPlace_id(2);
        item2.setAttending(false);
        EventsItems.add(item2);

        event item3 = new event();
        item3.setEvent_name("Dalhousie Orientation Week");
        item3.setEvent_Banner_link(R.drawable.seminar);
        item3.setEvent_date("1 August 2019");
        item3.setEvent_Description("Seminar");
        item3.setEvent_time("9:00 AM");
        item3.setYear(2019);
        item3.setMonth(7);
        item3.setDay(31);
        item3.setHour(9);
        item3.setMin(0);
        item3.setSecond(0);
        item3.setPlace_id(6);
        item3.setAttending(false);
        EventsItems.add(item3);

        event item4 = new event();
        item4.setEvent_name("Annual Computer Science Meeting");
        item4.setEvent_Banner_link(R.drawable.meeting);
        item4.setEvent_date("31 July 2019");
        item4.setEvent_Description("Meeting");
        item4.setEvent_time("6:00 PM");
        item4.setYear(2019);
        item4.setMonth(6);
        item4.setDay(31);
        item4.setHour(12);
        item4.setMin(0);
        item4.setSecond(0);
        item4.setPlace_id(2);
        item4.setAttending(false);
        EventsItems.add(item4);

        event item5 = new event();
        item5.setEvent_name("Beat The Heat Summer");
        item5.setEvent_Banner_link(R.drawable.party);
        item5.setEvent_date("29 July 2019");
        item5.setEvent_Description("Party");
        item5.setEvent_time("2:00 PM");
        item5.setYear(2019);
        item5.setMonth(6);
        item5.setDay(29);
        item5.setHour(14);
        item5.setMin(0);
        item5.setSecond(0);
        item5.setPlace_id(6);
        item5.setAttending(false);
        EventsItems.add(item5);

        event item6 = new event();
        item6.setEvent_name("Computer Science BBQ");
        item6.setEvent_Banner_link(R.drawable.party);
        item6.setEvent_date("30 July 2019");
        item6.setEvent_Description("Party");
        item6.setEvent_time("6:00 PM");
        item6.setYear(2019);
        item6.setMonth(6);
        item6.setDay(30);
        item6.setHour(12);
        item6.setMin(0);
        item6.setSecond(0);
        item6.setPlace_id(6);
        item6.setAttending(false);
        EventsItems.add(item6);

        event item7 = new event();
        item7.setEvent_name("Big Data Seminar");
        item7.setEvent_Banner_link(R.drawable.seminar);
        item7.setEvent_date("30 September 2019");
        item7.setEvent_Description("Seminar");
        item7.setEvent_time("6:00 PM");
        item7.setYear(2019);
        item7.setMonth(8);
        item7.setDay(30);
        item7.setHour(12);
        item7.setMin(0);
        item7.setSecond(30);
        item7.setPlace_id(2);
        item7.setAttending(false);
        EventsItems.add(item7);


        return EventsItems;
    }

    //This Method returns a list of Places objects to populate the database
    private static List<Places> getPlacesData(){
        List<Places> PlacesItems= new ArrayList<Places>();

        Places item1 = new Places();
        item1.setPlace_name("Dalhousie University Bookstore");
        item1.setPlace_description("Dalhousie Building | Stores | Books | Clothes | Stationary");
        item1.setPlace_ratings(4.3);
        item1.setPlace_type("Stores");
        item1.setPlace_lat(44.637046);
        item1.setPlace_lng(-63.589257);
        item1.setPlace_img_1(R.drawable.bookstore_1);
        item1.setPlace_img_2(R.drawable.bookstore_2);
        PlacesItems.add(item1);

        Places item2 = new Places();
        item2.setPlace_name("Mona Campell Building");
        item2.setPlace_description("Dalhousie Building | Classses");
        item2.setPlace_ratings(4.8);
        item2.setPlace_type("Buildings");
        item2.setPlace_lat(44.639161);
        item2.setPlace_lng(-63.590572);
        item2.setPlace_img_1(R.drawable.mona_campell_1);
        item2.setPlace_img_2(R.drawable.mona_campell_2);
        PlacesItems.add(item2);

        Places item3 = new Places();
        item3.setPlace_name("Coburg Social Bar & Café");
        item3.setPlace_description("Coffee | Tea | Social Hangout | Food");
        item3.setPlace_ratings(4.4);
        item3.setPlace_type("Restaurants");
        item3.setPlace_lat(44.639831);
        item3.setPlace_lng(-63.588987);
        item3.setPlace_img_1(R.drawable.colburg_1);
        item3.setPlace_img_2(R.drawable.colburg_2);
        PlacesItems.add(item3);

        Places item4 = new Places();
        item4.setPlace_name("Dalplex");
        item4.setPlace_description("Dalhousie Building | Gym | Field | Track | Fitness");
        item4.setPlace_ratings(4.0);
        item4.setPlace_type("Buildings");
        item4.setPlace_lat(44.634085);
        item4.setPlace_lng(-63.591314);
        item4.setPlace_img_1(R.drawable.daplex_1);
        item4.setPlace_img_2(R.drawable.daplex_2);
        PlacesItems.add(item4);

        Places item5 = new Places();
        item5.setPlace_name("Killam Memorial Library");
        item5.setPlace_description("Dalhousie Building | Books | Coffee | Restaurant");
        item5.setPlace_ratings(4.5);
        item5.setPlace_type("Stores");
        item5.setPlace_lat(44.637446);
        item5.setPlace_lng(-63.591188);
        item5.setPlace_img_1(R.drawable.killiam_1);
        item5.setPlace_img_2(R.drawable.killiam_2);
        PlacesItems.add(item5);

        Places item6 = new Places();
        item6.setPlace_name("Student Union Building");
        item6.setPlace_description("Dalhousie Building | Books | Coffee | Restaurant");
        item6.setPlace_ratings(4.3);
        item6.setPlace_type("Buildings");
        item6.setPlace_lat(44.636758 );
        item6.setPlace_lng(-63.588860);
        item6.setPlace_img_1(R.drawable.bookstore_1);
        item6.setPlace_img_2(R.drawable.sub_2);
        PlacesItems.add(item6);

        Places item7 = new Places();
        item7.setPlace_name("Marion McCain Arts and Social Sciences Building");
        item7.setPlace_description("Dalhousie Building | Classes | Art");
        item7.setPlace_ratings(5);
        item7.setPlace_type("Buildings");
        item7.setPlace_lat(44.639831);
        item7.setPlace_lng(-63.588987);
        item7.setPlace_img_1(R.drawable.mcain_arts_1);
        item7.setPlace_img_2(R.drawable.mcain_arts_2);
        PlacesItems.add(item7);

        Places item8 = new Places();
        item8.setPlace_name("Kenneth C. Rowe Management Building");
        item8.setPlace_description("Dalhousie Building | Coffee | Restaurant");
        item8.setPlace_ratings(4.0);
        item8.setPlace_type("Buildings");
        item8.setPlace_lat(44.637071);
        item8.setPlace_lng(-63.588235);
        item8.setPlace_img_1(R.drawable.kenneth_1);
        item8.setPlace_img_2(R.drawable.kenneth_2);
        PlacesItems.add(item8);

        Places item9 = new Places();
        item9.setPlace_name("Dalhousie Department of Physics & Atmospheric Science");
        item9.setPlace_description("Dalhousie Building | Books | Classes");
        item9.setPlace_ratings(3.5);
        item9.setPlace_type("Buildings");
        item9.setPlace_lat(44.638026);
        item9.setPlace_lng(-63.593457);
        item9.setPlace_img_1(R.drawable.chemistry_building_1);
        item9.setPlace_img_2(R.drawable.chemistry_building_1);
        PlacesItems.add(item9);

        Places item10 = new Places();
        item10.setPlace_name("Dalhousie Arts Centre");
        item10.setPlace_description("Dalhousie Building | Books | Classes");
        item10.setPlace_ratings(4.3);
        item10.setPlace_type("Buildings");
        item10.setPlace_lat(44.638151);
        item10.setPlace_lng(-63.588472);
        item10.setPlace_img_1(R.drawable.mcain_arts_1);
        item10.setPlace_img_2(R.drawable.mcain_arts_2);
        PlacesItems.add(item10);

        Places item11 = new Places();
        item11.setPlace_name("Chemistry Building");
        item11.setPlace_description("Dalhousie Building | Books | Classes | Research");
        item11.setPlace_ratings(5);
        item11.setPlace_type("Buildings");
        item11.setPlace_lat(44.636894);
        item11.setPlace_lng(-63.591983);
        item11.setPlace_img_1(R.drawable.chemistry_building_1);
        item11.setPlace_img_2(R.drawable.chemistry_building_2);
        PlacesItems.add(item11);

        Places item12 = new Places();
        item12.setPlace_name("Dalhousie Community Garden");
        item12.setPlace_description("Dalhousie Building | Relaxation");
        item12.setPlace_ratings(4.3);
        item12.setPlace_type("Buildings");
        item12.setPlace_lat(44.637206);
        item12.setPlace_lng(-63.587280);
        item12.setPlace_img_1(R.drawable.killiam_1);
        item12.setPlace_img_2(R.drawable.killiam_2);
        PlacesItems.add(item12);

        Places item13 = new Places();
        item13.setPlace_name("Dalhousie Security Services");
        item13.setPlace_description("Dalhousie Building");
        item13.setPlace_ratings(3.8);
        item13.setPlace_type("Buildings");
        item13.setPlace_lat(44.637471);
        item13.setPlace_lng(-63.589465);
        item13.setPlace_img_1(R.drawable.chemistry_building_1);
        item13.setPlace_img_2(R.drawable.chemistry_building_2);
        PlacesItems.add(item13);

        Places item14 = new Places();
        item14.setPlace_name("Goldberg Computer Science Building");
        item14.setPlace_description("Dalhousie Building | Books | Coffee | Restaurant");
        item14.setPlace_ratings(5);
        item14.setPlace_type("Buildings");
        item14.setPlace_lat(44.637631);
        item14.setPlace_lng(-63.587209);
        item14.setPlace_img_1(R.drawable.goldberg_1);
        item14.setPlace_img_2(R.drawable.goldberg_2);
        PlacesItems.add(item14);

        Places item15 = new Places();
        item15.setPlace_name("Tim hortons");
        item15.setPlace_description("Coffee | Restaurant");
        item15.setPlace_ratings(3.4);
        item15.setPlace_type("Restaurants");
        item15.setPlace_lat(44.637811);
        item15.setPlace_lng(-63.584041);
        item15.setPlace_img_1(R.drawable.sub_1);
        item15.setPlace_img_2(R.drawable.sub_2);
        PlacesItems.add(item15);

        Places item16 = new Places();
        item16.setPlace_name("Dalhousie University Bookstore");
        item16.setPlace_description("Dalhousie Building | Books | Items");
        item16.setPlace_ratings(4.3);
        item16.setPlace_type("Stores");
        item16.setPlace_lat(44.637046);
        item16.setPlace_lng(-63.589257);
        item16.setPlace_img_1(R.drawable.bookstore_1);
        item16.setPlace_img_2(R.drawable.bookstore_2);
        PlacesItems.add(item16);

        Places item17 = new Places();
        item17.setPlace_name("Henry Hicks Building");
        item17.setPlace_description("Dalhousie Building | Classes | Research");
        item17.setPlace_ratings(5);
        item17.setPlace_type("Buildings");
        item17.setPlace_lat(44.636403);
        item17.setPlace_lng(-63.593105);
        item17.setPlace_img_1(R.drawable.chemistry_building_1);
        item17.setPlace_img_2(R.drawable.chemistry_building_2);
        PlacesItems.add(item17);

        Places item18 = new Places();
        item18.setPlace_name("Walmart");
        item18.setPlace_description("Items | Clothes | Provisions | Coffee | Restaurant");
        item18.setPlace_ratings(3.7);
        item18.setPlace_type("Stores");
        item18.setPlace_lat(44.646584);
        item18.setPlace_lng(-63.620721);
        item18.setPlace_img_1(R.drawable.walmart_1);
        item18.setPlace_img_2(R.drawable.walmart_2);
        PlacesItems.add(item18);

        Places item19 = new Places();
        item19.setPlace_name("Dollarama");
        item19.setPlace_description("Affordable | Items | Provisions ");
        item19.setPlace_ratings(4.0);
        item19.setPlace_type("Stores");
        item19.setPlace_lat(44.642812);
        item19.setPlace_lng(-63.578542);
        item19.setPlace_img_1(R.drawable.parklane_1);
        item19.setPlace_img_2(R.drawable.parklane_2);
        PlacesItems.add(item19);

        Places item20 = new Places();
        item20.setPlace_name("Super Store");
        item20.setPlace_description("Shopping, comfortable, affordable ");
        item20.setPlace_ratings(4.3);
        item20.setPlace_type("Stores");
        item20.setPlace_lat(44.646749);
        item20.setPlace_lng(-63.594688);
        item20.setPlace_img_1(R.drawable.superstore_1);
        item20.setPlace_img_2(R.drawable.superstore_2);
        PlacesItems.add(item20);

        Places item21 = new Places();
        item21.setPlace_name("Athens Restaurant");
        item21.setPlace_description("Relaxed mainstay serving a wide variety of Greek dishes, including vegan & gluten-free options.");
        item21.setPlace_ratings(4.0);
        item21.setPlace_type("Restaurants");
        item21.setPlace_lat(44.642812);
        item21.setPlace_lng(-63.578542);
        item21.setPlace_img_1(R.drawable.parklane_1);
        item21.setPlace_img_2(R.drawable.parklane_2);
        PlacesItems.add(item21);

        Places item22 = new Places();
        item22.setPlace_name("Sweet Hereafter Cheesecakery");
        item22.setPlace_description("Food, Sweet, Cakes, romantic ");
        item22.setPlace_ratings(4.0);
        item22.setPlace_type("Restaurants");
        item22.setPlace_lat(44.645314);
        item22.setPlace_lng(-63.598078);
        item22.setPlace_img_1(R.drawable.parklane_1);
        item22.setPlace_img_2(R.drawable.parklane_2);
        PlacesItems.add(item22);





        return PlacesItems;
    }

    //This Method returns a list of place image objects to populate the database
    private static List<PlacesImages> getPlacesImageData(){
        List<PlacesImages> PlacesImagesItems= new ArrayList<PlacesImages>();

//        Checklist item1 = new Checklist();
//        item1.setCheck_list_item("Get Student ID Card");
//        item1.setCheck_list_location("Howe Hall 6230 Coburg Rd");
//        item1.setCheck_list_status("Not Done");
//        CheckListItems.add(item1);

        return PlacesImagesItems;
    }

    //This Method returns a list of to do list objects to populate the database
    private static List<to_do_list> getToDoListData(){
        List<to_do_list> ToDoListItems= new ArrayList<to_do_list>();

        to_do_list item1 = new to_do_list();
        item1.setTo_do_list_item("Check The Student Checklist");
        item1.setTo_do_list_source_id(0);
        item1.setTo_do_list_source_type("generated");
        ToDoListItems.add(item1);

        to_do_list item2 = new to_do_list();
        item2.setTo_do_list_item("Download Mobile recommended Apps");
        item2.setTo_do_list_source_id(0);
        item2.setTo_do_list_source_type("generated");
        ToDoListItems.add(item2);

        return ToDoListItems;
    }

    //this method checks if the checklist table is empty
    private static boolean isCheckListEmpty(database INSTANCE){
        if(INSTANCE.checklistDAO().getAll().size()==0){
            return true;
        }else{
            return false;
        }
    }

    //this method checks if the events table is empty
    private static boolean isEventEmpty(database INSTANCE){
        if(INSTANCE.eventDAO().getAll().size()==0){
            return true;
        }else{
            return false;
        }
    }

    //this method checks if the place table is empty
    private static boolean isPlaceEmpty(database INSTANCE){
        if(INSTANCE.placeDAO().getAll().size()==0){
            return true;
        }else{
            return false;
        }
    }

    //this method checks if the place image table is empty
    private static boolean isPlaceImageEmpty(database INSTANCE){
        if(INSTANCE.place_imageDAO().getAll().size()==0){
            return true;
        }else{
            return false;
        }
    }
    //this method checks if the to do list table is empty
    private static boolean isToDoListEmpty(database INSTANCE){
        if(INSTANCE.to_do_listDAO().getAll().size()==0){
            return true;
        }else{
            return false;
        }
    }

    //this table destorys the instance
    public static void destroyInstance() {

        INSTANCE = null;
    }

}
