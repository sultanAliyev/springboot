package kz.bitlab.springboot.db;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class DBManager {

    private static ArrayList<Tasks> tasks = new ArrayList<>();
    private static Long id = 6L;

    static {

        tasks.add(new Tasks(1L, "DO HOMEWORK", "05.04.2020", "YES"));
        tasks.add(new Tasks(2L, "GO FOR GYM", "05.04.2020", "NO"));
        tasks.add(new Tasks(3L, "CLEAN YOUR ROOM", "05.04.2020", "NO"));
        tasks.add(new Tasks(4L, "BUY FRUITS", "05.04.2020", "YES"));
        tasks.add(new Tasks(5L, "HELP FOR SOMEONE", "05.04.2020", "YES"));

    }

    public static ArrayList<Tasks> getAllItems(){
        return tasks;
    }

    public static void addItems(Tasks it){
        it.setId(id);
        tasks.add(it);
        id++;
    }


    public static Tasks getItem(Long id){

        for (Tasks it : tasks){
            if (it.getId()==id){
                return it;
            }
        }
return null;
    }


    public static void delete(Long id){

        tasks.removeIf(ta -> ta.getId() == id);

    }


    public static void saveTask(Tasks task) {
        delete(task.getId());
        tasks.add(task);

    }
}
