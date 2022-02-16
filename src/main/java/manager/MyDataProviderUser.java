package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDataProviderUser {

    //============================================LOGIN=============================================
    @DataProvider
    public Iterator<Object[]> loginValidData() {
        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{"jack123@gmail.com","Js12345@"});
        list.add(new Object[]{"will123@gmail.com","Js12346@"});
        list.add(new Object[]{"syao123@gmail.com","Js12347@"});

        return list.iterator();
    }

    //    @DataProvider //ne rabochii
//    public Iterator<Object[]> CarValidData(){
//        List<Object[]> list = new ArrayList<>();
//
//        list.add(new Object[]{"Jack","Sparrow","1234567890","jack123@gmail.com","Haifa","Friend""});
//        list.add(new Object[]{"Jack","Sparrow","1234567890","jack123@gmail.com","Haifa","Friend""});
//        list.add(new Object[]{"Jack","Sparrow","1234567890","jack123@gmail.com","Haifa","Friend""});
//        return list.iterator();
//    }

    //rabochii
    @DataProvider
    public Iterator<Object[]> loginValidDataModel() {
        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{new User().withEmail("jack123@gmail.com").withPassword("Js12345@")});
        list.add(new Object[]{new User().withEmail("will123@gmail.com").withPassword("Js12346@")});
        list.add(new Object[]{new User().withEmail("syao123@gmail.com").withPassword("Js12347@")});

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> loginValidDataCSV() throws IOException {

        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/login.csv"));
        String line = reader.readLine();

        while (line!=null){
            String[] split = line.split(",");
            list.add(new Object[]{split[0],split[1]});
            line = reader.readLine();
        }
        return list.iterator();
    }
    //=============================================REGISTRATION=====================================
//rabochii
    @DataProvider
    public Iterator<Object[]> registrationValidData() {
        int index = (int) (System.currentTimeMillis() / 1000 % 3600);
        System.out.println(index);

        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{"Cen","Zu","cebr"+index+"@gmail.com","Ce1"+index+"5@"});
        list.add(new Object[]{"Violetta","Dim","voitoud"+index+"@gmail.com","Vo1"+index+"6@"});
        list.add(new Object[]{"Hes","Crav","hoic"+index+"@gmail.com","Ho1"+index+"7@"});

        return list.iterator();
    }

}


