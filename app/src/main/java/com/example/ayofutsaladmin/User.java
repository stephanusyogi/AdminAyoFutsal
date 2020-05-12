package com.example.ayofutsaladmin;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    private int id_user;
    private String name;
    private String point;
    private String nohp;
    private int type;
    public static final int INSERT_TYPE=1;
    public static final int UPDATE_TYPE=2;
    public static final int DELETE_TYPE=3;

    public static User generateInsertObject(String name, String point, String nohp)
    {
        return new User (-1, name, point, nohp, User.INSERT_TYPE);
    }

    public static User generateUpdateObject(int id_user, String name, String point, String nohp)
    {
        return new User (id_user, name, point, nohp, User.UPDATE_TYPE);
    }

    public static User generateDeleteObject(int id_user){
        return new User (id_user, null, null, null, User.DELETE_TYPE);
    }

    public JSONObject getJsonProduct(){
        JSONObject obj = new JSONObject();
        try {
            switch (type) {
                case User.INSERT_TYPE:
                    obj.put("name", this.name);
                    obj.put("point", this.point);
                    obj.put("nohp", this.nohp);
                    break;
                case User.UPDATE_TYPE:
                    obj.put("id_user", this.id_user);
                    obj.put("name", this.name);
                    obj.put("point", this.point);
                    obj.put("nohp", this.nohp);
                    break;
                case User.DELETE_TYPE:
                    obj.put("id_lap",this.id_user);
                    break;
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return obj;
    }

    public String getJsonString() {
        return getJsonProduct().toString();
    }

    // Untuk sqlite (tanpa type)
    public User(int id_user, String name, String point, String nohp){
        this.id_user = id_user;
        this.name = name;
        this.point = point;
        this.nohp = nohp;
    }

    // Untuk generate object dan server (dengan type)
    public User(int id_user, String name, String point, String nohp, int type) {
        this.id_user = id_user;
        this.name = name;
        this.point = point;
        this.nohp = nohp;
        this.type = type;
    }

    public int getId_user() {
        return id_user;
    }

    public String getName() {
        return name;
    }

    public String getPoint() {
        return point;
    }

    public String getNohp() {
        return nohp;
    }
    public int getType() {
        return type;
    }
}
